package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.ProfessioneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Professione and its DTO ProfessioneDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProfessioneMapper extends EntityMapper<ProfessioneDTO, Professione> {



    default Professione fromId(Long id) {
        if (id == null) {
            return null;
        }
        Professione professione = new Professione();
        professione.setId(id);
        return professione;
    }
}
