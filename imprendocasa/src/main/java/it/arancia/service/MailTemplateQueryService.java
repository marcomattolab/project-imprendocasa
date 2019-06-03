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

import it.arancia.domain.MailTemplate;
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.MailTemplateRepository;
import it.arancia.service.dto.MailTemplateCriteria;
import it.arancia.service.dto.MailTemplateDTO;
import it.arancia.service.mapper.MailTemplateMapper;

/**
 * Service for executing complex queries for MailTemplate entities in the database.
 * The main input is a {@link MailTemplateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MailTemplateDTO} or a {@link Page} of {@link MailTemplateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MailTemplateQueryService extends QueryService<MailTemplate> {

    private final Logger log = LoggerFactory.getLogger(MailTemplateQueryService.class);

    private final MailTemplateRepository mailTemplateRepository;

    private final MailTemplateMapper mailTemplateMapper;

    public MailTemplateQueryService(MailTemplateRepository mailTemplateRepository, MailTemplateMapper mailTemplateMapper) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.mailTemplateMapper = mailTemplateMapper;
    }

    /**
     * Return a {@link List} of {@link MailTemplateDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MailTemplateDTO> findByCriteria(MailTemplateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MailTemplate> specification = createSpecification(criteria);
        return mailTemplateMapper.toDto(mailTemplateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MailTemplateDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MailTemplateDTO> findByCriteria(MailTemplateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MailTemplate> specification = createSpecification(criteria);
        return mailTemplateRepository.findAll(specification, page)
            .map(mailTemplateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MailTemplateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MailTemplate> specification = createSpecification(criteria);
        return mailTemplateRepository.count(specification);
    }

    /**
     * Function to convert MailTemplateCriteria to a {@link Specification}
     */
    private Specification<MailTemplate> createSpecification(MailTemplateCriteria criteria) {
        Specification<MailTemplate> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MailTemplate_.id));
            }
            if (criteria.getCodice() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodice(), MailTemplate_.codice));
            }
            if (criteria.getCategoria() != null) {
                specification = specification.and(buildSpecification(criteria.getCategoria(), MailTemplate_.categoria));
            }
            if (criteria.getOggetto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOggetto(), MailTemplate_.oggetto));
            }
            if (criteria.getAbilitato() != null) {
                specification = specification.and(buildSpecification(criteria.getAbilitato(), MailTemplate_.abilitato));
            }
        }
        return specification;
    }
}
