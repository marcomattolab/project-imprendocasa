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

import it.arancia.domain.AppSettings;
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.AppSettingsRepository;
import it.arancia.service.dto.AppSettingsCriteria;
import it.arancia.service.dto.AppSettingsDTO;
import it.arancia.service.mapper.AppSettingsMapper;

/**
 * Service for executing complex queries for AppSettings entities in the database.
 * The main input is a {@link AppSettingsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AppSettingsDTO} or a {@link Page} of {@link AppSettingsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AppSettingsQueryService extends QueryService<AppSettings> {

    private final Logger log = LoggerFactory.getLogger(AppSettingsQueryService.class);

    private final AppSettingsRepository appSettingsRepository;

    private final AppSettingsMapper appSettingsMapper;

    public AppSettingsQueryService(AppSettingsRepository appSettingsRepository, AppSettingsMapper appSettingsMapper) {
        this.appSettingsRepository = appSettingsRepository;
        this.appSettingsMapper = appSettingsMapper;
    }

    /**
     * Return a {@link List} of {@link AppSettingsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AppSettingsDTO> findByCriteria(AppSettingsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AppSettings> specification = createSpecification(criteria);
        return appSettingsMapper.toDto(appSettingsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AppSettingsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AppSettingsDTO> findByCriteria(AppSettingsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AppSettings> specification = createSpecification(criteria);
        return appSettingsRepository.findAll(specification, page)
            .map(appSettingsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AppSettingsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AppSettings> specification = createSpecification(criteria);
        return appSettingsRepository.count(specification);
    }

    /**
     * Function to convert AppSettingsCriteria to a {@link Specification}
     */
    private Specification<AppSettings> createSpecification(AppSettingsCriteria criteria) {
        Specification<AppSettings> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AppSettings_.id));
            }
            if (criteria.getCategoria() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCategoria(), AppSettings_.categoria));
            }
            if (criteria.getChiave() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChiave(), AppSettings_.chiave));
            }
            if (criteria.getValore() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValore(), AppSettings_.valore));
            }
            if (criteria.getAbilitato() != null) {
                specification = specification.and(buildSpecification(criteria.getAbilitato(), AppSettings_.abilitato));
            }
        }
        return specification;
    }
}
