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

import it.arancia.domain.Professione;
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.ProfessioneRepository;
import it.arancia.service.dto.ProfessioneCriteria;
import it.arancia.service.dto.ProfessioneDTO;
import it.arancia.service.mapper.ProfessioneMapper;

/**
 * Service for executing complex queries for Professione entities in the database.
 * The main input is a {@link ProfessioneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProfessioneDTO} or a {@link Page} of {@link ProfessioneDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProfessioneQueryService extends QueryService<Professione> {

    private final Logger log = LoggerFactory.getLogger(ProfessioneQueryService.class);

    private final ProfessioneRepository professioneRepository;

    private final ProfessioneMapper professioneMapper;

    public ProfessioneQueryService(ProfessioneRepository professioneRepository, ProfessioneMapper professioneMapper) {
        this.professioneRepository = professioneRepository;
        this.professioneMapper = professioneMapper;
    }

    /**
     * Return a {@link List} of {@link ProfessioneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProfessioneDTO> findByCriteria(ProfessioneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Professione> specification = createSpecification(criteria);
        return professioneMapper.toDto(professioneRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProfessioneDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProfessioneDTO> findByCriteria(ProfessioneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Professione> specification = createSpecification(criteria);
        return professioneRepository.findAll(specification, page)
            .map(professioneMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProfessioneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Professione> specification = createSpecification(criteria);
        return professioneRepository.count(specification);
    }

    /**
     * Function to convert ProfessioneCriteria to a {@link Specification}
     */
    private Specification<Professione> createSpecification(ProfessioneCriteria criteria) {
        Specification<Professione> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Professione_.id));
            }
            if (criteria.getCodice() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodice(), Professione_.codice));
            }
            if (criteria.getDenominazione() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDenominazione(), Professione_.denominazione));
            }
            if (criteria.getAbilitato() != null) {
                specification = specification.and(buildSpecification(criteria.getAbilitato(), Professione_.abilitato));
            }
        }
        return specification;
    }
}
