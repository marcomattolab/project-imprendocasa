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
import it.arancia.service.ProfessioneQueryService;
import it.arancia.service.ProfessioneService;
import it.arancia.service.dto.ProfessioneCriteria;
import it.arancia.service.dto.ProfessioneDTO;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Professione.
 */
@RestController
@RequestMapping("/api")
public class ProfessioneResource {
    private final Logger log = LoggerFactory.getLogger(ProfessioneResource.class);

    private static final String ENTITY_NAME = "professione";

    private final ProfessioneService professioneService;
    private final ProfessioneQueryService professioneQueryService;

    public ProfessioneResource(ProfessioneService professioneService, ProfessioneQueryService professioneQueryService) {
        this.professioneService = professioneService;
        this.professioneQueryService = professioneQueryService;
    }

    /**
     * POST  /professiones : Create a new professione.
     *
     * @param professioneDTO the professioneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new professioneDTO, or with status 400 (Bad Request) if the professione has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/professiones")
    @Timed
    public ResponseEntity<ProfessioneDTO> createProfessione(@Valid @RequestBody ProfessioneDTO professioneDTO) throws URISyntaxException {
        log.debug("REST request to save Professione : {}", professioneDTO);
        if (professioneDTO.getId() != null) {
            throw new BadRequestAlertException("A new professione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfessioneDTO result = professioneService.save(professioneDTO);
        return ResponseEntity.created(new URI("/api/professiones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /professiones : Updates an existing professione.
     *
     * @param professioneDTO the professioneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated professioneDTO,
     * or with status 400 (Bad Request) if the professioneDTO is not valid,
     * or with status 500 (Internal Server Error) if the professioneDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/professiones")
    @Timed
    public ResponseEntity<ProfessioneDTO> updateProfessione(@Valid @RequestBody ProfessioneDTO professioneDTO) throws URISyntaxException {
        log.debug("REST request to update Professione : {}", professioneDTO);
        if (professioneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfessioneDTO result = professioneService.save(professioneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, professioneDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /professiones : get all the professiones.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of professiones in body
     */
    @GetMapping("/professiones")
    @Timed
    public ResponseEntity<List<ProfessioneDTO>> getAllProfessiones(ProfessioneCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Professiones by criteria: {}", criteria);
        Page<ProfessioneDTO> page = professioneQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/professiones");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /professiones/count : count all the professiones.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/professiones/count")
    @Timed
    public ResponseEntity<Long> countProfessiones(ProfessioneCriteria criteria) {
        log.debug("REST request to count Professiones by criteria: {}", criteria);
        return ResponseEntity.ok().body(professioneQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /professiones/:id : get the "id" professione.
     *
     * @param id the id of the professioneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the professioneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/professiones/{id}")
    @Timed
    public ResponseEntity<ProfessioneDTO> getProfessione(@PathVariable Long id) {
        log.debug("REST request to get Professione : {}", id);
        Optional<ProfessioneDTO> professioneDTO = professioneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(professioneDTO);
    }

    /**
     * DELETE  /professiones/:id : delete the "id" professione.
     *
     * @param id the id of the professioneDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/professiones/{id}")
    @Timed
    public ResponseEntity<Void> deleteProfessione(@PathVariable Long id) {
        log.debug("REST request to delete Professione : {}", id);
        professioneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
