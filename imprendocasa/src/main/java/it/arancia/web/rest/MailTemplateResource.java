package it.arancia.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.arancia.service.MailTemplateService;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.service.dto.MailTemplateDTO;
import it.arancia.service.dto.MailTemplateCriteria;
import it.arancia.service.MailTemplateQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MailTemplate.
 */
@RestController
@RequestMapping("/api")
public class MailTemplateResource {
    private final Logger log = LoggerFactory.getLogger(MailTemplateResource.class);

    private static final String ENTITY_NAME = "mailTemplate";

    private final MailTemplateService mailTemplateService;
    private final MailTemplateQueryService mailTemplateQueryService;

    public MailTemplateResource(MailTemplateService mailTemplateService, MailTemplateQueryService mailTemplateQueryService) {
        this.mailTemplateService = mailTemplateService;
        this.mailTemplateQueryService = mailTemplateQueryService;
    }

    /**
     * POST  /mail-templates : Create a new mailTemplate.
     *
     * @param mailTemplateDTO the mailTemplateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mailTemplateDTO, or with status 400 (Bad Request) if the mailTemplate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mail-templates")
    @Timed
    public ResponseEntity<MailTemplateDTO> createMailTemplate(@Valid @RequestBody MailTemplateDTO mailTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save MailTemplate : {}", mailTemplateDTO);
        if (mailTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new mailTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MailTemplateDTO result = mailTemplateService.save(mailTemplateDTO);
        return ResponseEntity.created(new URI("/api/mail-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mail-templates : Updates an existing mailTemplate.
     *
     * @param mailTemplateDTO the mailTemplateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mailTemplateDTO,
     * or with status 400 (Bad Request) if the mailTemplateDTO is not valid,
     * or with status 500 (Internal Server Error) if the mailTemplateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mail-templates")
    @Timed
    public ResponseEntity<MailTemplateDTO> updateMailTemplate(@Valid @RequestBody MailTemplateDTO mailTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update MailTemplate : {}", mailTemplateDTO);
        if (mailTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MailTemplateDTO result = mailTemplateService.save(mailTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mailTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mail-templates : get all the mailTemplates.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mailTemplates in body
     */
    @GetMapping("/mail-templates")
    @Timed
    public ResponseEntity<List<MailTemplateDTO>> getAllMailTemplates(MailTemplateCriteria criteria) {
        log.debug("REST request to get MailTemplates by criteria: {}", criteria);
        List<MailTemplateDTO> entityList = mailTemplateQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /mail-templates/count : count all the mailTemplates.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/mail-templates/count")
    @Timed
    public ResponseEntity<Long> countMailTemplates(MailTemplateCriteria criteria) {
        log.debug("REST request to count MailTemplates by criteria: {}", criteria);
        return ResponseEntity.ok().body(mailTemplateQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /mail-templates/:id : get the "id" mailTemplate.
     *
     * @param id the id of the mailTemplateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mailTemplateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mail-templates/{id}")
    @Timed
    public ResponseEntity<MailTemplateDTO> getMailTemplate(@PathVariable Long id) {
        log.debug("REST request to get MailTemplate : {}", id);
        Optional<MailTemplateDTO> mailTemplateDTO = mailTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mailTemplateDTO);
    }

    /**
     * DELETE  /mail-templates/:id : delete the "id" mailTemplate.
     *
     * @param id the id of the mailTemplateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mail-templates/{id}")
    @Timed
    public ResponseEntity<Void> deleteMailTemplate(@PathVariable Long id) {
        log.debug("REST request to delete MailTemplate : {}", id);
        mailTemplateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
