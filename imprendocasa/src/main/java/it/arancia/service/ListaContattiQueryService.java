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

import it.arancia.domain.ListaContatti;
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.ListaContattiRepository;
import it.arancia.service.dto.ListaContattiCriteria;
import it.arancia.service.dto.ListaContattiDTO;
import it.arancia.service.dto.ListaContattiExtDTO;
import it.arancia.service.mapper.ListaContattiExtMapper;
import it.arancia.service.mapper.ListaContattiMapper;

/**
 * Service for executing complex queries for ListaContatti entities in the database.
 * The main input is a {@link ListaContattiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ListaContattiDTO} or a {@link Page} of {@link ListaContattiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ListaContattiQueryService extends QueryService<ListaContatti> {

    private final Logger log = LoggerFactory.getLogger(ListaContattiQueryService.class);

    private final ListaContattiRepository listaContattiRepository;

    private final ListaContattiMapper listaContattiMapper;
    
    private final ListaContattiExtMapper listaContattiExtMapper;

    public ListaContattiQueryService(ListaContattiRepository listaContattiRepository, ListaContattiMapper listaContattiMapper, ListaContattiExtMapper listaContattiExtMapper) {
        this.listaContattiRepository = listaContattiRepository;
        this.listaContattiMapper = listaContattiMapper;
        this.listaContattiExtMapper = listaContattiExtMapper;
    }

    /**
     * Return a {@link List} of {@link ListaContattiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ListaContattiDTO> findByCriteria(ListaContattiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ListaContatti> specification = createSpecification(criteria);
        return listaContattiMapper.toDto(listaContattiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ListaContattiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListaContattiDTO> findByCriteria(ListaContattiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListaContatti> specification = createSpecification(criteria);
        return listaContattiRepository.findAll(specification, page)
            .map(listaContattiMapper::toDto);
    }
    
    /**
     * Return a {@link Page} of {@link ListaContattiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ListaContattiExtDTO> findExtByCriteria(ListaContattiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ListaContatti> specification = createSpecification(criteria);
        return listaContattiRepository.findAll(specification, page).map(listaContattiExtMapper::toDto);
    }
    
    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ListaContattiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ListaContatti> specification = createSpecification(criteria);
        return listaContattiRepository.count(specification);
    }

    /**
     * Function to convert ListaContattiCriteria to a {@link Specification}
     */
    private Specification<ListaContatti> createSpecification(ListaContattiCriteria criteria) {
        Specification<ListaContatti> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ListaContatti_.id));
            }
            if (criteria.getDateTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateTime(), ListaContatti_.dateTime));
            }
            if (criteria.getEsito() != null) {
                specification = specification.and(buildSpecification(criteria.getEsito(), ListaContatti_.esito));
            }
            if (criteria.getMotivazione() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMotivazione(), ListaContatti_.motivazione));
            }
            if (criteria.getClienteId() != null) {
                specification = specification.and(buildSpecification(criteria.getClienteId(),
                    root -> root.join(ListaContatti_.cliente, JoinType.LEFT).get(Cliente_.id)));
            }
            if (criteria.getIncaricoId() != null) {
                specification = specification.and(buildSpecification(criteria.getIncaricoId(),
                    root -> root.join(ListaContatti_.incarico, JoinType.LEFT).get(Incarico_.id)));
            }
        }
        return specification;
    }
}
