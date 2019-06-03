package it.arancia.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.StringFilter;
// for static metamodels
import it.arancia.domain.Cliente;
import it.arancia.domain.Cliente_;
import it.arancia.domain.Incarico_;
import it.arancia.domain.ListaContatti_;
import it.arancia.domain.Tag_;
import it.arancia.repository.ClienteRepository;
import it.arancia.security.AuthoritiesConstants;
import it.arancia.security.SecurityUtils;
import it.arancia.service.dto.ClienteCriteria;
import it.arancia.service.dto.ClienteDTO;
import it.arancia.service.dto.EditableDTO;
import it.arancia.service.mapper.ClienteMapper;
import it.arancia.service.util.EntityProfilerUtils;

/**
 * Service for executing complex queries for Cliente entities in the database.
 * The main input is a {@link ClienteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ClienteDTO} or a {@link Page} of {@link ClienteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClienteQueryService extends QueryService<Cliente> {
	private final Logger log = LoggerFactory.getLogger(ClienteQueryService.class);
	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;
	private final EntityProfilerUtils entityProfilerUtils;
	
	private boolean securityEnabled = true;

	public ClienteQueryService(ClienteRepository clienteRepository, ClienteMapper clienteMapper, EntityProfilerUtils entityProfilerUtils) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
		this.entityProfilerUtils = entityProfilerUtils;
	}

	/**
	 * Return a {@link List} of {@link ClienteDTO} which matches the criteria from the database
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public List<ClienteDTO> findByCriteria(ClienteCriteria criteria) {
		log.debug("find by criteria : {}", criteria);
		final Specification<Cliente> specification = createSpecification(criteria);
		List<ClienteDTO> clienti = clienteMapper.toDto(clienteRepository.findAll(specification));
		entityProfilerUtils.completeInfoEditableDTO(clienti);
		return clienti;
	}

	/**
	 * Return a {@link Page} of {@link ClienteDTO} which matches the criteria from the database
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @param page The page, which should be returned.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public Page<ClienteDTO> findByCriteria(ClienteCriteria criteria, Pageable page) {
		log.debug("find by criteria : {}, page: {}", criteria.getCurrentUser(), page);
		final Specification<Cliente> specification = createSpecification(criteria);
		Page<ClienteDTO> clienti = clienteRepository.findAll(specification, page).map(clienteMapper::toDto);
		entityProfilerUtils.completeInfoEditableDTO(clienti);
		return clienti;
	}
	
	

	/**
	 * Return the number of matching entities in the database
	 * @param criteria The object which holds all the filters, which the entities should match.
	 * @return the number of matching entities.
	 */
	@Transactional(readOnly = true)
	public long countByCriteria(ClienteCriteria criteria) {
		log.debug("count by criteria : {}", criteria);
		final Specification<Cliente> specification = createSpecification(criteria);
		return clienteRepository.count(specification);
	}

	/**
	 * Function to convert ClienteCriteria to a {@link Specification}
	 */
	private Specification<Cliente> createSpecification(ClienteCriteria criteria) {
		Specification<Cliente> specification = Specification.where(null);
		if (criteria != null) {

			if (securityEnabled){
				boolean isAutenticated = SecurityUtils.isAuthenticated();
				boolean isAdmin = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN);
				boolean isAgent = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT);
				boolean isAgentPlus = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT_PLUS);
				boolean isOperator = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.OPERATOR);
				Optional<String> loggedUser = SecurityUtils.getCurrentUserLogin();
				String userName = loggedUser.orElse(null);

				//TODO DEVELOP THIS WITH AGENT_USER_NAME (FLAG OSCURA ??)
				if(isAutenticated && !isAdmin && !isAgentPlus && !isOperator && isAgent) {
					StringFilter currentUser = new StringFilter();
					currentUser.setEquals(userName);
					specification = specification.and(buildStringSpecification(currentUser, Cliente_.agente));
					// FILTRO PER FLAG_OSCURA = false (o null)
					BooleanFilter clienteAbilitatoFilter = new BooleanFilter();
					clienteAbilitatoFilter.setIn(Arrays.asList(true, null));
					specification = specification.and(buildSpecification(clienteAbilitatoFilter, Cliente_.abilitato));
				}
				if(!isAutenticated || (isAutenticated && !(isAdmin || isAgentPlus || isOperator || isAgent)) ) {
					StringFilter currentUser = new StringFilter();
					currentUser.setEquals("-1");
					specification = specification.and(buildStringSpecification(currentUser, Cliente_.agente));
				}
			}

			if (criteria.getFreeSearch() != null) {
				Specification<Cliente> freeSearchSpecification = Specification.where(null);
				freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Cliente_.cognome));
				freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Cliente_.nome));
				freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Cliente_.telefonoFisso));
				freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Cliente_.telefonoCellulare));
				freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Cliente_.indirizzo));
				freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Cliente_.email));
				specification = specification.and(freeSearchSpecification);
			}

			if (criteria.getId() != null) {
				specification = specification.and(buildSpecification(criteria.getId(), Cliente_.id));
			}
			if (criteria.getNome() != null) {
				specification = specification.and(buildStringSpecification(criteria.getNome(), Cliente_.nome));
			}
			if (criteria.getCognome() != null) {
				specification = specification.and(buildStringSpecification(criteria.getCognome(), Cliente_.cognome));
			}
			if (criteria.getCodiceFiscale() != null) {
				specification = specification.and(buildStringSpecification(criteria.getCodiceFiscale(), Cliente_.codiceFiscale));
			}
			if (criteria.getTitolo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitolo(), Cliente_.titolo));
            }
            if (criteria.getRagioneSociale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRagioneSociale(), Cliente_.ragioneSociale));
            }
            if (criteria.getAgente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgente(), Cliente_.agente));
            }
			if (criteria.getAlertCompleanno() != null) {
				specification = specification.and(buildSpecification(criteria.getAlertCompleanno(), Cliente_.alertCompleanno));
			}
			if (criteria.getDataNascita() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getDataNascita(), Cliente_.dataNascita));
			}
			if (criteria.getMeseNascita() != null) {
				specification = specification.and(buildSpecification(criteria.getMeseNascita(), Cliente_.meseNascita));
			}
			if (criteria.getTelefonoFisso() != null) {
				specification = specification.and(buildStringSpecification(criteria.getTelefonoFisso(), Cliente_.telefonoFisso));
			}
			if (criteria.getTelefonoCellulare() != null) {
				specification = specification.and(buildStringSpecification(criteria.getTelefonoCellulare(), Cliente_.telefonoCellulare));
			}
			if (criteria.getEmail() != null) {
				specification = specification.and(buildStringSpecification(criteria.getEmail(), Cliente_.email));
			}
			if (criteria.getIndirizzo() != null) {
				specification = specification.and(buildStringSpecification(criteria.getIndirizzo(), Cliente_.indirizzo));
			}
			if (criteria.getCap() != null) {
				specification = specification.and(buildStringSpecification(criteria.getCap(), Cliente_.cap));
			}
			if (criteria.getRegione() != null) {
				specification = specification.and(buildStringSpecification(criteria.getRegione(), Cliente_.regione));
			}
			if (criteria.getProvincia() != null) {
				specification = specification.and(buildStringSpecification(criteria.getProvincia(), Cliente_.provincia));
			}
			if (criteria.getCitta() != null) {
				specification = specification.and(buildStringSpecification(criteria.getCitta(), Cliente_.citta));
			}
			if (criteria.getCodiceAntiriciclaggio() != null) {
				specification = specification.and(buildStringSpecification(criteria.getCodiceAntiriciclaggio(), Cliente_.codiceAntiriciclaggio));
			}
			if (criteria.getImportoProvvigioni() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getImportoProvvigioni(), Cliente_.importoProvvigioni));
			}
			if (criteria.getImportoProvvigioniDerivate() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getImportoProvvigioniDerivate(), Cliente_.importoProvvigioniDerivate));
			}
			if (criteria.getNumeroPratiche() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getNumeroPratiche(), Cliente_.numeroPratiche));
			}
			if (criteria.getNumeroSegnalazioni() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getNumeroSegnalazioni(), Cliente_.numeroSegnalazioni));
			}
			if (criteria.getPunteggio() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getPunteggio(), Cliente_.punteggio));
			}
			if (criteria.getAbilitato() != null) {
				specification = specification.and(buildSpecification(criteria.getAbilitato(), Cliente_.abilitato));
			}
			if (criteria.getListaContattiId() != null) {
				specification = specification.and(buildSpecification(criteria.getListaContattiId(),
						root -> root.join(Cliente_.listaContattis, JoinType.LEFT).get(ListaContatti_.id)));
			}
			if (criteria.getTagId() != null) {
				specification = specification.and(buildSpecification(criteria.getTagId(),
						root -> root.join(Cliente_.tags, JoinType.LEFT).get(Tag_.id)));
			}
			//TODO FIXME COMMENT THIS
//			if (criteria.getIncaricoId() != null) {
//				specification = specification.and(buildSpecification(criteria.getIncaricoId(),
//						root -> root.join(Cliente_.incaricos, JoinType.LEFT).get(Incarico_.id)));
//			}
			//TODO FIXME DECOMMENT THESE
			if (criteria.getIncaricoCommittenteId() != null) {
	                specification = specification.and(buildSpecification(criteria.getIncaricoCommittenteId(),
	                    root -> root.join(Cliente_.incaricoCommittentes, JoinType.LEFT).get(Incarico_.id)));
	        }
            if (criteria.getIncaricoProponenteId() != null) {
                specification = specification.and(buildSpecification(criteria.getIncaricoProponenteId(),
                    root -> root.join(Cliente_.incaricoProponentes, JoinType.LEFT).get(Incarico_.id)));
            }
            if (criteria.getIncaricoAcquirenteLocatarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getIncaricoAcquirenteLocatarioId(),
                    root -> root.join(Cliente_.incaricoAcquirenteLocatarios, JoinType.LEFT).get(Incarico_.id)));
            }
            if (criteria.getIncaricoSegnalatoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getIncaricoSegnalatoreId(),
                    root -> root.join(Cliente_.incaricoSegnalatores, JoinType.LEFT).get(Incarico_.id)));
            }

		}
		return specification;
	}
}
