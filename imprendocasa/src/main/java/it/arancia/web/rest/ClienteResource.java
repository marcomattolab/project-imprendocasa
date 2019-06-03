package it.arancia.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.web.util.ResponseUtil;
import it.arancia.security.SecurityUtils;
import it.arancia.service.ClienteQueryService;
import it.arancia.service.ClienteService;
import it.arancia.service.IncaricoService;
import it.arancia.service.dto.ClienteCriteria;
import it.arancia.service.dto.ClienteDTO;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Cliente.
 */
@RestController
@RequestMapping("/api")
public class ClienteResource {
	private final Logger log = LoggerFactory.getLogger(ClienteResource.class);
	private static final String ENTITY_NAME = "cliente";
	private final IncaricoService incaricoService;
	private final ClienteService clienteService;
	private final ClienteQueryService clienteQueryService;

	public ClienteResource(ClienteService clienteService, ClienteQueryService clienteQueryService,
			IncaricoService incaricoService) {
		this.clienteService = clienteService;
		this.clienteQueryService = clienteQueryService;
		this.incaricoService = incaricoService;
	}

	/**
	 * POST /clientes : Create a new cliente.
	 *
	 * @param clienteDTO the clienteDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         clienteDTO, or with status 400 (Bad Request) if the cliente has
	 *         already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/clientes")
	@Timed
	public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO clienteDTO)
			throws URISyntaxException {
		log.debug("REST request to save Cliente : {}", clienteDTO);
		if (clienteDTO.getId() != null) {
			throw new BadRequestAlertException("A new cliente cannot already have an ID", ENTITY_NAME, "idexists");
		}
		/* TO-DO: GESTIRE DUPLICATO COD FISCALE PER ADMIN */
		if (!checkCodiceFiscale(clienteDTO.getCodiceFiscale(), clienteDTO.getAgente())) {
			throw new BadRequestAlertException("Codice Fiscale già presente nel sistema", ENTITY_NAME,
					"codiceFiscaleAlreadyExists");
		}
		ClienteDTO result = clienteService.save(clienteDTO);
		return ResponseEntity.created(new URI("/api/clientes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /clientes : Updates an existing cliente.
	 *
	 * @param clienteDTO the clienteDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         clienteDTO, or with status 400 (Bad Request) if the clienteDTO is not
	 *         valid, or with status 500 (Internal Server Error) if the clienteDTO
	 *         couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/clientes")
	@Timed
	public ResponseEntity<ClienteDTO> updateCliente(@Valid @RequestBody ClienteDTO clienteDTO)
			throws URISyntaxException {
		log.debug("REST request to update Cliente : {}", clienteDTO);
		if (clienteDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		ClienteDTO result = clienteService.save(clienteDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clienteDTO.getId().toString())).body(result);
	}

	/**
	 * GET /clientes : get all the clientes.
	 *
	 * @param pageable the pagination information
	 * @param criteria the criterias which the requested entities should match
	 * @return the ResponseEntity with status 200 (OK) and the list of clientes in
	 *         body
	 */
	@GetMapping("/clientes")
	@Timed
	public ResponseEntity<List<ClienteDTO>> getAllClientes(ClienteCriteria criteria, Pageable pageable) {
		log.debug("REST request to get Clientes by criteria: {}", criteria);
		Page<ClienteDTO> page = clienteQueryService.findByCriteria(criteria, pageable);

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clientes");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /clientes/count : count all the clientes.
	 *
	 * @param criteria the criterias which the requested entities should match
	 * @return the ResponseEntity with status 200 (OK) and the count in body
	 */
	@GetMapping("/clientes/count")
	@Timed
	public ResponseEntity<Long> countClientes(ClienteCriteria criteria) {
		log.debug("REST request to count Clientes by criteria: {}", criteria);
		return ResponseEntity.ok().body(clienteQueryService.countByCriteria(criteria));
	}

	/**
	 * GET /clientes/:id : get the "id" cliente.
	 *
	 * @param id the id of the clienteDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the clienteDTO,
	 *         or with status 404 (Not Found)
	 */
	@GetMapping("/clientes/{id}")
	@Timed
	public ResponseEntity<ClienteDTO> getCliente(@PathVariable Long id) {
		log.debug("REST request to get Cliente : {}", id);
		Optional<ClienteDTO> clienteDTO = clienteService.findOne(id);
		return ResponseUtil.wrapOrNotFound(clienteDTO);
	}

	/**
	 * DELETE /clientes/:id : delete the "id" cliente.
	 *
	 * @param id the id of the clienteDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/clientes/{id}")
	@Timed
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
		log.debug("REST request to delete Cliente : {}", id);
		clienteService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * GET /clienti : get all the clienti by currentUser.
	 *
	 * @param pageable the pagination information
	 * @param criteria the criterias which the requested entities should match
	 * @return the ResponseEntity with status 200 (OK) and the list of clienti in
	 *         body
	 */
	@GetMapping("/clientes/currentUser")
	@Timed
	public ResponseEntity<List<ClienteDTO>> getAllClientesByCurrentUser(ClienteCriteria criteria, Pageable pageable) {
		log.debug("REST request to get clientes by criteria: {}", criteria);
		/*
		 * String currentUser = SecurityUtils.getCurrentUserLogin().get(); StringFilter
		 * current = new StringFilter();
		 * if(!org.apache.commons.lang3.StringUtils.equals(currentUser, ADMIN)) {
		 * current.setEquals(currentUser); criteria.setCurrentUser(current); }
		 */
		Page<ClienteDTO> page = clienteQueryService.findByCriteria(criteria, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clientes");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@GetMapping("/clientes/editAvaiable/{id}")
	@Timed
	public ResponseEntity<Void> editClienteAvaiable(@PathVariable Long id) {
		log.debug("REST request to edit Cliente Avaiable : {}", id);
		if (!SecurityUtils.isCurrentUserInRoleAdmin() && hasIncarichiAttivi(id)) {
			throw new BadRequestAlertException(
					"Il Cliente non puo' essere modificato in quanto legato ad un incarico non in stato bozza",
					ENTITY_NAME, "error.editCliente.incaricoAttivoExists");
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/clientes/deleteAvaiable/{id}")
	@Timed
	public ResponseEntity<Void> deleteClienteAvaiable(@PathVariable Long id) {
		log.debug("REST request to delete Cliente Avaiable : {}", id);
		if (hasIncarichiAttivi(id)) {
			throw new BadRequestAlertException(
					"Il Cliente non puo' essere rimosso in quanto legato ad un incarico non in stato bozza",
					ENTITY_NAME, "error.deleteCliente.incaricoAttivoExists");
		}
		return ResponseEntity.ok().build();
	}

	private boolean hasIncarichiAttivi(Long idCliente) throws BadRequestAlertException {
		boolean hasIncarichiAttivi = false;
		log.debug("Check has Incarichi non in stato bozza for Cliente : {}", idCliente);
		final Long countIncarico = incaricoService.countNonDraftByCliente(idCliente);
		if (countIncarico != null && countIncarico.compareTo(0l) > 0) {
			hasIncarichiAttivi = true;
			log.debug("Cliente {0} has {1} incarichi non in stato bozza", idCliente, countIncarico);
		}
		return hasIncarichiAttivi;
	}

//    private void checkDeleteAvaiable(Long idCliente) throws BadRequestAlertException {
//		log.debug("Check delete avaiable for Cliente : {}", idCliente);
//		final List<IncaricoDTO> incaricoDraftByClienteList = incaricoService.findAllNonDraftByCliente(idCliente);
//		if (!CollectionUtils.isEmpty(incaricoDraftByClienteList)) {
//			log.debug("Delete not avaiable for Cliente : {} - legato ad un incarico non in stato bozza", idCliente);
//			throw new BadRequestAlertException("Il cliente non puo' essere rimosso in quanto legato ad un incarico non in stato bozza", ENTITY_NAME,
//					"deleteClienteErr.incaricoAttivoExists");
//		}
//	}

	/*
	 * @GetMapping("/clientes/freeSearch/{stringa}")
	 * 
	 * @Timed public ResponseEntity<List<ClienteDTO>>
	 * getAllClientesByFree(@PathVariable String stringa, ClienteCriteria criteria,
	 * Pageable pageable) {
	 * log.debug("REST request to get clientes by criteria: {}", criteria);
	 * StringFilter stringaFilter = new StringFilter();
	 * stringaFilter.setEquals(stringa); criteria.setFreeSearch(stringaFilter);
	 * Page<ClienteDTO> page = clienteQueryService.findByCriteria(criteria,
	 * pageable); HttpHeaders headers =
	 * PaginationUtil.generatePaginationHttpHeaders(page, "/api/clientes"); return
	 * ResponseEntity.ok().headers(headers).body(page.getContent()); }
	 */

	/**
	 * Verifica l'esistenza di un utente con lo stesso codice fiscale creato dallo
	 * stesso utente (agente).
	 *
	 * @param codiceFiscale the codice fiscale
	 * @return true, if successful
	 */
	private boolean checkCodiceFiscale(String codiceFiscale, String agente) {
		boolean checkCodiceFiscale = true;
		if (StringUtils.isNotEmpty(codiceFiscale)) {
			StringFilter filtro = new StringFilter();
			filtro.setEquals(codiceFiscale);
			StringFilter filtroAgente = new StringFilter();
			filtroAgente.setEquals(agente);
			ClienteCriteria criteria = new ClienteCriteria();
			criteria.setCodiceFiscale(filtro);
			criteria.setAgente(filtroAgente);
			List<ClienteDTO> cliente = clienteQueryService.findByCriteria(criteria);
			checkCodiceFiscale = CollectionUtils.isEmpty(cliente);
		}
		return checkCodiceFiscale;
	}
}
