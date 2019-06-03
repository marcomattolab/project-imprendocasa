package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.MailTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MailTemplate and its DTO MailTemplateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MailTemplateMapper extends EntityMapper<MailTemplateDTO, MailTemplate> {



    default MailTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        MailTemplate mailTemplate = new MailTemplate();
        mailTemplate.setId(id);
        return mailTemplate;
    }
}
