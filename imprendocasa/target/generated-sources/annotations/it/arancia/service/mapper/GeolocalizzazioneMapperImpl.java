package it.arancia.service.mapper;

import it.arancia.domain.Geolocalizzazione;
import it.arancia.service.dto.GeolocalizzazioneDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-29T14:29:19+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class GeolocalizzazioneMapperImpl implements GeolocalizzazioneMapper {

    @Override
    public GeolocalizzazioneDTO toDto(Geolocalizzazione entity) {
        if ( entity == null ) {
            return null;
        }

        GeolocalizzazioneDTO geolocalizzazioneDTO = new GeolocalizzazioneDTO();

        geolocalizzazioneDTO.setCreatedBy( entity.getCreatedBy() );
        geolocalizzazioneDTO.setCreatedDate( entity.getCreatedDate() );
        geolocalizzazioneDTO.setLastModifiedBy( entity.getLastModifiedBy() );
        geolocalizzazioneDTO.setLastModifiedDate( entity.getLastModifiedDate() );
        geolocalizzazioneDTO.setId( entity.getId() );
        geolocalizzazioneDTO.setImmobile( entity.getImmobile() );
        geolocalizzazioneDTO.setLatitudine( entity.getLatitudine() );
        geolocalizzazioneDTO.setLongitudine( entity.getLongitudine() );

        return geolocalizzazioneDTO;
    }

    @Override
    public List<Geolocalizzazione> toEntity(List<GeolocalizzazioneDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Geolocalizzazione> list = new ArrayList<Geolocalizzazione>( dtoList.size() );
        for ( GeolocalizzazioneDTO geolocalizzazioneDTO : dtoList ) {
            list.add( toEntity( geolocalizzazioneDTO ) );
        }

        return list;
    }

    @Override
    public List<GeolocalizzazioneDTO> toDto(List<Geolocalizzazione> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<GeolocalizzazioneDTO> list = new ArrayList<GeolocalizzazioneDTO>( entityList.size() );
        for ( Geolocalizzazione geolocalizzazione : entityList ) {
            list.add( toDto( geolocalizzazione ) );
        }

        return list;
    }

    @Override
    public Geolocalizzazione toEntity(GeolocalizzazioneDTO geolocalizzazioneDTO) {
        if ( geolocalizzazioneDTO == null ) {
            return null;
        }

        Geolocalizzazione geolocalizzazione = new Geolocalizzazione();

        geolocalizzazione.setCreatedBy( geolocalizzazioneDTO.getCreatedBy() );
        geolocalizzazione.setCreatedDate( geolocalizzazioneDTO.getCreatedDate() );
        geolocalizzazione.setLastModifiedBy( geolocalizzazioneDTO.getLastModifiedBy() );
        geolocalizzazione.setLastModifiedDate( geolocalizzazioneDTO.getLastModifiedDate() );
        geolocalizzazione.setId( geolocalizzazioneDTO.getId() );
        geolocalizzazione.setImmobile( geolocalizzazioneDTO.getImmobile() );
        geolocalizzazione.setLatitudine( geolocalizzazioneDTO.getLatitudine() );
        geolocalizzazione.setLongitudine( geolocalizzazioneDTO.getLongitudine() );

        return geolocalizzazione;
    }
}
