package it.arancia.service;

import it.arancia.service.dto.MailTemplateDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MailTemplate.
 */
public interface MailTemplateService {

    /**
     * Save a mailTemplate.
     *
     * @param mailTemplateDTO the entity to save
     * @return the persisted entity
     */
    MailTemplateDTO save(MailTemplateDTO mailTemplateDTO);

    /**
     * Get all the mailTemplates.
     *
     * @return the list of entities
     */
    List<MailTemplateDTO> findAll();


    /**
     * Get the "id" mailTemplate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MailTemplateDTO> findOne(Long id);

    /**
     * Delete the "id" mailTemplate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
