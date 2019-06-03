package it.arancia.service;

import it.arancia.service.dto.FilesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Files.
 */
public interface FilesService {

    /**
     * Save a files.
     *
     * @param filesDTO the entity to save
     * @return the persisted entity
     */
    FilesDTO save(FilesDTO filesDTO);

    /**
     * Get all the files.
     *
     * @return the list of entities
     */
    List<FilesDTO> findAll();

    /**
     * Get all the files about that parent Id.
     *
     * @param idImmobile the entity parent id
     * @return the list of entities
     */
    List<FilesDTO> findByImmobileId(Long idImmobile);

    /**
     * Get the "id" files.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FilesDTO> findOne(Long id);

    /**
     * Delete the "id" files.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
