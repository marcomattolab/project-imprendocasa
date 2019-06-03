package it.arancia.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.arancia.service.AppSettingsService;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.service.dto.AppSettingsDTO;
import it.arancia.service.dto.AppSettingsCriteria;
import it.arancia.service.AppSettingsQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AppSettings.
 */
@RestController
@RequestMapping("/api")
public class AppSettingsResource {

    private final Logger log = LoggerFactory.getLogger(AppSettingsResource.class);

    private static final String ENTITY_NAME = "appSettings";

    private final AppSettingsService appSettingsService;

    private final AppSettingsQueryService appSettingsQueryService;

    public AppSettingsResource(AppSettingsService appSettingsService, AppSettingsQueryService appSettingsQueryService) {
        this.appSettingsService = appSettingsService;
        this.appSettingsQueryService = appSettingsQueryService;
    }

    /**
     * POST  /app-settings : Create a new appSettings.
     *
     * @param appSettingsDTO the appSettingsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appSettingsDTO, or with status 400 (Bad Request) if the appSettings has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/app-settings")
    @Timed
    public ResponseEntity<AppSettingsDTO> createAppSettings(@Valid @RequestBody AppSettingsDTO appSettingsDTO) throws URISyntaxException {
        log.debug("REST request to save AppSettings : {}", appSettingsDTO);
        if (appSettingsDTO.getId() != null) {
            throw new BadRequestAlertException("A new appSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppSettingsDTO result = appSettingsService.save(appSettingsDTO);
        return ResponseEntity.created(new URI("/api/app-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /app-settings : Updates an existing appSettings.
     *
     * @param appSettingsDTO the appSettingsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appSettingsDTO,
     * or with status 400 (Bad Request) if the appSettingsDTO is not valid,
     * or with status 500 (Internal Server Error) if the appSettingsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/app-settings")
    @Timed
    public ResponseEntity<AppSettingsDTO> updateAppSettings(@Valid @RequestBody AppSettingsDTO appSettingsDTO) throws URISyntaxException {
        log.debug("REST request to update AppSettings : {}", appSettingsDTO);
        if (appSettingsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AppSettingsDTO result = appSettingsService.save(appSettingsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appSettingsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /app-settings : get all the appSettings.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of appSettings in body
     */
    @GetMapping("/app-settings")
    @Timed
    public ResponseEntity<List<AppSettingsDTO>> getAllAppSettings(AppSettingsCriteria criteria) {
        log.debug("REST request to get AppSettings by criteria: {}", criteria);
        List<AppSettingsDTO> entityList = appSettingsQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /app-settings/count : count all the appSettings.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/app-settings/count")
    @Timed
    public ResponseEntity<Long> countAppSettings(AppSettingsCriteria criteria) {
        log.debug("REST request to count AppSettings by criteria: {}", criteria);
        return ResponseEntity.ok().body(appSettingsQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /app-settings/:id : get the "id" appSettings.
     *
     * @param id the id of the appSettingsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appSettingsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/app-settings/{id}")
    @Timed
    public ResponseEntity<AppSettingsDTO> getAppSettings(@PathVariable Long id) {
        log.debug("REST request to get AppSettings : {}", id);
        Optional<AppSettingsDTO> appSettingsDTO = appSettingsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(appSettingsDTO);
    }

    /**
     * DELETE  /app-settings/:id : delete the "id" appSettings.
     *
     * @param id the id of the appSettingsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/app-settings/{id}")
    @Timed
    public ResponseEntity<Void> deleteAppSettings(@PathVariable Long id) {
        log.debug("REST request to delete AppSettings : {}", id);
        appSettingsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
