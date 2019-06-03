package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.PartnerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Partner and its DTO PartnerDTO.
 */
@Mapper(componentModel = "spring", uses = {ProfessioneMapper.class})
public interface PartnerMapper extends EntityMapper<PartnerDTO, Partner> {

    @Mapping(source = "professione.id", target = "professioneId")
    @Mapping(source = "professione.denominazione", target = "professioneDenominazione")
    PartnerDTO toDto(Partner partner);

    @Mapping(source = "professioneId", target = "professione")
    @Mapping(target = "incaricos", ignore = true)
    Partner toEntity(PartnerDTO partnerDTO);

    default Partner fromId(Long id) {
        if (id == null) {
            return null;
        }
        Partner partner = new Partner();
        partner.setId(id);
        return partner;
    }
}

