package it.arancia.service;

import java.util.List;

import it.arancia.service.dto.IdashboardDTO;

/**
 * Service Interface for managing IdashboardDTO.
 */
public interface DashboardService {

    /**
     * Get all the IdashboardDTO.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    List<IdashboardDTO> findByCriteria(String criteria);

}
