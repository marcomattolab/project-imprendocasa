package it.arancia.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.arancia.domain.ListaContatti;
import it.arancia.service.dto.ListaContattiExtDTO;

/**
 * Mapper for the entity ListaContatti and its DTO ListaContattiDTO.
 */
@Mapper(componentModel = "spring", uses = { ClienteMapper.class, IncaricoMapper.class })
public interface ListaContattiExtMapper extends EntityMapper<ListaContattiExtDTO, ListaContatti> {

	@Mapping(source = "cliente", target = "cliente")
	@Mapping(source = "incarico.id", target = "incaricoId")
	@Mapping(source = "incarico.riferimento", target = "incaricoRiferimento")
	ListaContattiExtDTO toDto(ListaContatti listaContatti);

	@Mapping(target = "cliente", ignore = true)
	@Mapping(source = "incaricoId", target = "incarico")
	ListaContatti toEntity(ListaContattiExtDTO listaContattiDTO);

	default ListaContatti fromId(Long id) {
		if (id == null) {
			return null;
		}
		ListaContatti listaContatti = new ListaContatti();
		listaContatti.setId(id);
		return listaContatti;
	}
}
