package it.arancia.service;

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
import io.github.jhipster.service.filter.StringFilter;
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.ImmobileRepository;
import it.arancia.security.AuthoritiesConstants;
import it.arancia.security.SecurityUtils;
import it.arancia.service.dto.ImmobileCriteria;
import it.arancia.service.dto.ImmobileDTO;
import it.arancia.service.mapper.ImmobileMapper;
import it.arancia.service.util.EntityProfilerUtils;

/**
 * Service for executing complex queries for Immobile entities in the database.
 * The main input is a {@link ImmobileCriteria} which gets converted to
 * {@link Specification}, in a way that all the filters must apply. It returns a
 * {@link List} of {@link ImmobileDTO} or a {@link Page} of {@link ImmobileDTO}
 * which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ImmobileQueryService extends QueryService<Immobile> {
	private final Logger log = LoggerFactory.getLogger(ImmobileQueryService.class);

	private final ImmobileRepository immobileRepository;
	private final ImmobileMapper immobileMapper;

	private final EntityProfilerUtils entityProfilerUtils;

	private boolean securityEnabled = true;

	public ImmobileQueryService(ImmobileRepository immobileRepository, ImmobileMapper immobileMapper,
			EntityProfilerUtils entityProfilerUtils) {
		this.immobileRepository = immobileRepository;
		this.immobileMapper = immobileMapper;
		this.entityProfilerUtils = entityProfilerUtils;
	}
	/**
	 * Return a {@link List} of {@link ImmobileDTO} which matches the criteria from
	 * the database
	 * 
	 * @param criteria The object which holds all the filters, which the entities
	 *                 should match.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public List<ImmobileDTO> findByCriteria(ImmobileCriteria criteria) {
		log.debug("find by criteria : {}", criteria);
		final Specification<Immobile> specification = createSpecification(criteria);
		List<ImmobileDTO> immobili = immobileMapper.toDto(immobileRepository.findAll(specification));
		entityProfilerUtils.completeInfoEditableDTO(immobili);
		return immobili;
	}
	/**
	 * Return a {@link Page} of {@link ImmobileDTO} which matches the criteria from
	 * the database
	 * 
	 * @param criteria The object which holds all the filters, which the entities
	 *                 should match.
	 * @param page     The page, which should be returned.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public Page<ImmobileDTO> findByCriteria(ImmobileCriteria criteria, Pageable page) {
		log.debug("find by criteria : {}, page: {}", criteria.getCurrentUser(), page);
		final Specification<Immobile> specification = createSpecification(criteria);
		Page<ImmobileDTO> immobili = immobileRepository.findAll(specification, page).map(immobileMapper::toDto);
		entityProfilerUtils.completeInfoEditableDTO(immobili);
		return immobili;
	}

	/**
	 * Return the number of matching entities in the database
	 * 
	 * @param criteria The object which holds all the filters, which the entities
	 *                 should match.
	 * @return the number of matching entities.
	 */
	@Transactional(readOnly = true)
	public long countByCriteria(ImmobileCriteria criteria) {
		log.debug("count by criteria : {}", criteria);
		final Specification<Immobile> specification = createSpecification(criteria);
		return immobileRepository.count(specification);
	}

	/**
	 * Function to convert ImmobileCriteria to a {@link Specification}
	 */
	private Specification<Immobile> createSpecification(ImmobileCriteria criteria) {
		Specification<Immobile> specification = Specification.where(null);

		if (securityEnabled) {
			boolean isAutenticated = SecurityUtils.isAuthenticated();
			boolean isAdmin = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)
					|| SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN);
			boolean isAgent = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT);
			boolean isAgentPlus = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT_PLUS);
			boolean isOperator = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.OPERATOR);
			Optional<String> loggedUser = SecurityUtils.getCurrentUserLogin();
			String userName = loggedUser.orElse(null);

			// TODO DEVELOP THIS WITH AGENT_USER_NAME (FLAG OSCURA ??)
			if (isAutenticated && !isAdmin && !isAgentPlus && !isOperator && isAgent) {
				StringFilter currentUser = new StringFilter();
				currentUser.setEquals(userName);
				specification = specification.and(buildStringSpecification(currentUser, Immobile_.agente));
			}
			if (!isAutenticated || (isAutenticated && !(isAdmin || isAgentPlus || isOperator || isAgent))) {
				StringFilter currentUser = new StringFilter();
				currentUser.setEquals("-1");
				specification = specification.and(buildStringSpecification(currentUser, Immobile_.agente));
			}
		}

		  if (criteria != null) {
      		if (criteria.getFreeSearch() != null) {
	         	Specification<Immobile> freeSearchSpecification = Specification.where(null);
	         	freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Immobile_.codice));
	         	freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Immobile_.indirizzo));
	         	specification = specification.and(freeSearchSpecification);
	        }
          if (criteria.getId() != null) {
              specification = specification.and(buildSpecification(criteria.getId(), Immobile_.id));
          }
          if (criteria.getAgente() != null) {
              specification = specification.and(buildStringSpecification(criteria.getAgente(), Immobile_.agente));
          }
          if (criteria.getCodice() != null) {
              specification = specification.and(buildStringSpecification(criteria.getCodice(), Immobile_.codice));
          }
          if (criteria.getIndirizzo() != null) {
              specification = specification.and(buildStringSpecification(criteria.getIndirizzo(), Immobile_.indirizzo));
          }
          if (criteria.getCap() != null) {
              specification = specification.and(buildStringSpecification(criteria.getCap(), Immobile_.cap));
          }
          if (criteria.getRegione() != null) {
              specification = specification.and(buildStringSpecification(criteria.getRegione(), Immobile_.regione));
          }
          if (criteria.getProvincia() != null) {
              specification = specification.and(buildStringSpecification(criteria.getProvincia(), Immobile_.provincia));
          }
          if (criteria.getCitta() != null) {
              specification = specification.and(buildStringSpecification(criteria.getCitta(), Immobile_.citta));
          }
          if (criteria.getPathFolder() != null) {
              specification = specification.and(buildStringSpecification(criteria.getPathFolder(), Immobile_.pathFolder));
          }
          if (criteria.getDatiCatastali() != null) {
              specification = specification.and(buildStringSpecification(criteria.getDatiCatastali(), Immobile_.datiCatastali));
          }
          if (criteria.getFoglio() != null) {
              specification = specification.and(buildStringSpecification(criteria.getFoglio(), Immobile_.foglio));
          }
          if (criteria.getParticella() != null) {
              specification = specification.and(buildStringSpecification(criteria.getParticella(), Immobile_.particella));
          }
          if (criteria.getSub() != null) {
              specification = specification.and(buildStringSpecification(criteria.getSub(), Immobile_.sub));
          }
          if (criteria.getGeolocalizzazioneId() != null) {
              specification = specification.and(buildSpecification(criteria.getGeolocalizzazioneId(),
                  root -> root.join(Immobile_.geolocalizzazione, JoinType.LEFT).get(Geolocalizzazione_.id)));
          }
          if (criteria.getFilesId() != null) {
              specification = specification.and(buildSpecification(criteria.getFilesId(),
                  root -> root.join(Immobile_.files, JoinType.LEFT).get(Files_.id)));
          }
          if (criteria.getIncaricoId() != null) {
              specification = specification.and(buildSpecification(criteria.getIncaricoId(),
                  root -> root.join(Immobile_.incaricos, JoinType.LEFT).get(Incarico_.id)));
          }
      }
		return specification;
	}

}
