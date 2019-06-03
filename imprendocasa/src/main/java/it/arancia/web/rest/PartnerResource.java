package it.arancia.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import it.arancia.service.PartnerQueryService;
import it.arancia.service.PartnerService;
import it.arancia.service.dto.PartnerCriteria;
import it.arancia.service.dto.PartnerDTO;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Partner.
 */
@RestController
@RequestMapping("/api")
public class PartnerResource {
    private final Logger log = LoggerFactory.getLogger(PartnerResource.class);

    private static final String ENTITY_NAME = "partner";

    private final PartnerService partnerService;
    private final PartnerQueryService partnerQueryService;

    public PartnerResource(PartnerService partnerService, PartnerQueryService partnerQueryService) {
        this.partnerService = partnerService;
        this.partnerQueryService = partnerQueryService;
    }

    /**
     * POST  /partners : Create a new partner.
     *
     * @param partnerDTO the partnerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new partnerDTO, or with status 400 (Bad Request) if the partner has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/partners")
    @Timed
    public ResponseEntity<PartnerDTO> createPartner(@Valid @RequestBody PartnerDTO partnerDTO) throws URISyntaxException {
        log.debug("REST request to save Partner : {}", partnerDTO);
        if (partnerDTO.getId() != null) {
            throw new BadRequestAlertException("A new partner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PartnerDTO result = partnerService.save(partnerDTO);
        return ResponseEntity.created(new URI("/api/partners/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /partners : Updates an existing partner.
     *
     * @param partnerDTO the partnerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated partnerDTO,
     * or with status 400 (Bad Request) if the partnerDTO is not valid,
     * or with status 500 (Internal Server Error) if the partnerDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/partners")
    @Timed
    public ResponseEntity<PartnerDTO> updatePartner(@Valid @RequestBody PartnerDTO partnerDTO) throws URISyntaxException {
        log.debug("REST request to update Partner : {}", partnerDTO);
        if (partnerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PartnerDTO result = partnerService.save(partnerDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, partnerDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /partners : get all the partners.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of partners in body
     */
    @GetMapping("/partners")
    @Timed
    public ResponseEntity<List<PartnerDTO>> getAllPartners(PartnerCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Partners by criteria: {}", criteria);
        Page<PartnerDTO> page = partnerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/partners");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /partners/count : count all the partners.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the count in body
     */
    @GetMapping("/partners/count")
    @Timed
    public ResponseEntity<Long> countPartners(PartnerCriteria criteria) {
        log.debug("REST request to count Partners by criteria: {}", criteria);
        return ResponseEntity.ok().body(partnerQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /partners/:id : get the "id" partner.
     *
     * @param id the id of the partnerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the partnerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/partners/{id}")
    @Timed
    public ResponseEntity<PartnerDTO> getPartner(@PathVariable Long id) {
        log.debug("REST request to get Partner : {}", id);
        Optional<PartnerDTO> partnerDTO = partnerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(partnerDTO);
    }

    /**
     * Check partner existence
     *
     * @param nome the nome of the partnerDTO to retrieve
     * @return true if exist, false otherwise
     */
    @GetMapping("/partners/exist/{nome}/{cognome}")
    @Timed
    public Boolean checkIfExistPartner(@PathVariable String nome, @PathVariable String cognome) {
        //inizializzazione
        PartnerCriteria criteria = new PartnerCriteria();
        StringFilter nomeFilter = new StringFilter();
        StringFilter cognomeFilter = new StringFilter();

        //convert filter
        nomeFilter.setEquals(nome);
        cognomeFilter.setEquals(cognome);

        // set filter
        criteria.setNome(nomeFilter);
        criteria.setCognome(cognomeFilter);

        List<PartnerDTO> result = partnerQueryService.findByCriteria(criteria);
        if(ObjectUtils.isEmpty(result)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * DELETE  /partners/:id : delete the "id" partner.
     *
     * @param id the id of the partnerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/partners/{id}")
    @Timed
    public ResponseEntity<Void> deletePartner(@PathVariable Long id) {
        log.debug("REST request to delete Partner : {}", id);
        partnerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
