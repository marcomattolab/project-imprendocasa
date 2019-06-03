package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.IncaricoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Incarico and its DTO IncaricoDTO.
 */
@Mapper(componentModel = "spring", uses = {ImmobileMapper.class, PartnerMapper.class, ClienteMapper.class})
public interface IncaricoMapper extends EntityMapper<IncaricoDTO, Incarico> {

    @Mapping(source = "immobile.id", target = "immobileId")
    @Mapping(source = "immobile.codice", target = "immobileCodice")
    IncaricoDTO toDto(Incarico incarico);

    @Mapping(target = "listaContattis", ignore = true)
    @Mapping(source = "immobileId", target = "immobile")
    Incarico toEntity(IncaricoDTO incaricoDTO);

    default Incarico fromId(Long id) {
        if (id == null) {
            return null;
        }
        Incarico incarico = new Incarico();
        incarico.setId(id);
        return incarico;
    }
}
