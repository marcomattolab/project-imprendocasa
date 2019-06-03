package it.arancia.service.mapper;

import it.arancia.domain.Incarico;
import it.arancia.domain.ListaContatti;
import it.arancia.service.dto.ListaContattiExtDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-29T14:29:18+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class ListaContattiExtMapperImpl implements ListaContattiExtMapper {

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private IncaricoMapper incaricoMapper;

    @Override
    public List<ListaContatti> toEntity(List<ListaContattiExtDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ListaContatti> list = new ArrayList<ListaContatti>( dtoList.size() );
        for ( ListaContattiExtDTO listaContattiExtDTO : dtoList ) {
            list.add( toEntity( listaContattiExtDTO ) );
        }

        return list;
    }

    @Override
    public List<ListaContattiExtDTO> toDto(List<ListaContatti> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ListaContattiExtDTO> list = new ArrayList<ListaContattiExtDTO>( entityList.size() );
        for ( ListaContatti listaContatti : entityList ) {
            list.add( toDto( listaContatti ) );
        }

        return list;
    }

    @Override
    public ListaContattiExtDTO toDto(ListaContatti listaContatti) {
        if ( listaContatti == null ) {
            return null;
        }

        ListaContattiExtDTO listaContattiExtDTO = new ListaContattiExtDTO();

        listaContattiExtDTO.setCliente( clienteMapper.toDto( listaContatti.getCliente() ) );
        String riferimento = listaContattiIncaricoRiferimento( listaContatti );
        if ( riferimento != null ) {
            listaContattiExtDTO.setIncaricoRiferimento( riferimento );
        }
        Long id = listaContattiIncaricoId( listaContatti );
        if ( id != null ) {
            listaContattiExtDTO.setIncaricoId( id );
        }
        listaContattiExtDTO.setCreatedBy( listaContatti.getCreatedBy() );
        listaContattiExtDTO.setCreatedDate( listaContatti.getCreatedDate() );
        listaContattiExtDTO.setLastModifiedBy( listaContatti.getLastModifiedBy() );
        listaContattiExtDTO.setLastModifiedDate( listaContatti.getLastModifiedDate() );
        listaContattiExtDTO.setId( listaContatti.getId() );
        listaContattiExtDTO.setDateTime( listaContatti.getDateTime() );
        listaContattiExtDTO.setEsito( listaContatti.getEsito() );
        listaContattiExtDTO.setMotivazione( listaContatti.getMotivazione() );
        listaContattiExtDTO.setNote( listaContatti.getNote() );

        return listaContattiExtDTO;
    }

    @Override
    public ListaContatti toEntity(ListaContattiExtDTO listaContattiDTO) {
        if ( listaContattiDTO == null ) {
            return null;
        }

        ListaContatti listaContatti = new ListaContatti();

        listaContatti.setIncarico( incaricoMapper.fromId( listaContattiDTO.getIncaricoId() ) );
        listaContatti.setCreatedBy( listaContattiDTO.getCreatedBy() );
        listaContatti.setCreatedDate( listaContattiDTO.getCreatedDate() );
        listaContatti.setLastModifiedBy( listaContattiDTO.getLastModifiedBy() );
        listaContatti.setLastModifiedDate( listaContattiDTO.getLastModifiedDate() );
        listaContatti.setId( listaContattiDTO.getId() );
        listaContatti.setDateTime( listaContattiDTO.getDateTime() );
        listaContatti.setEsito( listaContattiDTO.getEsito() );
        listaContatti.setMotivazione( listaContattiDTO.getMotivazione() );
        listaContatti.setNote( listaContattiDTO.getNote() );

        return listaContatti;
    }

    private String listaContattiIncaricoRiferimento(ListaContatti listaContatti) {
        if ( listaContatti == null ) {
            return null;
        }
        Incarico incarico = listaContatti.getIncarico();
        if ( incarico == null ) {
            return null;
        }
        String riferimento = incarico.getRiferimento();
        if ( riferimento == null ) {
            return null;
        }
        return riferimento;
    }

    private Long listaContattiIncaricoId(ListaContatti listaContatti) {
        if ( listaContatti == null ) {
            return null;
        }
        Incarico incarico = listaContatti.getIncarico();
        if ( incarico == null ) {
            return null;
        }
        Long id = incarico.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
