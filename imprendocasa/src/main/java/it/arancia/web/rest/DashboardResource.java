package it.arancia.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import it.arancia.service.DashboardService;
import it.arancia.service.dto.IdashboardDTO;

/**
 * REST controller for managing Dashboard.
 */
@RestController
@RequestMapping("/api")
public class DashboardResource {
    private final Logger log = LoggerFactory.getLogger(DashboardResource.class);
    private final DashboardService dashboardService;

    public DashboardResource(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }
    
    /**
     * GET  /dashboard : get all the series data by criteria.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of series data in body
     */
    @GetMapping("/dashboard/{criteria}")
    @Timed
    public ResponseEntity<List<IdashboardDTO>> getAllDataByCriteria(@PathVariable String criteria) {
        log.debug("REST request to get Incaricos by criteria: {}", criteria);
        List<IdashboardDTO> result = dashboardService.findByCriteria(criteria);
        return ResponseEntity.ok().body(result);
    }

}
