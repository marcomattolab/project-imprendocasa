package it.arancia.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arancia.domain.Cliente;
import it.arancia.repository.ClienteRepository;
import it.arancia.service.ClienteQueryService;
import it.arancia.service.ClienteService;
import it.arancia.service.dto.ClienteDTO;
import it.arancia.service.mapper.ClienteMapper;

/**
 * Service Implementation for managing Cliente.
 */
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {
	private final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;

	public ClienteServiceImpl(ClienteRepository clienteRepository, ClienteMapper clienteMapper,
			ClienteQueryService clienteQueryService) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
	}

	/**
	 * Save a cliente.
	 *
	 * @param clienteDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public ClienteDTO save(ClienteDTO clienteDTO) {
		log.debug("Request to save Cliente : {}", clienteDTO);

		Cliente cliente = clienteMapper.toEntity(clienteDTO);
		cliente = clienteRepository.save(cliente);
		return clienteMapper.toDto(cliente);
	}

	/**
	 * Get all the clientes.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Clientes");
		Page<ClienteDTO> clienti = clienteRepository.findAll(pageable).map(clienteMapper::toDto);
		return clienti;
	}

	/**
	 * Get one cliente by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<ClienteDTO> findOne(Long id) {
		log.debug("Request to get Cliente : {}", id);
		return clienteRepository.findById(id).map(clienteMapper::toDto);
	}

	/**
	 * Delete the cliente by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Cliente : {}", id);
		clienteRepository.deleteById(id);
	}

}
