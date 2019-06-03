package it.arancia.service;

import it.arancia.service.dto.GeolocalizzazioneDTO;
import it.arancia.service.dto.GeolocalizzazioneExtendedDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Geolocalizzazione.
 */
public interface GeolocalizzazioneService {

    /**
     * Save a geolocalizzazione.
     *
     * @param geolocalizzazioneDTO the entity to save
     * @return the persisted entity
     */
    GeolocalizzazioneDTO save(GeolocalizzazioneDTO geolocalizzazioneDTO);

    /**
     * Get all the geolocalizzaziones.
     *
     * @return the list of entities
     */
    List<GeolocalizzazioneDTO> findAll();
    /**
     * Get all the GeolocalizzazioneDTO where Posizione is null.
     *
     * @return the list of entities
     */
    List<GeolocalizzazioneDTO> findAllWherePosizioneIsNull();


    /**
     * Get the "id" geolocalizzazione.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<GeolocalizzazioneDTO> findOne(Long id);

    /**
     * Delete the "id" geolocalizzazione.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    
    /**
      * Get all the geolocalizzaziones with plus informations.
     * 
     * @return List<GeolocalizzazioneExtendedDTO>
     */
    public List<GeolocalizzazioneExtendedDTO> findAllGeolocalizationExt();
    
}
