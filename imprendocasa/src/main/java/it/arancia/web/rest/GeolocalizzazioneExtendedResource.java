package it.arancia.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import it.arancia.service.GeolocalizzazioneService;
import it.arancia.service.dto.GeolocalizzazioneExtendedDTO;

/**
 * REST controller for managing Geolocalizzazione Extened.
 */
@RestController
@RequestMapping("/api")
public class GeolocalizzazioneExtendedResource {
    private final Logger log = LoggerFactory.getLogger(GeolocalizzazioneResource.class);
    private final GeolocalizzazioneService geolocalizzazioneService;

    public GeolocalizzazioneExtendedResource(GeolocalizzazioneService geolocalizzazioneService) {
        this.geolocalizzazioneService = geolocalizzazioneService;
    }

    @GetMapping("/geolocalizzazioneExtended")
    @Timed
    public ResponseEntity<List<GeolocalizzazioneExtendedDTO>> getAllGeolocalizzazioneExtended() {
        log.debug("REST request to get GeolocalizzazioneExtended");
        List<GeolocalizzazioneExtendedDTO> entityList = geolocalizzazioneService.findAllGeolocalizationExt();
        return ResponseEntity.ok().body(entityList);
    }
}
