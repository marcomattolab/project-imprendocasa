package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.ClienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cliente and its DTO ClienteDTO.
 */
@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {


    @Mapping(target = "listaContattis", ignore = true)
//    @Mapping(target = "incaricos", ignore = true) // FIXME
    @Mapping(target = "incaricoCommittentes", ignore = true)
    @Mapping(target = "incaricoProponentes", ignore = true)
    @Mapping(target = "incaricoAcquirenteLocatarios", ignore = true)
    @Mapping(target = "incaricoSegnalatores", ignore = true)
    Cliente toEntity(ClienteDTO clienteDTO);

    default Cliente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }

}
