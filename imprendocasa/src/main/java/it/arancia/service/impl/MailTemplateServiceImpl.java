package it.arancia.service.impl;

import it.arancia.service.MailTemplateService;
import it.arancia.domain.MailTemplate;
import it.arancia.repository.MailTemplateRepository;
import it.arancia.service.dto.MailTemplateDTO;
import it.arancia.service.mapper.MailTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MailTemplate.
 */
@Service
@Transactional
public class MailTemplateServiceImpl implements MailTemplateService {
    private final Logger log = LoggerFactory.getLogger(MailTemplateServiceImpl.class);

    private final MailTemplateRepository mailTemplateRepository;
    private final MailTemplateMapper mailTemplateMapper;

    public MailTemplateServiceImpl(MailTemplateRepository mailTemplateRepository, MailTemplateMapper mailTemplateMapper) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.mailTemplateMapper = mailTemplateMapper;
    }

    /**
     * Save a mailTemplate.
     *
     * @param mailTemplateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MailTemplateDTO save(MailTemplateDTO mailTemplateDTO) {
        log.debug("Request to save MailTemplate : {}", mailTemplateDTO);

        MailTemplate mailTemplate = mailTemplateMapper.toEntity(mailTemplateDTO);
        mailTemplate = mailTemplateRepository.save(mailTemplate);
        return mailTemplateMapper.toDto(mailTemplate);
    }

    /**
     * Get all the mailTemplates.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MailTemplateDTO> findAll() {
        log.debug("Request to get all MailTemplates");
        return mailTemplateRepository.findAll().stream()
            .map(mailTemplateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one mailTemplate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MailTemplateDTO> findOne(Long id) {
        log.debug("Request to get MailTemplate : {}", id);
        return mailTemplateRepository.findById(id)
            .map(mailTemplateMapper::toDto);
    }

    /**
     * Delete the mailTemplate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MailTemplate : {}", id);
        mailTemplateRepository.deleteById(id);
    }
}
