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

import it.arancia.domain.Notifiche;
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.NotificheRepository;
import it.arancia.service.dto.NotificheCriteria;
import it.arancia.service.dto.NotificheDTO;
import it.arancia.service.mapper.NotificheMapper;

/**
 * Service for executing complex queries for Notifiche entities in the database.
 * The main input is a {@link NotificheCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NotificheDTO} or a {@link Page} of {@link NotificheDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NotificheQueryService extends QueryService<Notifiche> {

    private final Logger log = LoggerFactory.getLogger(NotificheQueryService.class);

    private final NotificheRepository notificheRepository;

    private final NotificheMapper notificheMapper;

    public NotificheQueryService(NotificheRepository notificheRepository, NotificheMapper notificheMapper) {
        this.notificheRepository = notificheRepository;
        this.notificheMapper = notificheMapper;
    }

    /**
     * Return a {@link List} of {@link NotificheDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NotificheDTO> findByCriteria(NotificheCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Notifiche> specification = createSpecification(criteria);
        return notificheMapper.toDto(notificheRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NotificheDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NotificheDTO> findByCriteria(NotificheCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Notifiche> specification = createSpecification(criteria);
        return notificheRepository.findAll(specification, page)
            .map(notificheMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NotificheCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Notifiche> specification = createSpecification(criteria);
        return notificheRepository.count(specification);
    }

    /**
     * Function to convert NotificheCriteria to a {@link Specification}
     */
    private Specification<Notifiche> createSpecification(NotificheCriteria criteria) {
        Specification<Notifiche> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Notifiche_.id));
            }
            if (criteria.getCanaleNotifica() != null) {
                specification = specification.and(buildSpecification(criteria.getCanaleNotifica(), Notifiche_.canaleNotifica));
            }
            if (criteria.getTipoNotifica() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoNotifica(), Notifiche_.tipoNotifica));
            }
            if (criteria.getCategoriaNotifica() != null) {
                specification = specification.and(buildSpecification(criteria.getCategoriaNotifica(), Notifiche_.categoriaNotifica));
            }
            if (criteria.getOggettoNotifica() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOggettoNotifica(), Notifiche_.oggettoNotifica));
            }
            if (criteria.getNumeroDestinatari() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroDestinatari(), Notifiche_.numeroDestinatari));
            }
            if (criteria.getEsitoNotifica() != null) {
                specification = specification.and(buildSpecification(criteria.getEsitoNotifica(), Notifiche_.esitoNotifica));
            }
        }
        return specification;
    }
}
