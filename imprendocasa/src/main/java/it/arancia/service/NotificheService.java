package it.arancia.service;

import it.arancia.service.dto.NotificheDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Notifiche.
 */
public interface NotificheService {

    /**
     * Save a notifiche.
     *
     * @param notificheDTO the entity to save
     * @return the persisted entity
     */
    NotificheDTO save(NotificheDTO notificheDTO);

    /**
     * Get all the notifiches.
     *
     * @return the list of entities
     */
    List<NotificheDTO> findAll();


    /**
     * Get the "id" notifiche.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NotificheDTO> findOne(Long id);

    /**
     * Delete the "id" notifiche.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
