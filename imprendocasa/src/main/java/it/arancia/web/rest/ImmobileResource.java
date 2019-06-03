package it.arancia.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import it.arancia.security.SecurityUtils;
import it.arancia.service.ImmobileQueryService;
import it.arancia.service.ImmobileService;
import it.arancia.service.IncaricoService;
import it.arancia.service.dto.ImmobileCriteria;
import it.arancia.service.dto.ImmobileDTO;
import it.arancia.service.dto.IncaricoDTO;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Immobile.
 */
@RestController
@RequestMapping("/api")
public class ImmobileResource {
	private final Logger log = LoggerFactory.getLogger(ImmobileResource.class);

	private static final String ENTITY_NAME = "immobile";

	private final ImmobileService immobileService;
	private final ImmobileQueryService immobileQueryService;
	private final IncaricoService incaricoService;

	public ImmobileResource(ImmobileService immobileService, ImmobileQueryService immobileQueryService,
			IncaricoService incaricoService) {
		this.immobileService = immobileService;
		this.immobileQueryService = immobileQueryService;
		this.incaricoService = incaricoService;
	}

	/**
	 * POST /immobiles : Create a new immobile.
	 *
	 * @param immobileDTO the immobileDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         immobileDTO, or with status 400 (Bad Request) if the immobile has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/immobiles")
	@Timed
	public ResponseEntity<ImmobileDTO> createImmobile(@Valid @RequestBody ImmobileDTO immobileDTO)
			throws URISyntaxException {
		log.debug("REST request to save Immobile : {}", immobileDTO);
		if (immobileDTO.getId() != null) {
			throw new BadRequestAlertException("A new immobile cannot already have an ID", ENTITY_NAME, "idexists");
		}
		ImmobileDTO result = immobileService.save(immobileDTO);
		return ResponseEntity.created(new URI("/api/immobiles/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /immobiles : Updates an existing immobile.
	 *
	 * @param immobileDTO the immobileDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         immobileDTO, or with status 400 (Bad Request) if the immobileDTO is
	 *         not valid, or with status 500 (Internal Server Error) if the
	 *         immobileDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/immobiles")
	@Timed
	public ResponseEntity<ImmobileDTO> updateImmobile(@Valid @RequestBody ImmobileDTO immobileDTO)
			throws URISyntaxException {
		log.debug("REST request to update Immobile : {}", immobileDTO);
		if (immobileDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		ImmobileDTO result = immobileService.save(immobileDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, immobileDTO.getId().toString())).body(result);
	}

	/**
	 * GET /immobiles : get all the immobiles.
	 *
	 * @param pageable the pagination information
	 * @param criteria the criterias which the requested entities should match
	 * @return the ResponseEntity with status 200 (OK) and the list of immobiles in
	 *         body
	 */
	@GetMapping("/immobiles")
	@Timed
	public ResponseEntity<List<ImmobileDTO>> getAllImmobiles(ImmobileCriteria criteria, Pageable pageable) {
		log.debug("REST request to get Immobiles by criteria: {}", criteria);
		Page<ImmobileDTO> page = immobileQueryService.findByCriteria(criteria, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/immobiles");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

//    /**
//     * GET  /immobiles : get all the immobiles by currentUser.
//     *
//     * @param pageable the pagination information
//     * @param criteria the criterias which the requested entities should match
//     * @return the ResponseEntity with status 200 (OK) and the list of immobiles in body
//     */
//    @GetMapping("/immobiles/currentUser")
//    @Timed
//    @Deprecated
//    public ResponseEntity<List<ImmobileDTO>> getAllImmobilesByCurrentUser(ImmobileCriteria criteria, Pageable pageable) {
//        log.debug("REST request to get Immobiles by criteria: {}", criteria);
//        String currentUser =  SecurityUtils.getCurrentUserLogin().get();
//        StringFilter current = new StringFilter();
//        log.debug("userrr" + currentUser);
//        if(!org.apache.commons.lang3.StringUtils.equals(currentUser, ADMIN) || !org.apache.commons.lang3.StringUtils.equals(currentUser, SUPER)) {
//       	 	current.setEquals(currentUser);
//            criteria.setCurrentUser(current);	
//       }
//        Page<ImmobileDTO> page = immobileQueryService.findByCriteria(criteria, pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/immobiles");
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

	/**
	 * GET /immobiles/count : count all the immobiles.
	 *
	 * @param criteria the criterias which the requested entities should match
	 * @return the ResponseEntity with status 200 (OK) and the count in body
	 */
	@GetMapping("/immobiles/count")
	@Timed
	public ResponseEntity<Long> countImmobiles(ImmobileCriteria criteria) {
		log.debug("REST request to count Immobiles by criteria: {}", criteria);
		return ResponseEntity.ok().body(immobileQueryService.countByCriteria(criteria));
	}

	/**
	 * GET /immobiles/:id : get the "id" immobile.
	 *
	 * @param id the id of the immobileDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         immobileDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/immobiles/{id}")
	@Timed
	public ResponseEntity<ImmobileDTO> getImmobile(@PathVariable Long id) {
		log.debug("REST request to get Immobile : {}", id);
		Optional<ImmobileDTO> immobileDTO = immobileService.findOne(id);
		return ResponseUtil.wrapOrNotFound(immobileDTO);
	}

	/**
	 * DELETE /immobiles/:id : delete the "id" immobile.
	 *
	 * @param id the id of the immobileDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/immobiles/{id}")
	@Timed
	public ResponseEntity<Void> deleteImmobile(@PathVariable Long id) {
		log.debug("REST request to delete Immobile : {}", id);
		immobileService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	@GetMapping("/immobiles/deleteAvaiable/{id}")
	@Timed
	public ResponseEntity<Void> deleteImmobileAvaiable(@PathVariable Long id) {
		log.debug("REST request to delete Immobile Avaiable : {}", id);
		List<IncaricoDTO> incarichiByImmobile = incaricoService.findAllByImmobile(id);
		// Non e' possibile  rimuovere  immobili se associati ad incarichi non in stato bozza o con incarichi
		if (!CollectionUtils.isEmpty(incarichiByImmobile)) {
			if (hasIncarichiAttivi(incarichiByImmobile)) {
				throw new BadRequestAlertException(
						"L'immobile non puo' essere rimosso in quanto legato ad un incarico non in stato bozza",
						ENTITY_NAME, "error.deleteImmobile.incaricoAttivoExists");
			} else {
				throw new BadRequestAlertException(
						"L'immobile non puo' essere rimosso in quanto legato ad un incarico. E' necessario rimuovere rima l'incarico.",
						ENTITY_NAME, "error.deleteImmobile.incaricoExists");
			}
		}

		return ResponseEntity.ok().build();
	}

	@GetMapping("/immobiles/editAvaiable/{id}")
	@Timed
	public ResponseEntity<Void> editImmobileAvaiable(@PathVariable Long id) {
		log.debug("REST request to edit Immobile Avaiable : {}", id);
		// Non e' possibile  modificare immobili se associati ad incarichi non in stato bozza (tranne Admin)
		if (!SecurityUtils.isCurrentUserInRoleAdmin() && hasIncarichiAttivi(id)) {
			throw new BadRequestAlertException(
					"L'immobile non puo' essere modificato in quanto legato ad un incarico non in stato bozza",
					ENTITY_NAME, "error.editImmobile.incaricoAttivoExists");
		}
		return ResponseEntity.ok().build();
	}

	private boolean hasIncarichiAttivi(Long idImmobile) {
		boolean hasIncarichiAttivi = false;
		log.debug("Check has Incarichi non in stato bozza for Immobile : {}", idImmobile);
		final Long countIncarico = incaricoService.countNonDraftByImmobile(idImmobile);
		if (countIncarico != null && countIncarico.compareTo(0l) > 0) {
			hasIncarichiAttivi = true;
			log.debug("Immobile {} has {} incarichi non in stato bozza", idImmobile, countIncarico);
		}
		return hasIncarichiAttivi;
	}

	private boolean hasIncarichiAttivi(List<IncaricoDTO> incarichiByImmobile) {
		boolean hasIncarichiAttivi = false;
		for (IncaricoDTO incaricoDTO : incarichiByImmobile) {
			if (incaricoDTO.getStato() != null && !incaricoDTO.isStatoBozza()) {
				hasIncarichiAttivi = true;
				break;
			}
		}
		return hasIncarichiAttivi;
	}

}
