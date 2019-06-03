package it.arancia.service.mapper;

import it.arancia.domain.Partner;
import it.arancia.domain.Professione;
import it.arancia.service.dto.PartnerDTO;
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
public class PartnerMapperImpl implements PartnerMapper {

    @Autowired
    private ProfessioneMapper professioneMapper;

    @Override
    public List<Partner> toEntity(List<PartnerDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Partner> list = new ArrayList<Partner>( dtoList.size() );
        for ( PartnerDTO partnerDTO : dtoList ) {
            list.add( toEntity( partnerDTO ) );
        }

        return list;
    }

    @Override
    public List<PartnerDTO> toDto(List<Partner> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PartnerDTO> list = new ArrayList<PartnerDTO>( entityList.size() );
        for ( Partner partner : entityList ) {
            list.add( toDto( partner ) );
        }

        return list;
    }

    @Override
    public PartnerDTO toDto(Partner partner) {
        if ( partner == null ) {
            return null;
        }

        PartnerDTO partnerDTO = new PartnerDTO();

        String denominazione = partnerProfessioneDenominazione( partner );
        if ( denominazione != null ) {
            partnerDTO.setProfessioneDenominazione( denominazione );
        }
        Long id = partnerProfessioneId( partner );
        if ( id != null ) {
            partnerDTO.setProfessioneId( id );
        }
        partnerDTO.setCreatedBy( partner.getCreatedBy() );
        partnerDTO.setCreatedDate( partner.getCreatedDate() );
        partnerDTO.setLastModifiedBy( partner.getLastModifiedBy() );
        partnerDTO.setLastModifiedDate( partner.getLastModifiedDate() );
        partnerDTO.setId( partner.getId() );
        partnerDTO.setNome( partner.getNome() );
        partnerDTO.setCognome( partner.getCognome() );
        partnerDTO.setCodiceFiscale( partner.getCodiceFiscale() );
        partnerDTO.setTelefonoFisso( partner.getTelefonoFisso() );
        partnerDTO.setTelefonoCellulare( partner.getTelefonoCellulare() );
        partnerDTO.setEmail( partner.getEmail() );
        partnerDTO.setTipoIndirizzo( partner.getTipoIndirizzo() );
        partnerDTO.setIndirizzo( partner.getIndirizzo() );
        partnerDTO.setCap( partner.getCap() );
        partnerDTO.setRegione( partner.getRegione() );
        partnerDTO.setProvincia( partner.getProvincia() );
        partnerDTO.setCitta( partner.getCitta() );
        partnerDTO.setNote( partner.getNote() );
        partnerDTO.setAbilitato( partner.isAbilitato() );

        return partnerDTO;
    }

    @Override
    public Partner toEntity(PartnerDTO partnerDTO) {
        if ( partnerDTO == null ) {
            return null;
        }

        Partner partner = new Partner();

        partner.setProfessione( professioneMapper.fromId( partnerDTO.getProfessioneId() ) );
        partner.setCreatedBy( partnerDTO.getCreatedBy() );
        partner.setCreatedDate( partnerDTO.getCreatedDate() );
        partner.setLastModifiedBy( partnerDTO.getLastModifiedBy() );
        partner.setLastModifiedDate( partnerDTO.getLastModifiedDate() );
        partner.setId( partnerDTO.getId() );
        partner.setNome( partnerDTO.getNome() );
        partner.setCognome( partnerDTO.getCognome() );
        partner.setCodiceFiscale( partnerDTO.getCodiceFiscale() );
        partner.setTelefonoFisso( partnerDTO.getTelefonoFisso() );
        partner.setTelefonoCellulare( partnerDTO.getTelefonoCellulare() );
        partner.setEmail( partnerDTO.getEmail() );
        partner.setTipoIndirizzo( partnerDTO.getTipoIndirizzo() );
        partner.setIndirizzo( partnerDTO.getIndirizzo() );
        partner.setCap( partnerDTO.getCap() );
        partner.setRegione( partnerDTO.getRegione() );
        partner.setProvincia( partnerDTO.getProvincia() );
        partner.setCitta( partnerDTO.getCitta() );
        partner.setNote( partnerDTO.getNote() );
        partner.setAbilitato( partnerDTO.isAbilitato() );

        return partner;
    }

    private String partnerProfessioneDenominazione(Partner partner) {
        if ( partner == null ) {
            return null;
        }
        Professione professione = partner.getProfessione();
        if ( professione == null ) {
            return null;
        }
        String denominazione = professione.getDenominazione();
        if ( denominazione == null ) {
            return null;
        }
        return denominazione;
    }

    private Long partnerProfessioneId(Partner partner) {
        if ( partner == null ) {
            return null;
        }
        Professione professione = partner.getProfessione();
        if ( professione == null ) {
            return null;
        }
        Long id = professione.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
