package it.arancia.service.mapper;

import it.arancia.domain.Cliente;
import it.arancia.domain.Tag;
import it.arancia.service.dto.ClienteDTO;
import it.arancia.service.dto.TagDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-29T14:29:18+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public ClienteDTO toDto(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setCreatedBy( entity.getCreatedBy() );
        clienteDTO.setCreatedDate( entity.getCreatedDate() );
        clienteDTO.setLastModifiedBy( entity.getLastModifiedBy() );
        clienteDTO.setLastModifiedDate( entity.getLastModifiedDate() );
        clienteDTO.setId( entity.getId() );
        clienteDTO.setNome( entity.getNome() );
        clienteDTO.setCognome( entity.getCognome() );
        clienteDTO.setCodiceFiscale( entity.getCodiceFiscale() );
        clienteDTO.setTitolo( entity.getTitolo() );
        clienteDTO.setRagioneSociale( entity.getRagioneSociale() );
        clienteDTO.setAgente( entity.getAgente() );
        clienteDTO.setAlertCompleanno( entity.getAlertCompleanno() );
        clienteDTO.setDataNascita( entity.getDataNascita() );
        clienteDTO.setMeseNascita( entity.getMeseNascita() );
        clienteDTO.setTelefonoFisso( entity.getTelefonoFisso() );
        clienteDTO.setTelefonoCellulare( entity.getTelefonoCellulare() );
        clienteDTO.setEmail( entity.getEmail() );
        clienteDTO.setIndirizzo( entity.getIndirizzo() );
        clienteDTO.setCap( entity.getCap() );
        clienteDTO.setRegione( entity.getRegione() );
        clienteDTO.setProvincia( entity.getProvincia() );
        clienteDTO.setCitta( entity.getCitta() );
        clienteDTO.setNote( entity.getNote() );
        clienteDTO.setCodiceAntiriciclaggio( entity.getCodiceAntiriciclaggio() );
        clienteDTO.setImportoProvvigioni( entity.getImportoProvvigioni() );
        clienteDTO.setNumeroPratiche( entity.getNumeroPratiche() );
        clienteDTO.setNumeroSegnalazioni( entity.getNumeroSegnalazioni() );
        clienteDTO.setPunteggio( entity.getPunteggio() );
        clienteDTO.setAbilitato( entity.isAbilitato() );
        clienteDTO.setTags( tagSetToTagDTOSet( entity.getTags() ) );
        clienteDTO.setImportoProvvigioniDerivate( entity.getImportoProvvigioniDerivate() );

        return clienteDTO;
    }

    @Override
    public List<Cliente> toEntity(List<ClienteDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( dtoList.size() );
        for ( ClienteDTO clienteDTO : dtoList ) {
            list.add( toEntity( clienteDTO ) );
        }

        return list;
    }

    @Override
    public List<ClienteDTO> toDto(List<Cliente> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( entityList.size() );
        for ( Cliente cliente : entityList ) {
            list.add( toDto( cliente ) );
        }

        return list;
    }

    @Override
    public Cliente toEntity(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setCreatedBy( clienteDTO.getCreatedBy() );
        cliente.setCreatedDate( clienteDTO.getCreatedDate() );
        cliente.setLastModifiedBy( clienteDTO.getLastModifiedBy() );
        cliente.setLastModifiedDate( clienteDTO.getLastModifiedDate() );
        cliente.setId( clienteDTO.getId() );
        cliente.setNome( clienteDTO.getNome() );
        cliente.setCognome( clienteDTO.getCognome() );
        cliente.setCodiceFiscale( clienteDTO.getCodiceFiscale() );
        cliente.setAlertCompleanno( clienteDTO.getAlertCompleanno() );
        cliente.setDataNascita( clienteDTO.getDataNascita() );
        cliente.setMeseNascita( clienteDTO.getMeseNascita() );
        cliente.setTelefonoFisso( clienteDTO.getTelefonoFisso() );
        cliente.setTelefonoCellulare( clienteDTO.getTelefonoCellulare() );
        cliente.setEmail( clienteDTO.getEmail() );
        cliente.setIndirizzo( clienteDTO.getIndirizzo() );
        cliente.setCap( clienteDTO.getCap() );
        cliente.setRegione( clienteDTO.getRegione() );
        cliente.setProvincia( clienteDTO.getProvincia() );
        cliente.setCitta( clienteDTO.getCitta() );
        cliente.setNote( clienteDTO.getNote() );
        cliente.setCodiceAntiriciclaggio( clienteDTO.getCodiceAntiriciclaggio() );
        cliente.setImportoProvvigioni( clienteDTO.getImportoProvvigioni() );
        cliente.setNumeroPratiche( clienteDTO.getNumeroPratiche() );
        cliente.setNumeroSegnalazioni( clienteDTO.getNumeroSegnalazioni() );
        cliente.setPunteggio( clienteDTO.getPunteggio() );
        cliente.setAbilitato( clienteDTO.isAbilitato() );
        cliente.setTitolo( clienteDTO.getTitolo() );
        cliente.setRagioneSociale( clienteDTO.getRagioneSociale() );
        cliente.setAgente( clienteDTO.getAgente() );
        cliente.setImportoProvvigioniDerivate( clienteDTO.getImportoProvvigioniDerivate() );
        cliente.setTags( tagDTOSetToTagSet( clienteDTO.getTags() ) );

        return cliente;
    }

    protected Set<TagDTO> tagSetToTagDTOSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<TagDTO> set1 = new HashSet<TagDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tag tag : set ) {
            set1.add( tagMapper.toDto( tag ) );
        }

        return set1;
    }

    protected Set<Tag> tagDTOSetToTagSet(Set<TagDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Tag> set1 = new HashSet<Tag>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TagDTO tagDTO : set ) {
            set1.add( tagMapper.toEntity( tagDTO ) );
        }

        return set1;
    }
}
