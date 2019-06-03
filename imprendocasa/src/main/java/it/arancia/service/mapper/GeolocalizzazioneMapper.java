package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.GeolocalizzazioneDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Geolocalizzazione and its DTO GeolocalizzazioneDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GeolocalizzazioneMapper extends EntityMapper<GeolocalizzazioneDTO, Geolocalizzazione> {


    @Mapping(target = "posizione", ignore = true)
    Geolocalizzazione toEntity(GeolocalizzazioneDTO geolocalizzazioneDTO);

    default Geolocalizzazione fromId(Long id) {
        if (id == null) {
            return null;
        }
        Geolocalizzazione geolocalizzazione = new Geolocalizzazione();
        geolocalizzazione.setId(id);
        return geolocalizzazione;
    }
}
