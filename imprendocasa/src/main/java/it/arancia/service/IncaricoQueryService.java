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
import it.arancia.domain.*; // for static metamodels
import it.arancia.repository.IncaricoRepository;
import it.arancia.security.AuthoritiesConstants;
import it.arancia.security.SecurityUtils;
import it.arancia.service.dto.IncaricoCriteria;
import it.arancia.service.dto.IncaricoDTO;
import it.arancia.service.mapper.IncaricoMapper;
import it.arancia.service.util.EntityProfilerUtils;


/**
 * Service for executing complex queries for Incarico entities in the database.
 * The main input is a {@link IncaricoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link IncaricoDTO} or a {@link Page} of {@link IncaricoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class IncaricoQueryService extends QueryService<Incarico> {
    private final Logger log = LoggerFactory.getLogger(IncaricoQueryService.class);
    private final IncaricoRepository incaricoRepository;
    private final IncaricoMapper incaricoMapper;
    
    private final EntityProfilerUtils entityProfilerUtils;
    
    private boolean securityEnabled = true;

    public IncaricoQueryService(IncaricoRepository incaricoRepository, IncaricoMapper incaricoMapper, EntityProfilerUtils entityProfilerUtils) {
        this.incaricoRepository = incaricoRepository;
        this.incaricoMapper = incaricoMapper;
        this.entityProfilerUtils = entityProfilerUtils;
    }

    /**
     * Return a {@link List} of {@link IncaricoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<IncaricoDTO> findByCriteria(IncaricoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Incarico> specification = createSpecification(criteria);
        List<IncaricoDTO> incarichi = incaricoMapper.toDto(incaricoRepository.findAll(specification));
        entityProfilerUtils.completeInfoEditableDTO(incarichi);
        return incarichi;
    }

    /**
     * Return a {@link Page} of {@link IncaricoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<IncaricoDTO> findByCriteria(IncaricoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Incarico> specification = createSpecification(criteria);
         Page<IncaricoDTO> incarichi = incaricoRepository.findAll(specification, page).map(incaricoMapper::toDto);
         entityProfilerUtils.completeInfoEditableDTO(incarichi);
         return incarichi;
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(IncaricoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Incarico> specification = createSpecification(criteria);
        return incaricoRepository.count(specification);
    }

    /**
     * Function to convert IncaricoCriteria to a {@link Specification}
     */
    private Specification<Incarico> createSpecification(IncaricoCriteria criteria) {
        Specification<Incarico> specification = Specification.where(null);
        
        if (securityEnabled){
			boolean isAutenticated = SecurityUtils.isAuthenticated();
			boolean isAdmin = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN) || SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN);
			boolean isAgent = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT);
			boolean isAgentPlus = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT_PLUS);
			boolean isOperator = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.OPERATOR);
			Optional<String> loggedUser = SecurityUtils.getCurrentUserLogin();
			String userName = loggedUser.orElse(null);

			if(isAutenticated && !isAdmin && !isAgentPlus && !isOperator && isAgent) {
				// SE NON ADMIN 
				// FILTRO PER AGENTE = user
				StringFilter currentUser = new StringFilter();
				currentUser.setEquals(userName);
				specification = specification.and(buildStringSpecification(currentUser, Incarico_.agente));
				// FILTRO PER FLAG_OSCURA = false (o null)
				BooleanFilter oscuraIncaricoFilter = new BooleanFilter();
				oscuraIncaricoFilter.setIn(Arrays.asList(false, null));
				specification = specification.and(buildSpecification(oscuraIncaricoFilter, Incarico_.oscuraIncarico));
			}
			if(!isAutenticated || (isAutenticated && !(isAdmin || isAgentPlus || isOperator || isAgent)) ) {
				StringFilter currentUser = new StringFilter();
				currentUser.setEquals("-1");
				specification = specification.and(buildStringSpecification(currentUser, Incarico_.agente));
			}
		}
        
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Incarico_.id));
            }
            if (criteria.getRiferimento() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRiferimento(), Incarico_.riferimento));
            }
            if (criteria.getTipo() != null) {
                specification = specification.and(buildSpecification(criteria.getTipo(), Incarico_.tipo));
            }
            if (criteria.getCategoriaIncarico() != null) {
                specification = specification.and(buildSpecification(criteria.getCategoriaIncarico(), Incarico_.categoriaIncarico));
            }
            if (criteria.getStato() != null) {
                specification = specification.and(buildSpecification(criteria.getStato(), Incarico_.stato));
            }
            if (criteria.getDataScadenza() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataScadenza(), Incarico_.dataScadenza));
            }
            if (criteria.getAgente() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgente(), Incarico_.agente));
            }
            if (criteria.getAgenteDiContatto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAgenteDiContatto(), Incarico_.agenteDiContatto));
            }
            if (criteria.getDataContatto() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataContatto(), Incarico_.dataContatto));
            }
            if (criteria.getDataAlertFiscali() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataAlertFiscali(), Incarico_.dataAlertFiscali));
            }
            if (criteria.getAlertFiscali() != null) {
                specification = specification.and(buildSpecification(criteria.getAlertFiscali(), Incarico_.alertFiscali));
            }
            if (criteria.getRicorrenzaAlertFiscali() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRicorrenzaAlertFiscali(), Incarico_.ricorrenzaAlertFiscali));
            }
            if (criteria.getAlertCortesia() != null) {
                specification = specification.and(buildSpecification(criteria.getAlertCortesia(), Incarico_.alertCortesia));
            }
            if (criteria.getDataAlertCortesia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataAlertCortesia(), Incarico_.dataAlertCortesia));
            }
            if (criteria.getRicorrenzaAlertCortesia() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRicorrenzaAlertCortesia(), Incarico_.ricorrenzaAlertCortesia));
            }
            if (criteria.getAlertRicorrenza() != null) {
                specification = specification.and(buildSpecification(criteria.getAlertRicorrenza(), Incarico_.alertRicorrenza));
            }
            if (criteria.getDataAlertRicorrenza() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataAlertRicorrenza(), Incarico_.dataAlertRicorrenza));
            }
            if (criteria.getRicorrenzaAlertRicorrenza() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRicorrenzaAlertRicorrenza(), Incarico_.ricorrenzaAlertRicorrenza));
            }
            if (criteria.getPrivacy() != null) {
                specification = specification.and(buildSpecification(criteria.getPrivacy(), Incarico_.privacy));
            }
            if (criteria.getAntiriciclaggio() != null) {
                specification = specification.and(buildSpecification(criteria.getAntiriciclaggio(), Incarico_.antiriciclaggio));
            }
            if (criteria.getPrivacyAcquirenti() != null) {
                specification = specification.and(buildSpecification(criteria.getPrivacyAcquirenti(), Incarico_.privacyAcquirenti));
            }
            if (criteria.getAntiriciclaggioAcquirenti() != null) {
                specification = specification.and(buildSpecification(criteria.getAntiriciclaggioAcquirenti(), Incarico_.antiriciclaggioAcquirenti));
            }
            if (criteria.getPrezzoRichiesta() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrezzoRichiesta(), Incarico_.prezzoRichiesta));
            }
            if (criteria.getPrezzoMinimo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrezzoMinimo(), Incarico_.prezzoMinimo));
            }
            if (criteria.getPrezzoAcquisto() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrezzoAcquisto(), Incarico_.prezzoAcquisto));
            }
            if (criteria.getImportoProvvigione() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImportoProvvigione(), Incarico_.importoProvvigione));
            }
            if (criteria.getImportoProvvigioneAcquirenti() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getImportoProvvigioneAcquirenti(), Incarico_.importoProvvigioneAcquirenti));
            }
            if (criteria.getOscuraIncarico() != null) {
                specification = specification.and(buildSpecification(criteria.getOscuraIncarico(), Incarico_.oscuraIncarico));
            }
            if (criteria.getFlagLocato() != null) {
                specification = specification.and(buildSpecification(criteria.getFlagLocato(), Incarico_.flagLocato));
            }
            if (criteria.getNomeConduttore() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNomeConduttore(), Incarico_.nomeConduttore));
            }
            if (criteria.getCognomeConduttore() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCognomeConduttore(), Incarico_.cognomeConduttore));
            }
            if (criteria.getCanoneCorrisposto() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCanoneCorrisposto(), Incarico_.canoneCorrisposto));
            }
            if (criteria.getTelefonoConduttore() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefonoConduttore(), Incarico_.telefonoConduttore));
            }
            if (criteria.getDataInizioLocazione() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataInizioLocazione(), Incarico_.dataInizioLocazione));
            }
            if (criteria.getDataFineLocazione() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataFineLocazione(), Incarico_.dataFineLocazione));
            }
            if (criteria.getListaContattiId() != null) {
                specification = specification.and(buildSpecification(criteria.getListaContattiId(),
                    root -> root.join(Incarico_.listaContattis, JoinType.LEFT).get(ListaContatti_.id)));
            }
            if (criteria.getImmobileId() != null) {
                specification = specification.and(buildSpecification(criteria.getImmobileId(),
                    root -> root.join(Incarico_.immobile, JoinType.LEFT).get(Immobile_.id)));
            }
            if (criteria.getPartnerId() != null) {
                specification = specification.and(buildSpecification(criteria.getPartnerId(),
                    root -> root.join(Incarico_.partners, JoinType.LEFT).get(Partner_.id)));
            }
            if (criteria.getCommittenteId() != null) {
                specification = specification.and(buildSpecification(criteria.getCommittenteId(),
                    root -> root.join(Incarico_.committentes, JoinType.LEFT).get(Cliente_.id)));
            }
            if (criteria.getProponenteId() != null) {
                specification = specification.and(buildSpecification(criteria.getProponenteId(),
                    root -> root.join(Incarico_.proponentes, JoinType.LEFT).get(Cliente_.id)));
            }
            if (criteria.getAcquirenteLocatarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getAcquirenteLocatarioId(),
                    root -> root.join(Incarico_.acquirenteLocatarios, JoinType.LEFT).get(Cliente_.id)));
            }
            if (criteria.getSegnalatoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getSegnalatoreId(),
                    root -> root.join(Incarico_.segnalatores, JoinType.LEFT).get(Cliente_.id)));
            }
        }
        return specification;
    }
}
