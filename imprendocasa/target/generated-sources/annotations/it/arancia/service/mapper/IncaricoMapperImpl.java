package it.arancia.service.mapper;

import it.arancia.domain.Cliente;
import it.arancia.domain.Immobile;
import it.arancia.domain.Incarico;
import it.arancia.domain.Partner;
import it.arancia.service.dto.ClienteDTO;
import it.arancia.service.dto.IncaricoDTO;
import it.arancia.service.dto.PartnerDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-29T14:29:19+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class IncaricoMapperImpl implements IncaricoMapper {

    @Autowired
    private ImmobileMapper immobileMapper;
    @Autowired
    private PartnerMapper partnerMapper;
    @Autowired
    private ClienteMapper clienteMapper;

    @Override
    public List<Incarico> toEntity(List<IncaricoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Incarico> list = new ArrayList<Incarico>( dtoList.size() );
        for ( IncaricoDTO incaricoDTO : dtoList ) {
            list.add( toEntity( incaricoDTO ) );
        }

        return list;
    }

    @Override
    public List<IncaricoDTO> toDto(List<Incarico> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<IncaricoDTO> list = new ArrayList<IncaricoDTO>( entityList.size() );
        for ( Incarico incarico : entityList ) {
            list.add( toDto( incarico ) );
        }

        return list;
    }

    @Override
    public IncaricoDTO toDto(Incarico incarico) {
        if ( incarico == null ) {
            return null;
        }

        IncaricoDTO incaricoDTO = new IncaricoDTO();

        String codice = incaricoImmobileCodice( incarico );
        if ( codice != null ) {
            incaricoDTO.setImmobileCodice( codice );
        }
        Long id = incaricoImmobileId( incarico );
        if ( id != null ) {
            incaricoDTO.setImmobileId( id );
        }
        incaricoDTO.setCreatedBy( incarico.getCreatedBy() );
        incaricoDTO.setCreatedDate( incarico.getCreatedDate() );
        incaricoDTO.setLastModifiedBy( incarico.getLastModifiedBy() );
        incaricoDTO.setLastModifiedDate( incarico.getLastModifiedDate() );
        incaricoDTO.setId( incarico.getId() );
        incaricoDTO.setRiferimento( incarico.getRiferimento() );
        incaricoDTO.setTipo( incarico.getTipo() );
        incaricoDTO.setStato( incarico.getStato() );
        incaricoDTO.setDataScadenza( incarico.getDataScadenza() );
        incaricoDTO.setAgente( incarico.getAgente() );
        incaricoDTO.setAgenteDiContatto( incarico.getAgenteDiContatto() );
        incaricoDTO.setDataContatto( incarico.getDataContatto() );
        incaricoDTO.setNoteTrattativa( incarico.getNoteTrattativa() );
        incaricoDTO.setDatiFiscali( incarico.getDatiFiscali() );
        incaricoDTO.setAlertFiscali( incarico.getAlertFiscali() );
        incaricoDTO.setAlertCortesia( incarico.getAlertCortesia() );
        incaricoDTO.setPrivacy( incarico.isPrivacy() );
        incaricoDTO.setAntiriciclaggio( incarico.isAntiriciclaggio() );
        incaricoDTO.setPrezzoRichiesta( incarico.getPrezzoRichiesta() );
        incaricoDTO.setPrezzoMinimo( incarico.getPrezzoMinimo() );
        incaricoDTO.setPrezzoAcquisto( incarico.getPrezzoAcquisto() );
        incaricoDTO.setImportoProvvigione( incarico.getImportoProvvigione() );
        incaricoDTO.setOscuraIncarico( incarico.isOscuraIncarico() );
        incaricoDTO.setFlagLocato( incarico.isFlagLocato() );
        incaricoDTO.setNomeConduttore( incarico.getNomeConduttore() );
        incaricoDTO.setCognomeConduttore( incarico.getCognomeConduttore() );
        incaricoDTO.setDataInizioLocazione( incarico.getDataInizioLocazione() );
        incaricoDTO.setDataFineLocazione( incarico.getDataFineLocazione() );
        incaricoDTO.setNoteLocazione( incarico.getNoteLocazione() );
        incaricoDTO.setPartners( partnerSetToPartnerDTOSet( incarico.getPartners() ) );
        incaricoDTO.setCommittentes( clienteSetToClienteDTOSet( incarico.getCommittentes() ) );
        incaricoDTO.setProponentes( clienteSetToClienteDTOSet( incarico.getProponentes() ) );
        incaricoDTO.setAcquirenteLocatarios( clienteSetToClienteDTOSet( incarico.getAcquirenteLocatarios() ) );
        incaricoDTO.setSegnalatores( clienteSetToClienteDTOSet( incarico.getSegnalatores() ) );
        incaricoDTO.setCategoriaIncarico( incarico.getCategoriaIncarico() );
        incaricoDTO.setDataAlertFiscali( incarico.getDataAlertFiscali() );
        incaricoDTO.setRicorrenzaAlertFiscali( incarico.getRicorrenzaAlertFiscali() );
        incaricoDTO.setDataAlertCortesia( incarico.getDataAlertCortesia() );
        incaricoDTO.setRicorrenzaAlertCortesia( incarico.getRicorrenzaAlertCortesia() );
        incaricoDTO.setAlertRicorrenza( incarico.getAlertRicorrenza() );
        incaricoDTO.setDataAlertRicorrenza( incarico.getDataAlertRicorrenza() );
        incaricoDTO.setRicorrenzaAlertRicorrenza( incarico.getRicorrenzaAlertRicorrenza() );
        incaricoDTO.setAntiriciclaggioAcquirenti( incarico.isAntiriciclaggioAcquirenti() );
        incaricoDTO.setImportoProvvigioneAcquirenti( incarico.getImportoProvvigioneAcquirenti() );
        incaricoDTO.setCanoneCorrisposto( incarico.getCanoneCorrisposto() );
        incaricoDTO.setTelefonoConduttore( incarico.getTelefonoConduttore() );

        return incaricoDTO;
    }

    @Override
    public Incarico toEntity(IncaricoDTO incaricoDTO) {
        if ( incaricoDTO == null ) {
            return null;
        }

        Incarico incarico = new Incarico();

        incarico.setImmobile( immobileMapper.fromId( incaricoDTO.getImmobileId() ) );
        incarico.setCreatedBy( incaricoDTO.getCreatedBy() );
        incarico.setCreatedDate( incaricoDTO.getCreatedDate() );
        incarico.setLastModifiedBy( incaricoDTO.getLastModifiedBy() );
        incarico.setLastModifiedDate( incaricoDTO.getLastModifiedDate() );
        incarico.setId( incaricoDTO.getId() );
        incarico.setRiferimento( incaricoDTO.getRiferimento() );
        incarico.setTipo( incaricoDTO.getTipo() );
        incarico.setStato( incaricoDTO.getStato() );
        incarico.setDataScadenza( incaricoDTO.getDataScadenza() );
        incarico.setAgente( incaricoDTO.getAgente() );
        incarico.setAgenteDiContatto( incaricoDTO.getAgenteDiContatto() );
        incarico.setDataContatto( incaricoDTO.getDataContatto() );
        incarico.setNoteTrattativa( incaricoDTO.getNoteTrattativa() );
        incarico.setDatiFiscali( incaricoDTO.getDatiFiscali() );
        incarico.setAlertFiscali( incaricoDTO.getAlertFiscali() );
        incarico.setAlertCortesia( incaricoDTO.getAlertCortesia() );
        incarico.setPrivacy( incaricoDTO.getPrivacy() );
        incarico.setAntiriciclaggio( incaricoDTO.getAntiriciclaggio() );
        incarico.setPrezzoRichiesta( incaricoDTO.getPrezzoRichiesta() );
        incarico.setPrezzoMinimo( incaricoDTO.getPrezzoMinimo() );
        incarico.setPrezzoAcquisto( incaricoDTO.getPrezzoAcquisto() );
        incarico.setCategoriaIncarico( incaricoDTO.getCategoriaIncarico() );
        incarico.setImportoProvvigione( incaricoDTO.getImportoProvvigione() );
        incarico.setOscuraIncarico( incaricoDTO.getOscuraIncarico() );
        incarico.setDataAlertFiscali( incaricoDTO.getDataAlertFiscali() );
        incarico.setRicorrenzaAlertFiscali( incaricoDTO.getRicorrenzaAlertFiscali() );
        incarico.setDataAlertCortesia( incaricoDTO.getDataAlertCortesia() );
        incarico.setRicorrenzaAlertCortesia( incaricoDTO.getRicorrenzaAlertCortesia() );
        incarico.setAlertRicorrenza( incaricoDTO.getAlertRicorrenza() );
        incarico.setDataAlertRicorrenza( incaricoDTO.getDataAlertRicorrenza() );
        incarico.setRicorrenzaAlertRicorrenza( incaricoDTO.getRicorrenzaAlertRicorrenza() );
        incarico.setPrivacyAcquirenti( incaricoDTO.getPrivacyAcquirenti() );
        incarico.setAntiriciclaggioAcquirenti( incaricoDTO.isAntiriciclaggioAcquirenti() );
        incarico.setImportoProvvigioneAcquirenti( incaricoDTO.getImportoProvvigioneAcquirenti() );
        incarico.setFlagLocato( incaricoDTO.getFlagLocato() );
        incarico.setNomeConduttore( incaricoDTO.getNomeConduttore() );
        incarico.setCognomeConduttore( incaricoDTO.getCognomeConduttore() );
        incarico.setCanoneCorrisposto( incaricoDTO.getCanoneCorrisposto() );
        incarico.setDataInizioLocazione( incaricoDTO.getDataInizioLocazione() );
        incarico.setDataFineLocazione( incaricoDTO.getDataFineLocazione() );
        incarico.setNoteLocazione( incaricoDTO.getNoteLocazione() );
        incarico.setPartners( partnerDTOSetToPartnerSet( incaricoDTO.getPartners() ) );
        incarico.setCommittentes( clienteDTOSetToClienteSet( incaricoDTO.getCommittentes() ) );
        incarico.setProponentes( clienteDTOSetToClienteSet( incaricoDTO.getProponentes() ) );
        incarico.setAcquirenteLocatarios( clienteDTOSetToClienteSet( incaricoDTO.getAcquirenteLocatarios() ) );
        incarico.setSegnalatores( clienteDTOSetToClienteSet( incaricoDTO.getSegnalatores() ) );

        return incarico;
    }

    private String incaricoImmobileCodice(Incarico incarico) {
        if ( incarico == null ) {
            return null;
        }
        Immobile immobile = incarico.getImmobile();
        if ( immobile == null ) {
            return null;
        }
        String codice = immobile.getCodice();
        if ( codice == null ) {
            return null;
        }
        return codice;
    }

    private Long incaricoImmobileId(Incarico incarico) {
        if ( incarico == null ) {
            return null;
        }
        Immobile immobile = incarico.getImmobile();
        if ( immobile == null ) {
            return null;
        }
        Long id = immobile.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Set<PartnerDTO> partnerSetToPartnerDTOSet(Set<Partner> set) {
        if ( set == null ) {
            return null;
        }

        Set<PartnerDTO> set1 = new HashSet<PartnerDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Partner partner : set ) {
            set1.add( partnerMapper.toDto( partner ) );
        }

        return set1;
    }

    protected Set<ClienteDTO> clienteSetToClienteDTOSet(Set<Cliente> set) {
        if ( set == null ) {
            return null;
        }

        Set<ClienteDTO> set1 = new HashSet<ClienteDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Cliente cliente : set ) {
            set1.add( clienteMapper.toDto( cliente ) );
        }

        return set1;
    }

    protected Set<Partner> partnerDTOSetToPartnerSet(Set<PartnerDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Partner> set1 = new HashSet<Partner>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PartnerDTO partnerDTO : set ) {
            set1.add( partnerMapper.toEntity( partnerDTO ) );
        }

        return set1;
    }

    protected Set<Cliente> clienteDTOSetToClienteSet(Set<ClienteDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Cliente> set1 = new HashSet<Cliente>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ClienteDTO clienteDTO : set ) {
            set1.add( clienteMapper.toEntity( clienteDTO ) );
        }

        return set1;
    }
}
