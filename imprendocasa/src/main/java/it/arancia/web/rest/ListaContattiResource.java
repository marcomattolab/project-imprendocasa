package it.arancia.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.arancia.service.ListaContattiService;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.web.rest.util.PaginationUtil;
import it.arancia.service.dto.ListaContattiDTO;
import it.arancia.service.dto.ListaContattiExtDTO;
import it.arancia.service.dto.ListaContattiCriteria;
import it.arancia.service.ListaContattiQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/**
 * REST controller for managing ListaContatti.
 */
@RestController
@RequestMapping("/api")
public class ListaContattiResource {
    private final Logger log = LoggerFactory.getLogger(ListaContattiResource.class);

    private static final String ENTITY_NAME = "listaContatti";

    private final ListaContattiService listaContattiService;
    private final ListaContattiQueryService listaContattiQueryService;

    public ListaContattiResource(ListaContattiService listaContattiService, ListaContattiQueryService listaContattiQueryService) {
        this.listaContattiService = listaContattiService;
        this.listaContattiQueryService = listaContattiQueryService;
    }

    /**
     * POST  /lista-contattis : Create a new listaContatti.
     *
     * @param listaContattiDTO the listaContattiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new listaContattiDTO, or with status 400 (Bad Request) if the listaContatti has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/lista-contattis")
    @Timed
    public ResponseEntity<ListaContattiDTO> createListaContatti(@Valid @RequestBody ListaContattiDTO listaContattiDTO) throws URISyntaxException {
        log.debug("REST request to save ListaContatti : {}", listaContattiDTO);
        if (listaContattiDTO.getId() != null) {
            throw new BadRequestAlertException("A new listaContatti cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListaContattiDTO result = listaContattiService.save(listaContattiDTO);
        return ResponseEntity.created(new URI("/api/lista-contattis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /lista-contattis : Updates an existing listaContatti.
     *
     * @param listaContattiDTO the listaContattiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated listaContattiDTO,
     * or with status 400 (Bad Request) if the listaContattiDTO is not valid,
     * or with status 500 (Internal Server Error) if the listaContattiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/lista-contattis")
    @Timed
    public ResponseEntity<ListaContattiDTO> updateListaContatti(@Valid @RequestBody ListaContattiDTO listaContattiDTO) throws URISyntaxException {
        log.debug("REST request to update ListaContatti : {}", listaContattiDTO);
        if (listaContattiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListaContattiDTO result = listaContattiService.save(listaContattiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, listaContattiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /lista-contattis : get all the listaContattis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of listaContattis in body
     */
    @GetMapping("/lista-contattis")
    @Timed
    public ResponseEntity<List<ListaContattiDTO>> getAllListaContattis(ListaContattiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ListaContattis by criteria: {}", criteria);
        Page<ListaContattiDTO> page = listaContattiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/lista-contattis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /lista-contattis/count : count all the listaContattis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/lista-contattis/count")
    @Timed
    public ResponseEntity<Long> countListaContattis(ListaContattiCriteria criteria) {
        log.debug("REST request to count ListaContattis by criteria: {}", criteria);
        return ResponseEntity.ok().body(listaContattiQueryService.countByCriteria(criteria));
    }
    
    
    
    /**
     * GET  /lista-contattis-ext : get all the ListaContattiExtDTO.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of listaContattis in body
     */
    @GetMapping("/lista-contattis-ext")
    @Timed
    public ResponseEntity<List<ListaContattiExtDTO>> getAllListaContattiExt(ListaContattiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ListaContattisExt by criteria: {}", criteria);
        Page<ListaContattiExtDTO> page = listaContattiQueryService.findExtByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/lista-contattis-ext");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /lista-contattis/:id : get the "id" listaContatti.
     *
     * @param id the id of the listaContattiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the listaContattiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/lista-contattis/{id}")
    @Timed
    public ResponseEntity<ListaContattiDTO> getListaContatti(@PathVariable Long id) {
        log.debug("REST request to get ListaContatti : {}", id);
        Optional<ListaContattiDTO> listaContattiDTO = listaContattiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listaContattiDTO);
    }

    /**
     * DELETE  /lista-contattis/:id : delete the "id" listaContatti.
     *
     * @param id the id of the listaContattiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/lista-contattis/{id}")
    @Timed
    public ResponseEntity<Void> deleteListaContatti(@PathVariable Long id) {
        log.debug("REST request to delete ListaContatti : {}", id);
        listaContattiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
