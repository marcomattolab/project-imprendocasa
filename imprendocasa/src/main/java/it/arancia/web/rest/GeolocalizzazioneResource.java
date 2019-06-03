package it.arancia.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import it.arancia.service.GeolocalizzazioneQueryService;
import it.arancia.service.GeolocalizzazioneService;
import it.arancia.service.dto.GeolocalizzazioneCriteria;
import it.arancia.service.dto.GeolocalizzazioneDTO;
import it.arancia.service.dto.GeolocalizzazioneExtendedDTO;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Geolocalizzazione.
 */
@RestController
@RequestMapping("/api")
public class GeolocalizzazioneResource {
    private final Logger log = LoggerFactory.getLogger(GeolocalizzazioneResource.class);

    private static final String ENTITY_NAME = "geolocalizzazione";

    private final GeolocalizzazioneService geolocalizzazioneService;
    private final GeolocalizzazioneQueryService geolocalizzazioneQueryService;
    

    public GeolocalizzazioneResource(GeolocalizzazioneService geolocalizzazioneService, GeolocalizzazioneQueryService geolocalizzazioneQueryService) {
        this.geolocalizzazioneService = geolocalizzazioneService;
        this.geolocalizzazioneQueryService = geolocalizzazioneQueryService;
    }

    /**
     * POST  /geolocalizzaziones : Create a new geolocalizzazione.
     *
     * @param geolocalizzazioneDTO the geolocalizzazioneDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new geolocalizzazioneDTO, or with status 400 (Bad Request) if the geolocalizzazione has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/geolocalizzaziones")
    @Timed
    public ResponseEntity<GeolocalizzazioneDTO> createGeolocalizzazione(@RequestBody GeolocalizzazioneDTO geolocalizzazioneDTO) throws URISyntaxException {
        log.debug("REST request to save Geolocalizzazione : {}", geolocalizzazioneDTO);
        if (geolocalizzazioneDTO.getId() != null) {
            throw new BadRequestAlertException("A new geolocalizzazione cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GeolocalizzazioneDTO result = geolocalizzazioneService.save(geolocalizzazioneDTO);
        return ResponseEntity.created(new URI("/api/geolocalizzaziones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /geolocalizzaziones : Updates an existing geolocalizzazione.
     *
     * @param geolocalizzazioneDTO the geolocalizzazioneDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated geolocalizzazioneDTO,
     * or with status 400 (Bad Request) if the geolocalizzazioneDTO is not valid,
     * or with status 500 (Internal Server Error) if the geolocalizzazioneDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/geolocalizzaziones")
    @Timed
    public ResponseEntity<GeolocalizzazioneDTO> updateGeolocalizzazione(@RequestBody GeolocalizzazioneDTO geolocalizzazioneDTO) throws URISyntaxException {
        log.debug("REST request to update Geolocalizzazione : {}", geolocalizzazioneDTO);
        if (geolocalizzazioneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GeolocalizzazioneDTO result = geolocalizzazioneService.save(geolocalizzazioneDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, geolocalizzazioneDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /geolocalizzaziones : get all the geolocalizzaziones.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of geolocalizzaziones in body
     */
    @GetMapping("/geolocalizzaziones")
    @Timed
    public ResponseEntity<List<GeolocalizzazioneDTO>> getAllGeolocalizzaziones(GeolocalizzazioneCriteria criteria) {
        log.debug("REST request to get Geolocalizzaziones by criteria: {}", criteria);
        List<GeolocalizzazioneDTO> entityList = geolocalizzazioneQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /geolocalizzaziones/count : count all the geolocalizzaziones.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/geolocalizzaziones/count")
    @Timed
    public ResponseEntity<Long> countGeolocalizzaziones(GeolocalizzazioneCriteria criteria) {
        log.debug("REST request to count Geolocalizzaziones by criteria: {}", criteria);
        return ResponseEntity.ok().body(geolocalizzazioneQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /geolocalizzaziones/:id : get the "id" geolocalizzazione.
     *
     * @param id the id of the geolocalizzazioneDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the geolocalizzazioneDTO, or with status 404 (Not Found)
     */
    @GetMapping("/geolocalizzaziones/{id}")
    @Timed
    public ResponseEntity<GeolocalizzazioneDTO> getGeolocalizzazione(@PathVariable Long id) {
        log.debug("REST request to get Geolocalizzazione : {}", id);
        Optional<GeolocalizzazioneDTO> geolocalizzazioneDTO = geolocalizzazioneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(geolocalizzazioneDTO);
    }

    /**
     * DELETE  /geolocalizzaziones/:id : delete the "id" geolocalizzazione.
     *
     * @param id the id of the geolocalizzazioneDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/geolocalizzaziones/{id}")
    @Timed
    public ResponseEntity<Void> deleteGeolocalizzazione(@PathVariable Long id) {
        log.debug("REST request to delete Geolocalizzazione : {}", id);
        geolocalizzazioneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/geolocalizzazione/plus")
    @Timed
    public ResponseEntity<List<GeolocalizzazioneExtendedDTO>> getAllGeolocalizzazioneExtended() {
        log.debug("REST request to get GeolocalizzazioneExtended");
        List<GeolocalizzazioneExtendedDTO> result = geolocalizzazioneService.findAllGeolocalizationExt();
        return ResponseEntity.ok().body(result);
    }
    
}
