package it.arancia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.arancia.service.dto.IncaricoDTO;
import it.arancia.service.dto.IncaricoStatutesDTO;

/**
 * Service Interface for managing Incarico.
 */
public interface IncaricoService {

    /**
     * Save a incarico.
     *
     * @param incaricoDTO the entity to save
     * @return the persisted entity
     */
    IncaricoDTO save(IncaricoDTO incaricoDTO);

    /**
     * Get all the incaricos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IncaricoDTO> findAll(Pageable pageable);

    /**
     * Get all the Incarico with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<IncaricoDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" incarico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<IncaricoDTO> findOne(Long id);

    /**
     * Delete the "id" incarico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    
    /**
     * Get all the incaricos in Draft Status.
     *
     * @return the list of entities
     */
    List<IncaricoDTO> findAllDraft();
    
    /**
     * Check Permission by user role
     *
     * @return boolean
     */
    boolean checkPermission(IncaricoDTO incaricoDTO);

    /**
     * Get status information of Incarico
     *   
     * @param idIncarico the Incarico ID to check
     * @return the ResponseEntity with statutes information
     */
    IncaricoStatutesDTO getStatusInformation(Long idIncarico);
    
    /**
	 * Change Status to Incarico.
	 *
	 * @param incaricoDTO the entity to update/change status
	 * @return the persisted entity
	 */
    IncaricoDTO changeStatus(IncaricoDTO incaricoDTO);
    
    /**
     * Get all the incaricos not in Draft Status for Id Cliente.
     *
     * @return the list of entities
     */
    List<IncaricoDTO> findAllNonDraftByCliente(Long idCliente);

    /**
     * Get count of the incaricos not in Draft Status for immobile.
     *
     * @return count
     */
	Long countNonDraftByImmobile(Long idImmobile);

	 /**
     * Get count of the incaricos not in Draft Status for Id Cliente.
     *
     * @return count
     */
	Long countNonDraftByCliente(Long idCliente);

	/**
     * Get all the incaricos by idImmobile
     *
     * @return the list of entities
     */
	List<IncaricoDTO> findAllByImmobile(Long idImmobile);
    
}
