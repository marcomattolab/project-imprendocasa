package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.FilesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Files and its DTO FilesDTO.
 */
@Mapper(componentModel = "spring", uses = {ImmobileMapper.class})
public interface FilesMapper extends EntityMapper<FilesDTO, Files> {

    @Mapping(source = "immobile.id", target = "immobileId")
    @Mapping(source = "immobile.pathFolder", target = "immobilePathFolder")
    FilesDTO toDto(Files files);

    @Mapping(source = "immobileId", target = "immobile")
    Files toEntity(FilesDTO filesDTO);

    default Files fromId(Long id) {
        if (id == null) {
            return null;
        }
        Files files = new Files();
        files.setId(id);
        return files;
    }
}
