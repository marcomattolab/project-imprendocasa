package it.arancia.service.impl;

import it.arancia.service.ProfessioneService;
import it.arancia.domain.Professione;
import it.arancia.repository.ProfessioneRepository;
import it.arancia.service.dto.ProfessioneDTO;
import it.arancia.service.mapper.ProfessioneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Professione.
 */
@Service
@Transactional
public class ProfessioneServiceImpl implements ProfessioneService {
    private final Logger log = LoggerFactory.getLogger(ProfessioneServiceImpl.class);

    private final ProfessioneRepository professioneRepository;
    private final ProfessioneMapper professioneMapper;

    public ProfessioneServiceImpl(ProfessioneRepository professioneRepository, ProfessioneMapper professioneMapper) {
        this.professioneRepository = professioneRepository;
        this.professioneMapper = professioneMapper;
    }

    /**
     * Save a professione.
     *
     * @param professioneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfessioneDTO save(ProfessioneDTO professioneDTO) {
        log.debug("Request to save Professione : {}", professioneDTO);

        Professione professione = professioneMapper.toEntity(professioneDTO);
        professione = professioneRepository.save(professione);
        return professioneMapper.toDto(professione);
    }

    /**
     * Get all the professiones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfessioneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Professiones");
        return professioneRepository.findAll(pageable)
            .map(professioneMapper::toDto);
    }


    /**
     * Get one professione by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProfessioneDTO> findOne(Long id) {
        log.debug("Request to get Professione : {}", id);
        return professioneRepository.findById(id)
            .map(professioneMapper::toDto);
    }

    /**
     * Delete the professione by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Professione : {}", id);
        professioneRepository.deleteById(id);
    }
}
