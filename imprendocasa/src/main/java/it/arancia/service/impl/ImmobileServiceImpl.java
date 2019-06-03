package it.arancia.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.arancia.domain.Immobile;
import it.arancia.repository.ImmobileRepository;
import it.arancia.service.ImmobileService;
import it.arancia.service.dto.ImmobileDTO;
import it.arancia.service.mapper.ImmobileMapper;

/**
 * Service Implementation for managing Immobile.
 */
@Service
@Transactional
public class ImmobileServiceImpl implements ImmobileService {
    private final Logger log = LoggerFactory.getLogger(ImmobileServiceImpl.class);

    private final ImmobileRepository immobileRepository;
    private final ImmobileMapper immobileMapper;

    public ImmobileServiceImpl(ImmobileRepository immobileRepository, ImmobileMapper immobileMapper) {
        this.immobileRepository = immobileRepository;
        this.immobileMapper = immobileMapper;
    }

    /**
     * Save a immobile.
     *
     * @param immobileDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImmobileDTO save(ImmobileDTO immobileDTO) {
        log.debug("Request to save Immobile : {}", immobileDTO);

        Immobile immobile = immobileMapper.toEntity(immobileDTO);
        immobile = immobileRepository.save(immobile);
        return immobileMapper.toDto(immobile);
    }

    /**
     * Get all the immobiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ImmobileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Immobiles");
        return immobileRepository.findAll(pageable)
            .map(immobileMapper::toDto);
    }

    /**
     * Get one immobile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ImmobileDTO> findOne(Long id) {
        log.debug("Request to get Immobile : {}", id);
        return immobileRepository.findById(id)
            .map(immobileMapper::toDto);
    }

    /**
     * Delete the immobile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Immobile : {}", id);
        immobileRepository.deleteById(id);
    }
    
//  /**
//  *  get all the immobiles where Incarico is null.
//  *
//  *  @return the list of entities
//  */
// @Transactional(readOnly = true) 
// public List<ImmobileDTO> findAllWhereIncaricoIsNull() {
//     log.debug("Request to get all immobiles where Incarico is null");
//     return StreamSupport
//         .stream(immobileRepository.findAll().spliterator(), false)
//         //.filter(immobile -> immobile.getIncarico() == null)
//         .map(immobileMapper::toDto)
//         .collect(Collectors.toCollection(LinkedList::new));
// }

}
