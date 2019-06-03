package it.arancia.service.impl;

import it.arancia.service.NotificheService;
import it.arancia.domain.Notifiche;
import it.arancia.repository.NotificheRepository;
import it.arancia.service.dto.NotificheDTO;
import it.arancia.service.mapper.NotificheMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Notifiche.
 */
@Service
@Transactional
public class NotificheServiceImpl implements NotificheService {

    private final Logger log = LoggerFactory.getLogger(NotificheServiceImpl.class);

    private final NotificheRepository notificheRepository;

    private final NotificheMapper notificheMapper;

    public NotificheServiceImpl(NotificheRepository notificheRepository, NotificheMapper notificheMapper) {
        this.notificheRepository = notificheRepository;
        this.notificheMapper = notificheMapper;
    }

    /**
     * Save a notifiche.
     *
     * @param notificheDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NotificheDTO save(NotificheDTO notificheDTO) {
        log.debug("Request to save Notifiche : {}", notificheDTO);

        Notifiche notifiche = notificheMapper.toEntity(notificheDTO);
        notifiche = notificheRepository.save(notifiche);
        return notificheMapper.toDto(notifiche);
    }

    /**
     * Get all the notifiches.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificheDTO> findAll() {
        log.debug("Request to get all Notifiches");
        return notificheRepository.findAll().stream()
            .map(notificheMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one notifiche by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NotificheDTO> findOne(Long id) {
        log.debug("Request to get Notifiche : {}", id);
        return notificheRepository.findById(id)
            .map(notificheMapper::toDto);
    }

    /**
     * Delete the notifiche by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Notifiche : {}", id);
        notificheRepository.deleteById(id);
    }
}
