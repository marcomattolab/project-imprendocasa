package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.ListaContattiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ListaContatti and its DTO ListaContattiDTO.
 */
@Mapper(componentModel = "spring", uses = {ClienteMapper.class, IncaricoMapper.class})
public interface ListaContattiMapper extends EntityMapper<ListaContattiDTO, ListaContatti> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cognome", target = "clienteCognome")
    @Mapping(source = "incarico.id", target = "incaricoId")
    @Mapping(source = "incarico.riferimento", target = "incaricoRiferimento")
    ListaContattiDTO toDto(ListaContatti listaContatti);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "incaricoId", target = "incarico")
    ListaContatti toEntity(ListaContattiDTO listaContattiDTO);

    default ListaContatti fromId(Long id) {
        if (id == null) {
            return null;
        }
        ListaContatti listaContatti = new ListaContatti();
        listaContatti.setId(id);
        return listaContatti;
    }
}
