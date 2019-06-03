package it.arancia.service;

import it.arancia.service.dto.ProfessioneDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Professione.
 */
public interface ProfessioneService {

    /**
     * Save a professione.
     *
     * @param professioneDTO the entity to save
     * @return the persisted entity
     */
    ProfessioneDTO save(ProfessioneDTO professioneDTO);

    /**
     * Get all the professiones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProfessioneDTO> findAll(Pageable pageable);


    /**
     * Get the "id" professione.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProfessioneDTO> findOne(Long id);

    /**
     * Delete the "id" professione.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
