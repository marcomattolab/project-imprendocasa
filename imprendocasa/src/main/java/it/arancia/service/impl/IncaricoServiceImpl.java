package it.arancia.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.arancia.domain.Cliente;
import it.arancia.domain.Incarico;
import it.arancia.domain.Tag;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.domain.enumeration.Tags;
import it.arancia.domain.enumeration.TipoNegoziazione;
import it.arancia.repository.ClienteRepository;
import it.arancia.repository.IncaricoRepository;
import it.arancia.repository.TagRepository;
import it.arancia.security.AuthoritiesConstants;
import it.arancia.security.SecurityUtils;
import it.arancia.service.IncaricoService;
import it.arancia.service.dto.IncaricoDTO;
import it.arancia.service.dto.IncaricoStatutesDTO;
import it.arancia.service.mapper.IncaricoMapper;

/**
 * Service Implementation for managing Incarico.
 */
@Service
@Transactional
public class IncaricoServiceImpl implements IncaricoService {
	private final Logger log = LoggerFactory.getLogger(IncaricoServiceImpl.class);

	private final IncaricoRepository incaricoRepository;
	private final IncaricoMapper incaricoMapper;
	private final ClienteRepository clienteRepository;
	private final TagRepository tagRepository;
	private final HashMap<StatoIncarico, List<StatoIncarico>> mappaStati;

	public IncaricoServiceImpl(IncaricoRepository incaricoRepository, IncaricoMapper incaricoMapper, ClienteRepository clienteRepository, TagRepository tagRepository) {
		this.incaricoRepository = incaricoRepository;
		this.incaricoMapper = incaricoMapper;
		this.clienteRepository = clienteRepository;
		this.tagRepository = tagRepository;
		this.mappaStati = buildMappaStati();
	}

	/**
	 * Save a incarico.
	 *
	 * @param incaricoDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public IncaricoDTO save(IncaricoDTO incaricoDTO) {
		log.debug("Request to save Incarico : {}", incaricoDTO);
		Incarico incarico = incaricoMapper.toEntity(incaricoDTO);
		incarico.updateFlgOscuraClienti();
		incarico = incaricoRepository.save(incarico);
		return incaricoMapper.toDto(incarico);
	}

	/**
	 * Get all the incaricos.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<IncaricoDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Incaricos");
		return incaricoRepository.findAll(pageable).map(incaricoMapper::toDto);
	}

	/**
	 * Get all the Incarico with eager load of many-to-many relationships.
	 *
	 * @return the list of entities
	 */
	public Page<IncaricoDTO> findAllWithEagerRelationships(Pageable pageable) {
		return incaricoRepository.findAllWithEagerRelationships(pageable).map(incaricoMapper::toDto);
	}

	/**
	 * Get one incarico by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<IncaricoDTO> findOne(Long id) {
		log.debug("Request to get Incarico : {}", id);
		return incaricoRepository.findOneWithEagerRelationships(id).map(incaricoMapper::toDto);
	}

	/**
	 * Delete the incarico by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Incarico : {}", id);
		incaricoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<IncaricoDTO> findAllDraft() {
		log.debug("Request to get all Incaricos in DRAFT status");
		List<Incarico> incaricoList = incaricoRepository.findByStato(StatoIncarico.BOZZA);
		List<IncaricoDTO> incaricoDTOList = incaricoMapper.toDto(incaricoList);
		return incaricoDTOList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean checkPermission(IncaricoDTO incaricoDTO) {
		boolean isReadOnly;
		boolean userRole = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
		if (!userRole && incaricoDTO.getStato() != null && !StatoIncarico.BOZZA.equals(incaricoDTO.getStato())) {
			isReadOnly = true;
		} else {
			isReadOnly = false;
		}
		return isReadOnly;
	}

	@Override
	public IncaricoDTO changeStatus(IncaricoDTO incaricoDTO) {
		log.debug("Request to change status to Incarico : {}", incaricoDTO);
		Incarico incarico = incaricoMapper.toEntity(incaricoDTO);
		
		// 1.Recupero Incarico dal Database per il precedente Stato
		Incarico savedIncarico = incaricoRepository.findById(incaricoDTO.getId()).orElse(null);

		if(savedIncarico == null) {
			log.error("Incarico con Id "+ incaricoDTO.getId() + " null e/o non recuperato.");
			return null;
		}
		
		// 2.Verifica Validità passaggio di stato
		boolean isPermittedChangeStatus = isPermittedChangeStatus(savedIncarico);
		StatoIncarico currentStatus = savedIncarico.getStato();
		StatoIncarico nextStatus = incaricoDTO.getStato();
		List<StatoIncarico> possibleNextStatutes = mappaStati.get(currentStatus);
		boolean isFirstTransition = StatoIncarico.BOZZA.equals(currentStatus) && StatoIncarico.ATTIVO.equals(nextStatus);
		boolean isFinalTransition = StatoIncarico.ATTIVO.equals(currentStatus) && StatoIncarico.CONCLUSO.equals(nextStatus);
		boolean isValidTransition = possibleNextStatutes.contains(nextStatus);

		//TODO MOVE INTO USER SERVICE (CENTRILIZE)
		boolean isAdmin = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)
				|| SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN);
		boolean isAgentOperator = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT)
				|| SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.OPERATOR);

		if ( isPermittedChangeStatus && (isAgentOperator && isFirstTransition) || (isAdmin && isValidTransition) ) {
			if (isFinalTransition) {
				// Cambio Stato Incarico ATTIVO => CONCLUSO
				
				//Target Status
				savedIncarico.setStato(incarico.getStato());
				
				// Salvo Flags Antiriciclaggio
				savedIncarico.setAntiriciclaggio(Boolean.TRUE);
				savedIncarico.setAntiriciclaggioAcquirenti(Boolean.TRUE);
				
				// Salvo il Totale delle Provvigioni
				savedIncarico.setImportoProvvigione(incarico.getImportoProvvigione());
				savedIncarico.setImportoProvvigioneAcquirenti(incarico.getImportoProvvigioneAcquirenti());
				
				//Salvo la relazione e Metto il Cliente come Acquirente
				savedIncarico.addAllAcquirenteLocatario(incarico.getAcquirenteLocatarios());

				// Salvo il codice antiriciclaggio degli Acquirenti/Conduttori
				updateAntiriciclaggio(incarico.getAcquirenteLocatarios(), Tags.ACQUIRENTE_CONDUTTORE);
				
				// Salvo il codice antiriciclaggio dei Proponenti
				updateAntiriciclaggio(incarico.getProponentes(), Tags.PROPONENTE);
				
				// Salvo Incarico
				savedIncarico = incaricoRepository.save(savedIncarico);
				
			} else {
				// Cambio Stato Incarico (Tutti gli Altri)
				savedIncarico.setStato(incarico.getStato());
				savedIncarico = incaricoRepository.save(savedIncarico);
			}
		} else {
			//THROW ERROR  
//			String msg = "Passaggio di stato " + currentStatus + " -> " + nextStatus + " non valido per Incarico Id "
//					+ incaricoDTO.getId() + " e per profilo utente " + SecurityUtils.getCurrentUserLogin();
			String msg = String.format("%s,%s,%s,%s,%s",
					"error.editIncarico.cambioStatoNonValido",
					currentStatus,
					nextStatus,
					incaricoDTO.getId(),
					SecurityUtils.getCurrentUserLogin());
			log.error(msg);
			throw new IllegalStateException(msg);
		}
		
		return incaricoMapper.toDto(incarico);
	}

	/**
	 * Update Codice Antiriciclaggio
	 * 
	 * @param clienti Lista Clienti
	 * @param tagCode Enum Tags
	 */
	private void updateAntiriciclaggio(Collection<Cliente> clienti, Tags tagCode) {
		if(clienti!=null) {
			for(Cliente cliente : clienti) {
				Long id = cliente.getId();
				String codiceAntiriciclaggio = cliente.getCodiceAntiriciclaggio();
				Cliente clienteAnag = clienteRepository.findById(cliente.getId()).orElse(null);
				if (clienteAnag!=null) {
					//Update Codice Antiriciclaggio nel Cliente 
					clienteAnag.setCodiceAntiriciclaggio(codiceAntiriciclaggio);
					Tag tag = tagRepository.findByCodice(tagCode.name());
					clienteAnag.addTag(tag);
					clienteRepository.save(clienteAnag);
				} else {
					log.error("Cliente con Id "+ id + " Tag code "+tagCode.name() + " non recuperato.");
				}
			}
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public IncaricoStatutesDTO getStatusInformation(Long idIncarico) {
		IncaricoStatutesDTO result = new IncaricoStatutesDTO();
		Optional<Incarico> savedIncarico = incaricoRepository.findById(idIncarico);
		Incarico saved = savedIncarico.orElse(null);
		if(saved!=null) {
			boolean isAdmin = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN)
					|| SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
			result.setId(idIncarico);
			result.setStatus(saved.getStato());
			result.setStatuses(saved.getStato() != null
					? (isAdmin ? mappaStati.get(StatoIncarico.ALL) : mappaStati.get(saved.getStato()))
					: null);
			result.setAllStatuses(mappaStati.get(StatoIncarico.ALL));
			result.setChangeStatusEnabled( isPermittedChangeStatus(saved) );
		} else {
			result.setErrorCode("immobile.notfound");
		}
		return result;
	}

	private boolean isPermittedChangeStatus(Incarico incarico) {
		boolean isPermitted = TipoNegoziazione.LOCAZIONE.equals(incarico.getTipo()) 
				|| TipoNegoziazione.COMPRAVENDITA.equals(incarico.getTipo()) ? true : false;
		return isPermitted;
	}
	
	/**
	 * Build Incarico Statutes Map.
	 * 
	 * @return Mappa Stati Incarico
	 */
	private HashMap<StatoIncarico, List<StatoIncarico>> buildMappaStati() {
		// BOZZA, ATTIVO, CONCLUSO, INTERROTTO, SCADUTO
		HashMap<StatoIncarico, List<StatoIncarico>> mappaStati = new HashMap<>();
		mappaStati.put(StatoIncarico.BOZZA, Arrays.asList(StatoIncarico.ATTIVO));
		mappaStati.put(StatoIncarico.ATTIVO,
				Arrays.asList(StatoIncarico.INTERROTTO, StatoIncarico.SCADUTO, StatoIncarico.CONCLUSO));
		mappaStati.put(StatoIncarico.INTERROTTO, Arrays.asList(StatoIncarico.ATTIVO));
		mappaStati.put(StatoIncarico.SCADUTO, Arrays.asList(StatoIncarico.ATTIVO));
		mappaStati.put(StatoIncarico.CONCLUSO, new ArrayList<>());
		mappaStati.put(StatoIncarico.ALL, Arrays.asList(StatoIncarico.BOZZA, StatoIncarico.ATTIVO,
				StatoIncarico.INTERROTTO, StatoIncarico.SCADUTO, StatoIncarico.CONCLUSO));
		return mappaStati;
	}

	@Override
	@Transactional(readOnly = true)
	public Long countNonDraftByImmobile(Long idImmobile) {
		log.debug("Request to count all Incaricos by immobile not in DRAFT status");
		Long incaricoCount = incaricoRepository.countByImmobileIdAndStatoNotIn(idImmobile, StatoIncarico.BOZZA);
		return incaricoCount;
	}

	@Override
	@Transactional(readOnly = true)
	public Long countNonDraftByCliente(Long idCliente) {
		log.debug("Request to count all Incaricos by cliente not in DRAFT status");
		Long count = 0l;
		List<Incarico> incarichiCliente = incaricoRepository.findAllByClienteAndStatoNotIn(idCliente, StatoIncarico.BOZZA);
		if (!CollectionUtils.isEmpty(incarichiCliente)) {
			count = Integer.valueOf(incarichiCliente.size()).longValue();
		}
		return count;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<IncaricoDTO> findAllNonDraftByCliente(Long idCliente) {
		log.debug("Request to get all Incaricos by cliente not in DRAFT status");
		List<Incarico> incaricoList = incaricoRepository.findAllByClienteAndStatoNotIn(idCliente, StatoIncarico.BOZZA);
		List<IncaricoDTO> incaricoDTOList = incaricoMapper.toDto(incaricoList);
		return incaricoDTOList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<IncaricoDTO> findAllByImmobile(Long idImmobile) {
		log.debug("Request to find all Incaricos by immobile");
		return incaricoRepository.findAllByImmobileId(idImmobile);
	}

}
