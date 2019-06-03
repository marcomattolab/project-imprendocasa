package it.arancia.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import it.arancia.security.SecurityUtils;
import it.arancia.service.IncaricoQueryService;
import it.arancia.service.IncaricoService;
import it.arancia.service.dto.IncaricoCriteria;
import it.arancia.service.dto.IncaricoDTO;
import it.arancia.service.dto.IncaricoStatutesDTO;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Incarico.
 */
@RestController
@RequestMapping("/api/incaricos")
public class IncaricoResource {
    private final Logger log = LoggerFactory.getLogger(IncaricoResource.class);
    private static final String ENTITY_NAME = "incarico";
    private final IncaricoService incaricoService;
    private final IncaricoQueryService incaricoQueryService;

    public IncaricoResource(IncaricoService incaricoService, IncaricoQueryService incaricoQueryService) {
        this.incaricoService = incaricoService;
        this.incaricoQueryService = incaricoQueryService;
    }

    /**
     * POST   : Create a new incarico.
     *
     * @param incaricoDTO the incaricoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new incaricoDTO, or with status 400 (Bad Request) if the incarico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("")
    @Timed
    public ResponseEntity<IncaricoDTO> createIncarico(@RequestBody IncaricoDTO incaricoDTO) throws URISyntaxException {
        log.debug("REST request to save Incarico : {}", incaricoDTO);
        if (incaricoDTO.getId() != null) {
            throw new BadRequestAlertException("A new incarico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IncaricoDTO result = incaricoService.save(incaricoDTO);
        return ResponseEntity.created(new URI("/api/incaricos/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT   : Updates an existing incarico.
     *
     * @param incaricoDTO the incaricoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated incaricoDTO,
     * or with status 400 (Bad Request) if the incaricoDTO is not valid,
     * or with status 500 (Internal Server Error) if the incaricoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("")
    @Timed
    public ResponseEntity<IncaricoDTO> updateIncarico(@RequestBody IncaricoDTO incaricoDTO) throws URISyntaxException {
        log.debug("REST request to update Incarico : {}", incaricoDTO);
        if (incaricoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IncaricoDTO result = incaricoService.save(incaricoDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, incaricoDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET   : get all the incaricos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of incaricos in body
     */
    @GetMapping("")
    @Timed
    public ResponseEntity<List<IncaricoDTO>> getAllIncaricos(IncaricoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Incaricos by criteria: {}", criteria);
        Page<IncaricoDTO> page = incaricoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/incaricos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /count : count all the incaricos.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the count in body
     */
    @GetMapping("/count")
    @Timed
    public ResponseEntity<Long> countIncaricos(IncaricoCriteria criteria) {
        log.debug("REST request to count Incaricos by criteria: {}", criteria);
        return ResponseEntity.ok().body(incaricoQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /:id : get the "id" incarico.
     *
     * @param id the id of the incaricoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the incaricoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    @Timed
    public ResponseEntity<IncaricoDTO> getIncarico(@PathVariable Long id) {
        log.debug("REST request to get Incarico : {}", id);
        Optional<IncaricoDTO> incaricoDTO = incaricoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(incaricoDTO);
    }

    /**
     * DELETE  /:id : delete the "id" incarico.
     *
     * @param id the id of the incaricoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/{id}")
    @Timed
    public ResponseEntity<Void> deleteIncarico(@PathVariable Long id) {
        log.debug("REST request to delete Incarico : {}", id);
        incaricoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/getPermission")
    @Timed
    public boolean retrievePermission(@RequestParam("idIncarico") Long idIncarico) {
        Optional<IncaricoDTO> incaricoDTO = incaricoService.findOne(idIncarico);
        return incaricoService.checkPermission(incaricoDTO.get());
    }


    /**
     * GET  /getStatus : to check change status of incarico.
     *
     * @param idIncarico the id of Incarico
     * @return IncaricoStatutesDTO with all statutes information
     */
    @GetMapping("/getStatus/{idIncarico}")
    @Timed
    public IncaricoStatutesDTO retrieveStatus(@PathVariable("idIncarico") Long idIncarico) {
        return incaricoService.getStatusInformation(idIncarico);
    }

    /**
     * POST  /changestatus : change status of incarico.
     *
     * @param incaricoDTO the incaricoDTO to create
     * @return the ResponseEntity with IncaricoDTO
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/changestatus")
    @Timed
    public ResponseEntity<IncaricoDTO> changestatus(@RequestBody IncaricoDTO incaricoDTO) throws URISyntaxException {
        log.debug("REST request to update status of Incarico : {}", incaricoDTO);
        if (incaricoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IncaricoDTO result = incaricoService.changeStatus(incaricoDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, incaricoDTO.getId().toString()))
                .body(result);
    }


    @GetMapping("/editAvaiable/{id}")
    @Timed
    public ResponseEntity<Void> editIncaricoAvaiable(@PathVariable Long id) {
        log.debug("REST request to edit Incarico Avaiable : {}", id);
        // Non e' possibile  modificare immobili se associati ad incarichi non in stato bozza (tranne Admin)
        if (!SecurityUtils.isCurrentUserInRoleAdmin() && !hasStatoDraft(id)) {
            throw new BadRequestAlertException(
                    "L'incarico non puo' essere modificato in quanto non in stato bozza",
                    ENTITY_NAME, "error.editIncarico.incaricoAttivoExists");
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/deleteAvaiable/{id}")
    @Timed
    public ResponseEntity<Void> deleteIncaricoAvaiable(@PathVariable Long id) {
        log.debug("REST request to delete Incarico Avaiable : {}", id);
        // Non e' possibile  rimuovere  immobili se associati ad incarichi non in stato bozza o con incarichi
        if (!hasStatoDraft(id)) {
            throw new BadRequestAlertException(
                    "L'incarico non puo' essere rimosso in quanto non in stato bozza",
                    ENTITY_NAME, "error.deleteIncarico.incaricoAttivoExists");
        }

        return ResponseEntity.ok().build();
    }

    private boolean hasStatoDraft(Long idIncarico) {
        IncaricoDTO incarico = incaricoService.findOne(idIncarico).get();
        return incarico.isStatoBozza();
    }
}
