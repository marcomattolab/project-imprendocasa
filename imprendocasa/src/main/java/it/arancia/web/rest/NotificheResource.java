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
import it.arancia.service.NotificheQueryService;
import it.arancia.service.NotificheService;
import it.arancia.service.dto.NotificheCriteria;
import it.arancia.service.dto.NotificheDTO;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Notifiche.
 */
@RestController
@RequestMapping("/api")
public class NotificheResource {
    private final Logger log = LoggerFactory.getLogger(NotificheResource.class);

    private static final String ENTITY_NAME = "notifiche";

    private final NotificheService notificheService;
    private final NotificheQueryService notificheQueryService;

    public NotificheResource(NotificheService notificheService, NotificheQueryService notificheQueryService) {
        this.notificheService = notificheService;
        this.notificheQueryService = notificheQueryService;
    }

    /**
     * POST  /notifiches : Create a new notifiche.
     *
     * @param notificheDTO the notificheDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new notificheDTO, or with status 400 (Bad Request) if the notifiche has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/notifiches")
    @Timed
    public ResponseEntity<NotificheDTO> createNotifiche(@Valid @RequestBody NotificheDTO notificheDTO) throws URISyntaxException {
        log.debug("REST request to save Notifiche : {}", notificheDTO);
        if (notificheDTO.getId() != null) {
            throw new BadRequestAlertException("A new notifiche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificheDTO result = notificheService.save(notificheDTO);
        return ResponseEntity.created(new URI("/api/notifiches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /notifiches : Updates an existing notifiche.
     *
     * @param notificheDTO the notificheDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated notificheDTO,
     * or with status 400 (Bad Request) if the notificheDTO is not valid,
     * or with status 500 (Internal Server Error) if the notificheDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/notifiches")
    @Timed
    public ResponseEntity<NotificheDTO> updateNotifiche(@Valid @RequestBody NotificheDTO notificheDTO) throws URISyntaxException {
        log.debug("REST request to update Notifiche : {}", notificheDTO);
        if (notificheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificheDTO result = notificheService.save(notificheDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, notificheDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /notifiches : get all the notifiches.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of notifiches in body
     */
    @GetMapping("/notifiches")
    @Timed
    public ResponseEntity<List<NotificheDTO>> getAllNotifiches(NotificheCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Notifiches by criteria: {}", criteria);
        Page<NotificheDTO> page = notificheQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/notifiches");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /notifiches/count : count all the notifiches.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/notifiches/count")
    @Timed
    public ResponseEntity<Long> countNotifiches(NotificheCriteria criteria) {
        log.debug("REST request to count Notifiches by criteria: {}", criteria);
        return ResponseEntity.ok().body(notificheQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /notifiches/:id : get the "id" notifiche.
     *
     * @param id the id of the notificheDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the notificheDTO, or with status 404 (Not Found)
     */
    @GetMapping("/notifiches/{id}")
    @Timed
    public ResponseEntity<NotificheDTO> getNotifiche(@PathVariable Long id) {
        log.debug("REST request to get Notifiche : {}", id);
        Optional<NotificheDTO> notificheDTO = notificheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificheDTO);
    }

    /**
     * DELETE  /notifiches/:id : delete the "id" notifiche.
     *
     * @param id the id of the notificheDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/notifiches/{id}")
    @Timed
    public ResponseEntity<Void> deleteNotifiche(@PathVariable Long id) {
        log.debug("REST request to delete Notifiche : {}", id);
        notificheService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
