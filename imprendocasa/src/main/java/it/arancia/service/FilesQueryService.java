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

import it.arancia.domain.Files;
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.FilesRepository;
import it.arancia.service.dto.FilesCriteria;
import it.arancia.service.dto.FilesDTO;
import it.arancia.service.mapper.FilesMapper;

/**
 * Service for executing complex queries for Files entities in the database.
 * The main input is a {@link FilesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FilesDTO} or a {@link Page} of {@link FilesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FilesQueryService extends QueryService<Files> {

    private final Logger log = LoggerFactory.getLogger(FilesQueryService.class);

    private final FilesRepository filesRepository;
    private final FilesMapper filesMapper;

    public FilesQueryService(FilesRepository filesRepository, FilesMapper filesMapper) {
        this.filesRepository = filesRepository;
        this.filesMapper = filesMapper;
    }

    /**
     * Return a {@link List} of {@link FilesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FilesDTO> findByCriteria(FilesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Files> specification = createSpecification(criteria);
        return filesMapper.toDto(filesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FilesDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FilesDTO> findByCriteria(FilesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Files> specification = createSpecification(criteria);
        return filesRepository.findAll(specification, page)
            .map(filesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FilesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Files> specification = createSpecification(criteria);
        return filesRepository.count(specification);
    }

    /**
     * Function to convert FilesCriteria to a {@link Specification}
     */
    private Specification<Files> createSpecification(FilesCriteria criteria) {
        Specification<Files> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Files_.id));
            }
            if (criteria.getNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNome(), Files_.nome));
            }
            if (criteria.getDimensione() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDimensione(), Files_.dimensione));
            }
            if (criteria.getEstensione() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEstensione(), Files_.estensione));
            }
            if (criteria.getImmobileId() != null) {
                specification = specification.and(buildSpecification(criteria.getImmobileId(),
                    root -> root.join(Files_.immobile, JoinType.LEFT).get(Immobile_.id)));
            }
        }
        return specification;
    }
}
