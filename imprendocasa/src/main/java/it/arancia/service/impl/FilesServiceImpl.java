package it.arancia.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arancia.domain.Files;
import it.arancia.repository.FilesRepository;
import it.arancia.service.FilesService;
import it.arancia.service.dto.FilesDTO;
import it.arancia.service.mapper.FilesMapper;

/**
 * Service Implementation for managing Files.
 */
@Service
@Transactional
public class FilesServiceImpl implements FilesService {

    private final Logger log = LoggerFactory.getLogger(FilesServiceImpl.class);

    private final FilesRepository filesRepository;

    private final FilesMapper filesMapper;

    public FilesServiceImpl(FilesRepository filesRepository, FilesMapper filesMapper) {
        this.filesRepository = filesRepository;
        this.filesMapper = filesMapper;
    }

    /**
     * Save a files.
     *
     * @param filesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FilesDTO save(FilesDTO filesDTO) {
        log.debug("Request to save Files : {}", filesDTO);

        Files files = filesMapper.toEntity(filesDTO);
        files = filesRepository.save(files);
        return filesMapper.toDto(files);
    }

    /**
     * Get all the files.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FilesDTO> findAll() {
        log.debug("Request to get all Files");
        return filesRepository.findAll().stream()
            .map(filesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one files by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FilesDTO> findOne(Long id) {
        log.debug("Request to get Files : {}", id);
        return filesRepository.findById(id)
            .map(filesMapper::toDto);
    }

    /**
     * Delete the files by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Files : {}", id);
        filesRepository.deleteById(id);
    }

	@Override
	@Transactional(readOnly = true)
	public List<FilesDTO> findByImmobileId(Long idImmobile) {
		log.debug("Request to get Files about immobileId : {}", idImmobile);
        return filesRepository.findByImmobileId(idImmobile).stream()
            .map(filesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
	}
}
