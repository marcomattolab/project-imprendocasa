package it.arancia.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import it.arancia.domain.Geolocalizzazione;
import it.arancia.domain.Immobile;
import it.arancia.domain.Incarico;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.repository.GeolocalizzazioneRepository;
import it.arancia.repository.ImmobileRepository;
import it.arancia.security.AuthoritiesConstants;
import it.arancia.security.SecurityUtils;
import it.arancia.service.GeolocalizzazioneService;
import it.arancia.service.dto.GeolocalizzazioneDTO;
import it.arancia.service.dto.GeolocalizzazioneExtendedDTO;
import it.arancia.service.mapper.GeolocalizzazioneMapper;

/**
 * Service Implementation for managing Geolocalizzazione.
 */
@Service
@Transactional
public class GeolocalizzazioneServiceImpl implements GeolocalizzazioneService {
    private final Logger log = LoggerFactory.getLogger(GeolocalizzazioneServiceImpl.class);

    private final GeolocalizzazioneRepository geolocalizzazioneRepository;
    private final GeolocalizzazioneMapper geolocalizzazioneMapper;
    private final ImmobileRepository immobileRepository;

    public GeolocalizzazioneServiceImpl(GeolocalizzazioneRepository geolocalizzazioneRepository, GeolocalizzazioneMapper geolocalizzazioneMapper, ImmobileRepository immobileRepository) {
        this.geolocalizzazioneRepository = geolocalizzazioneRepository;
        this.geolocalizzazioneMapper = geolocalizzazioneMapper;
        this.immobileRepository = immobileRepository;
    }

    /**
     * Save a geolocalizzazione.
     *
     * @param geolocalizzazioneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GeolocalizzazioneDTO save(GeolocalizzazioneDTO geolocalizzazioneDTO) {
        log.debug("Request to save Geolocalizzazione : {}", geolocalizzazioneDTO);

        Geolocalizzazione geolocalizzazione = geolocalizzazioneMapper.toEntity(geolocalizzazioneDTO);
        geolocalizzazione = geolocalizzazioneRepository.save(geolocalizzazione);
        return geolocalizzazioneMapper.toDto(geolocalizzazione);
    }

    /**
     * Get all the geolocalizzaziones.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<GeolocalizzazioneDTO> findAll() {
        log.debug("Request to get all Geolocalizzaziones");
        return geolocalizzazioneRepository.findAll().stream()
            .map(geolocalizzazioneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the geolocalizzaziones where Posizione is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<GeolocalizzazioneDTO> findAllWherePosizioneIsNull() {
        log.debug("Request to get all geolocalizzaziones where Posizione is null");
        return StreamSupport
            .stream(geolocalizzazioneRepository.findAll().spliterator(), false)
            .filter(geolocalizzazione -> geolocalizzazione.getPosizione() == null)
            .map(geolocalizzazioneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one geolocalizzazione by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GeolocalizzazioneDTO> findOne(Long id) {
        log.debug("Request to get Geolocalizzazione : {}", id);
        return geolocalizzazioneRepository.findById(id)
            .map(geolocalizzazioneMapper::toDto);
    }

    /**
     * Delete the geolocalizzazione by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Geolocalizzazione : {}", id);
        geolocalizzazioneRepository.deleteById(id);
    }

    /**
     * Return Geolocalization Extended
     * 
     * @return List of GeolocalizzazioneExtendedDTO
     */
    @Override
    @Transactional(readOnly = true) 
    public List<GeolocalizzazioneExtendedDTO> findAllGeolocalizationExt() {
    	log.debug("Request to get all geolocalizzaziones extra data");

    	boolean isAutenticated = SecurityUtils.isAuthenticated();
    	boolean isAdmin = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN);
    	boolean isAgentPlus = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT_PLUS);
    	Optional<String> loggedUser = SecurityUtils.getCurrentUserLogin();
    	String username = loggedUser.orElse(null);

    	List<GeolocalizzazioneExtendedDTO> result = new ArrayList<>();
    	if(!isAutenticated) {
    		return result;
    	}
    	
    	List<Immobile> allImmobili = immobileRepository.findAll();
    	if ( !CollectionUtils.isEmpty(allImmobili) ) {
    		for(Immobile curImmobile: allImmobili) {
    			if(curImmobile!=null) {
    				//Get Incarico
    				Set<Incarico> setIncarichi = curImmobile.getIncaricos();
    				List<Incarico> listaIncarichi = setIncarichi!=null ? setIncarichi.stream().collect(Collectors.toList()) : new ArrayList<>();
    				Incarico incarico = !CollectionUtils.isEmpty(listaIncarichi) ? listaIncarichi.get(0) : null;

    				//TODO MOVE INTO MAPPER
    				Geolocalizzazione geolocalizzazione = curImmobile.getGeolocalizzazione();
    				boolean isOscurato = incarico!=null && incarico.isOscuraIncarico() ? true : false;
    				GeolocalizzazioneExtendedDTO dto = new GeolocalizzazioneExtendedDTO();
				    dto.setIdIncarico( incarico!=null ? incarico.getId() : null );
				    dto.setRiferimentoIncarico( incarico!=null ? incarico.getRiferimento(): null );
				    dto.setStatoIncarico( incarico!=null ? incarico.getStato() : null );
				    dto.setIdImmobile(curImmobile.getId()!=null ? curImmobile.getId() : null);
				    dto.setCodiceImmobile( curImmobile.getCodice()!=null ? curImmobile.getCodice() : null );
				    dto.setImmobile( curImmobile.getCodice()!=null ? curImmobile.getCodice() : null );
				    dto.setDescrizioneImmobile(curImmobile.getDescrizione()!=null ? curImmobile.getDescrizione() : null);
				    dto.setIdGeolocalizzazione( geolocalizzazione!=null ? geolocalizzazione.getId() : null );
				    dto.setLatitudine( geolocalizzazione!=null ? geolocalizzazione.getLatitudine() : null );
				    dto.setLongitudine( geolocalizzazione!=null ? geolocalizzazione.getLongitudine() : null );
    				
    				if(isAdmin) {
    					result.add(dto);
    				} else if (!isOscurato) {
    					if(isAgentPlus) {
    						result.add(dto);
    					} else {
    						if( curImmobile.getCreatedBy()!=null && curImmobile.getCreatedBy().equals(username)) {
    							result.add(dto);
    						}
    					}
    				}
    			}
    		}
    	}
    	return result;
    }
	
}
