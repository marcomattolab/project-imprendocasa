package it.arancia.service;

import it.arancia.service.dto.ListaContattiDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ListaContatti.
 */
public interface ListaContattiService {

    /**
     * Save a listaContatti.
     *
     * @param listaContattiDTO the entity to save
     * @return the persisted entity
     */
    ListaContattiDTO save(ListaContattiDTO listaContattiDTO);

    /**
     * Get all the listaContattis.
     *
     * @return the list of entities
     */
    List<ListaContattiDTO> findAll();


    /**
     * Get the "id" listaContatti.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ListaContattiDTO> findOne(Long id);

    /**
     * Delete the "id" listaContatti.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
