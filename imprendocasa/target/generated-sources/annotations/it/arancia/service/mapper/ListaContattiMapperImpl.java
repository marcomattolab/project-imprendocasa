package it.arancia.service.mapper;

import it.arancia.domain.Cliente;
import it.arancia.domain.Incarico;
import it.arancia.domain.ListaContatti;
import it.arancia.service.dto.ListaContattiDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-29T14:29:19+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class ListaContattiMapperImpl implements ListaContattiMapper {

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private IncaricoMapper incaricoMapper;

    @Override
    public List<ListaContatti> toEntity(List<ListaContattiDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ListaContatti> list = new ArrayList<ListaContatti>( dtoList.size() );
        for ( ListaContattiDTO listaContattiDTO : dtoList ) {
            list.add( toEntity( listaContattiDTO ) );
        }

        return list;
    }

    @Override
    public List<ListaContattiDTO> toDto(List<ListaContatti> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ListaContattiDTO> list = new ArrayList<ListaContattiDTO>( entityList.size() );
        for ( ListaContatti listaContatti : entityList ) {
            list.add( toDto( listaContatti ) );
        }

        return list;
    }

    @Override
    public ListaContattiDTO toDto(ListaContatti listaContatti) {
        if ( listaContatti == null ) {
            return null;
        }

        ListaContattiDTO listaContattiDTO = new ListaContattiDTO();

        String riferimento = listaContattiIncaricoRiferimento( listaContatti );
        if ( riferimento != null ) {
            listaContattiDTO.setIncaricoRiferimento( riferimento );
        }
        Long id = listaContattiClienteId( listaContatti );
        if ( id != null ) {
            listaContattiDTO.setClienteId( id );
        }
        Long id1 = listaContattiIncaricoId( listaContatti );
        if ( id1 != null ) {
            listaContattiDTO.setIncaricoId( id1 );
        }
        String cognome = listaContattiClienteCognome( listaContatti );
        if ( cognome != null ) {
            listaContattiDTO.setClienteCognome( cognome );
        }
        listaContattiDTO.setCreatedBy( listaContatti.getCreatedBy() );
        listaContattiDTO.setCreatedDate( listaContatti.getCreatedDate() );
        listaContattiDTO.setLastModifiedBy( listaContatti.getLastModifiedBy() );
        listaContattiDTO.setLastModifiedDate( listaContatti.getLastModifiedDate() );
        listaContattiDTO.setId( listaContatti.getId() );
        listaContattiDTO.setDateTime( listaContatti.getDateTime() );
        listaContattiDTO.setEsito( listaContatti.getEsito() );
        listaContattiDTO.setMotivazione( listaContatti.getMotivazione() );
        listaContattiDTO.setNote( listaContatti.getNote() );

        return listaContattiDTO;
    }

    @Override
    public ListaContatti toEntity(ListaContattiDTO listaContattiDTO) {
        if ( listaContattiDTO == null ) {
            return null;
        }

        ListaContatti listaContatti = new ListaContatti();

        listaContatti.setCliente( clienteMapper.fromId( listaContattiDTO.getClienteId() ) );
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

    private Long listaContattiClienteId(ListaContatti listaContatti) {
        if ( listaContatti == null ) {
            return null;
        }
        Cliente cliente = listaContatti.getCliente();
        if ( cliente == null ) {
            return null;
        }
        Long id = cliente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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

    private String listaContattiClienteCognome(ListaContatti listaContatti) {
        if ( listaContatti == null ) {
            return null;
        }
        Cliente cliente = listaContatti.getCliente();
        if ( cliente == null ) {
            return null;
        }
        String cognome = cliente.getCognome();
        if ( cognome == null ) {
            return null;
        }
        return cognome;
    }
}
