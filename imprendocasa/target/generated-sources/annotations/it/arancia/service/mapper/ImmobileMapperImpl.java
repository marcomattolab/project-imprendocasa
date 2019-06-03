package it.arancia.service.mapper;

import it.arancia.domain.Geolocalizzazione;
import it.arancia.domain.Immobile;
import it.arancia.service.dto.ImmobileDTO;
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
public class ImmobileMapperImpl implements ImmobileMapper {

    @Autowired
    private GeolocalizzazioneMapper geolocalizzazioneMapper;

    @Override
    public List<Immobile> toEntity(List<ImmobileDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Immobile> list = new ArrayList<Immobile>( dtoList.size() );
        for ( ImmobileDTO immobileDTO : dtoList ) {
            list.add( toEntity( immobileDTO ) );
        }

        return list;
    }

    @Override
    public List<ImmobileDTO> toDto(List<Immobile> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ImmobileDTO> list = new ArrayList<ImmobileDTO>( entityList.size() );
        for ( Immobile immobile : entityList ) {
            list.add( toDto( immobile ) );
        }

        return list;
    }

    @Override
    public ImmobileDTO toDto(Immobile immobile) {
        if ( immobile == null ) {
            return null;
        }

        ImmobileDTO immobileDTO = new ImmobileDTO();

        Long id = immobileGeolocalizzazioneId( immobile );
        if ( id != null ) {
            immobileDTO.setGeolocalizzazioneId( id );
        }
        String immobile1 = immobileGeolocalizzazioneImmobile( immobile );
        if ( immobile1 != null ) {
            immobileDTO.setGeolocalizzazioneImmobile( immobile1 );
        }
        immobileDTO.setCreatedBy( immobile.getCreatedBy() );
        immobileDTO.setCreatedDate( immobile.getCreatedDate() );
        immobileDTO.setLastModifiedBy( immobile.getLastModifiedBy() );
        immobileDTO.setLastModifiedDate( immobile.getLastModifiedDate() );
        immobileDTO.setId( immobile.getId() );
        immobileDTO.setAgente( immobile.getAgente() );
        immobileDTO.setCodice( immobile.getCodice() );
        immobileDTO.setDescrizione( immobile.getDescrizione() );
        immobileDTO.setIndirizzo( immobile.getIndirizzo() );
        immobileDTO.setCap( immobile.getCap() );
        immobileDTO.setRegione( immobile.getRegione() );
        immobileDTO.setProvincia( immobile.getProvincia() );
        immobileDTO.setCitta( immobile.getCitta() );
        immobileDTO.setNote( immobile.getNote() );
        immobileDTO.setPathFolder( immobile.getPathFolder() );
        immobileDTO.setDatiCatastali( immobile.getDatiCatastali() );
        immobileDTO.setFoglio( immobile.getFoglio() );
        immobileDTO.setParticella( immobile.getParticella() );
        immobileDTO.setSub( immobile.getSub() );

        return immobileDTO;
    }

    @Override
    public Immobile toEntity(ImmobileDTO immobileDTO) {
        if ( immobileDTO == null ) {
            return null;
        }

        Immobile immobile = new Immobile();

        immobile.setGeolocalizzazione( geolocalizzazioneMapper.fromId( immobileDTO.getGeolocalizzazioneId() ) );
        immobile.setCreatedBy( immobileDTO.getCreatedBy() );
        immobile.setCreatedDate( immobileDTO.getCreatedDate() );
        immobile.setLastModifiedBy( immobileDTO.getLastModifiedBy() );
        immobile.setLastModifiedDate( immobileDTO.getLastModifiedDate() );
        immobile.setId( immobileDTO.getId() );
        immobile.setAgente( immobileDTO.getAgente() );
        immobile.setCodice( immobileDTO.getCodice() );
        immobile.setDescrizione( immobileDTO.getDescrizione() );
        immobile.setIndirizzo( immobileDTO.getIndirizzo() );
        immobile.setCap( immobileDTO.getCap() );
        immobile.setRegione( immobileDTO.getRegione() );
        immobile.setProvincia( immobileDTO.getProvincia() );
        immobile.setCitta( immobileDTO.getCitta() );
        immobile.setNote( immobileDTO.getNote() );
        immobile.setPathFolder( immobileDTO.getPathFolder() );
        immobile.setDatiCatastali( immobileDTO.getDatiCatastali() );
        immobile.setFoglio( immobileDTO.getFoglio() );
        immobile.setParticella( immobileDTO.getParticella() );
        immobile.setSub( immobileDTO.getSub() );

        return immobile;
    }

    private Long immobileGeolocalizzazioneId(Immobile immobile) {
        if ( immobile == null ) {
            return null;
        }
        Geolocalizzazione geolocalizzazione = immobile.getGeolocalizzazione();
        if ( geolocalizzazione == null ) {
            return null;
        }
        Long id = geolocalizzazione.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String immobileGeolocalizzazioneImmobile(Immobile immobile) {
        if ( immobile == null ) {
            return null;
        }
        Geolocalizzazione geolocalizzazione = immobile.getGeolocalizzazione();
        if ( geolocalizzazione == null ) {
            return null;
        }
        String immobile1 = geolocalizzazione.getImmobile();
        if ( immobile1 == null ) {
            return null;
        }
        return immobile1;
    }
}
