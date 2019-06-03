package it.arancia.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import it.arancia.domain.Geolocalizzazione;
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.GeolocalizzazioneRepository;
import it.arancia.service.dto.GeolocalizzazioneCriteria;
import it.arancia.service.dto.GeolocalizzazioneDTO;
import it.arancia.service.mapper.GeolocalizzazioneMapper;

/**
 * Service for executing complex queries for Geolocalizzazione entities in the database.
 * The main input is a {@link GeolocalizzazioneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GeolocalizzazioneDTO} or a {@link Page} of {@link GeolocalizzazioneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GeolocalizzazioneQueryService extends QueryService<Geolocalizzazione> {

    private final Logger log = LoggerFactory.getLogger(GeolocalizzazioneQueryService.class);

    private final GeolocalizzazioneRepository geolocalizzazioneRepository;

    private final GeolocalizzazioneMapper geolocalizzazioneMapper;

    public GeolocalizzazioneQueryService(GeolocalizzazioneRepository geolocalizzazioneRepository, GeolocalizzazioneMapper geolocalizzazioneMapper) {
        this.geolocalizzazioneRepository = geolocalizzazioneRepository;
        this.geolocalizzazioneMapper = geolocalizzazioneMapper;
    }

    /**
     * Return a {@link List} of {@link GeolocalizzazioneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GeolocalizzazioneDTO> findByCriteria(GeolocalizzazioneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Geolocalizzazione> specification = createSpecification(criteria);
        return geolocalizzazioneMapper.toDto(geolocalizzazioneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GeolocalizzazioneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GeolocalizzazioneDTO> findByCriteria(GeolocalizzazioneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Geolocalizzazione> specification = createSpecification(criteria);
        return geolocalizzazioneRepository.findAll(specification, page)
            .map(geolocalizzazioneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GeolocalizzazioneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Geolocalizzazione> specification = createSpecification(criteria);
        return geolocalizzazioneRepository.count(specification);
    }

    /**
     * Function to convert GeolocalizzazioneCriteria to a {@link Specification}
     */
    private Specification<Geolocalizzazione> createSpecification(GeolocalizzazioneCriteria criteria) {
        Specification<Geolocalizzazione> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Geolocalizzazione_.id));
            }
            if (criteria.getImmobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImmobile(), Geolocalizzazione_.immobile));
            }
            if (criteria.getLatitudine() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLatitudine(), Geolocalizzazione_.latitudine));
            }
            if (criteria.getLongitudine() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLongitudine(), Geolocalizzazione_.longitudine));
            }
            if (criteria.getPosizioneId() != null) {
                specification = specification.and(buildSpecification(criteria.getPosizioneId(),
                    root -> root.join(Geolocalizzazione_.posizione, JoinType.LEFT).get(Immobile_.id)));
            }
        }
        return specification;
    }
}
