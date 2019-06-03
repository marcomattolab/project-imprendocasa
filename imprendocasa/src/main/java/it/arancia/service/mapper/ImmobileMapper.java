package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.ImmobileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Immobile and its DTO ImmobileDTO.
 */
@Mapper(componentModel = "spring", uses = {GeolocalizzazioneMapper.class})
public interface ImmobileMapper extends EntityMapper<ImmobileDTO, Immobile> {

    @Mapping(source = "geolocalizzazione.id", target = "geolocalizzazioneId")
    @Mapping(source = "geolocalizzazione.immobile", target = "geolocalizzazioneImmobile")
    ImmobileDTO toDto(Immobile immobile);

    @Mapping(source = "geolocalizzazioneId", target = "geolocalizzazione")
    @Mapping(target = "files", ignore = true)
    @Mapping(target = "incaricos", ignore = true)
    Immobile toEntity(ImmobileDTO immobileDTO);

    default Immobile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Immobile immobile = new Immobile();
        immobile.setId(id);
        return immobile;
    }
}
