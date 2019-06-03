package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.NotificheDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Notifiche and its DTO NotificheDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NotificheMapper extends EntityMapper<NotificheDTO, Notifiche> {



    default Notifiche fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notifiche notifiche = new Notifiche();
        notifiche.setId(id);
        return notifiche;
    }
}
