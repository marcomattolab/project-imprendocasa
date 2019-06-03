package it.arancia.service.impl;

import it.arancia.service.AppSettingsService;
import it.arancia.domain.AppSettings;
import it.arancia.repository.AppSettingsRepository;
import it.arancia.service.dto.AppSettingsDTO;
import it.arancia.service.mapper.AppSettingsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing AppSettings.
 */
@Service
@Transactional
public class AppSettingsServiceImpl implements AppSettingsService {

	private final Logger log = LoggerFactory.getLogger(AppSettingsServiceImpl.class);

	private final AppSettingsRepository appSettingsRepository;

	private final AppSettingsMapper appSettingsMapper;

	public AppSettingsServiceImpl(AppSettingsRepository appSettingsRepository, AppSettingsMapper appSettingsMapper) {
		this.appSettingsRepository = appSettingsRepository;
		this.appSettingsMapper = appSettingsMapper;
	}

	/**
	 * Save a appSettings.
	 *
	 * @param appSettingsDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public AppSettingsDTO save(AppSettingsDTO appSettingsDTO) {
		log.debug("Request to save AppSettings : {}", appSettingsDTO);

		AppSettings appSettings = appSettingsMapper.toEntity(appSettingsDTO);
		appSettings = appSettingsRepository.save(appSettings);
		return appSettingsMapper.toDto(appSettings);
	}

	/**
	 * Get all the appSettings.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AppSettingsDTO> findAll() {
		log.debug("Request to get all AppSettings");
		return appSettingsRepository.findAll().stream().map(appSettingsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one appSettings by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<AppSettingsDTO> findOne(Long id) {
		log.debug("Request to get AppSettings : {}", id);
		return appSettingsRepository.findById(id).map(appSettingsMapper::toDto);
	}

	/**
	 * Delete the appSettings by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete AppSettings : {}", id);
		appSettingsRepository.deleteById(id);
	}

	@Override
	public Optional<AppSettingsDTO> findOneByCategoriaAndChiave(String categoria, String chiave) {
		log.debug("Request to get AppSettings : {}", categoria, chiave);
		return appSettingsRepository.findOneByCategoriaAndChiave(categoria, chiave).map(appSettingsMapper::toDto);
	}
}
