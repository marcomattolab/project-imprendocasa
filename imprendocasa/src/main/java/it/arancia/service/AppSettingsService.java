package it.arancia.service;

import it.arancia.service.dto.AppSettingsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AppSettings.
 */
public interface AppSettingsService {

    /**
     * Save a appSettings.
     *
     * @param appSettingsDTO the entity to save
     * @return the persisted entity
     */
    AppSettingsDTO save(AppSettingsDTO appSettingsDTO);

    /**
     * Get all the appSettings.
     *
     * @return the list of entities
     */
    List<AppSettingsDTO> findAll();


    /**
     * Get the "id" appSettings.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AppSettingsDTO> findOne(Long id);

    /**
     * Delete the "id" appSettings.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    
    /**
     * Find one by categoria and chiave.
     *
     * @param categoria the categoria
     * @param chiave the chiave
     * @return the optional
     */
    Optional<AppSettingsDTO> findOneByCategoriaAndChiave(String categoria, String chiave);
    
}
