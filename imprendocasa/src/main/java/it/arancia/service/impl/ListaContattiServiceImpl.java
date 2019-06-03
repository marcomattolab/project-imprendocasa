package it.arancia.service.impl;

import it.arancia.service.ListaContattiService;
import it.arancia.domain.ListaContatti;
import it.arancia.repository.ListaContattiRepository;
import it.arancia.service.dto.ListaContattiDTO;
import it.arancia.service.mapper.ListaContattiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ListaContatti.
 */
@Service
@Transactional
public class ListaContattiServiceImpl implements ListaContattiService {
    private final Logger log = LoggerFactory.getLogger(ListaContattiServiceImpl.class);

    private final ListaContattiRepository listaContattiRepository;
    private final ListaContattiMapper listaContattiMapper;

    public ListaContattiServiceImpl(ListaContattiRepository listaContattiRepository, ListaContattiMapper listaContattiMapper) {
        this.listaContattiRepository = listaContattiRepository;
        this.listaContattiMapper = listaContattiMapper;
    }

    /**
     * Save a listaContatti.
     *
     * @param listaContattiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ListaContattiDTO save(ListaContattiDTO listaContattiDTO) {
        log.debug("Request to save ListaContatti : {}", listaContattiDTO);

        ListaContatti listaContatti = listaContattiMapper.toEntity(listaContattiDTO);
        listaContatti = listaContattiRepository.save(listaContatti);
        return listaContattiMapper.toDto(listaContatti);
    }

    /**
     * Get all the listaContattis.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ListaContattiDTO> findAll() {
        log.debug("Request to get all ListaContattis");
        return listaContattiRepository.findAll().stream()
            .map(listaContattiMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one listaContatti by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ListaContattiDTO> findOne(Long id) {
        log.debug("Request to get ListaContatti : {}", id);
        return listaContattiRepository.findById(id)
            .map(listaContattiMapper::toDto);
    }

    /**
     * Delete the listaContatti by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ListaContatti : {}", id);
        listaContattiRepository.deleteById(id);
    }
}
