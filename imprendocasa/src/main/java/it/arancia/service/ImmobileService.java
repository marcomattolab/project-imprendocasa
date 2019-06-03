package it.arancia.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.arancia.service.dto.ImmobileDTO;

/**
 * Service Interface for managing Immobile.
 */
public interface ImmobileService {

    /**
     * Save a immobile.
     *
     * @param immobileDTO the entity to save
     * @return the persisted entity
     */
    ImmobileDTO save(ImmobileDTO immobileDTO);

    /**
     * Get all the immobiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ImmobileDTO> findAll(Pageable pageable);

    /**
     * Get the "id" immobile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ImmobileDTO> findOne(Long id);

    /**
     * Delete the "id" immobile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

//    /**
//     * Get all the ImmobileDTO where Incarico is null.
//     *
//     * @return the list of entities
//     */
//    List<ImmobileDTO> findAllWhereIncaricoIsNull();
}
