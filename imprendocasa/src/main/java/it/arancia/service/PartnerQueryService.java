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
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.PartnerRepository;
import it.arancia.service.dto.PartnerCriteria;
import it.arancia.service.dto.PartnerDTO;
import it.arancia.service.mapper.PartnerMapper;

/**
 * Service for executing complex queries for Partner entities in the database.
 * The main input is a {@link PartnerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PartnerDTO} or a {@link Page} of {@link PartnerDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PartnerQueryService extends QueryService<Partner> {

    private final Logger log = LoggerFactory.getLogger(PartnerQueryService.class);

    private final PartnerRepository partnerRepository;

    private final PartnerMapper partnerMapper;

    public PartnerQueryService(PartnerRepository partnerRepository, PartnerMapper partnerMapper) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
    }

    /**
     * Return a {@link List} of {@link PartnerDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PartnerDTO> findByCriteria(PartnerCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Partner> specification = createSpecification(criteria);
        return partnerMapper.toDto(partnerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PartnerDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PartnerDTO> findByCriteria(PartnerCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Partner> specification = createSpecification(criteria);
        return partnerRepository.findAll(specification, page)
            .map(partnerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PartnerCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Partner> specification = createSpecification(criteria);
        return partnerRepository.count(specification);
    }

    /**
     * Function to convert PartnerCriteria to a {@link Specification}
     */
    private Specification<Partner> createSpecification(PartnerCriteria criteria) {
        Specification<Partner> specification = Specification.where(null);
        if (criteria != null) {
        		if (criteria.getFreeSearch() != null) {
	         	Specification<Partner> freeSearchSpecification = Specification.where(null);
	         	freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Partner_.cognome));
	         	freeSearchSpecification = freeSearchSpecification.or(buildStringSpecification(criteria.getFreeSearch(), Partner_.nome));
	         	specification = specification.and(freeSearchSpecification);
	        }
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Partner_.id));
            }
            if (criteria.getNome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNome(), Partner_.nome));
            }
            if (criteria.getCognome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCognome(), Partner_.cognome));
            }
            if (criteria.getCodiceFiscale() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodiceFiscale(), Partner_.codiceFiscale));
            }
            if (criteria.getTelefonoFisso() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefonoFisso(), Partner_.telefonoFisso));
            }
            if (criteria.getTelefonoCellulare() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefonoCellulare(), Partner_.telefonoCellulare));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Partner_.email));
            }
            if (criteria.getTipoIndirizzo() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoIndirizzo(), Partner_.tipoIndirizzo));
            }
            if (criteria.getIndirizzo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIndirizzo(), Partner_.indirizzo));
            }
            if (criteria.getCap() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCap(), Partner_.cap));
            }
            if (criteria.getRegione() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRegione(), Partner_.regione));
            }
            if (criteria.getProvincia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProvincia(), Partner_.provincia));
            }
            if (criteria.getCitta() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCitta(), Partner_.citta));
            }
            if (criteria.getAbilitato() != null) {
                specification = specification.and(buildSpecification(criteria.getAbilitato(), Partner_.abilitato));
            }
            if (criteria.getProfessioneId() != null) {
                specification = specification.and(buildSpecification(criteria.getProfessioneId(),
                    root -> root.join(Partner_.professione, JoinType.LEFT).get(Professione_.id)));
            }
            if (criteria.getIncaricoId() != null) {
                specification = specification.and(buildSpecification(criteria.getIncaricoId(),
                    root -> root.join(Partner_.incaricos, JoinType.LEFT).get(Incarico_.id)));
            }
        }
        return specification;
    }
    
}
