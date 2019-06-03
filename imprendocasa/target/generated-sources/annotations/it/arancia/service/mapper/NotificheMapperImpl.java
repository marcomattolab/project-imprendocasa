package it.arancia.service.mapper;

import it.arancia.domain.Notifiche;
import it.arancia.service.dto.NotificheDTO;
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
public class NotificheMapperImpl implements NotificheMapper {

    @Override
    public Notifiche toEntity(NotificheDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Notifiche notifiche = new Notifiche();

        notifiche.setCreatedBy( dto.getCreatedBy() );
        notifiche.setCreatedDate( dto.getCreatedDate() );
        notifiche.setLastModifiedBy( dto.getLastModifiedBy() );
        notifiche.setLastModifiedDate( dto.getLastModifiedDate() );
        notifiche.setId( dto.getId() );
        notifiche.setCanaleNotifica( dto.getCanaleNotifica() );
        notifiche.setTipoNotifica( dto.getTipoNotifica() );
        notifiche.setCategoriaNotifica( dto.getCategoriaNotifica() );
        notifiche.setOggettoNotifica( dto.getOggettoNotifica() );
        notifiche.setTestoNotifica( dto.getTestoNotifica() );
        notifiche.setNumeroDestinatari( dto.getNumeroDestinatari() );
        notifiche.setDestinatariNotifica( dto.getDestinatariNotifica() );
        notifiche.setEsitoNotifica( dto.getEsitoNotifica() );
        notifiche.setDettagliEsito( dto.getDettagliEsito() );

        return notifiche;
    }

    @Override
    public NotificheDTO toDto(Notifiche entity) {
        if ( entity == null ) {
            return null;
        }

        NotificheDTO notificheDTO = new NotificheDTO();

        notificheDTO.setCreatedBy( entity.getCreatedBy() );
        notificheDTO.setCreatedDate( entity.getCreatedDate() );
        notificheDTO.setLastModifiedBy( entity.getLastModifiedBy() );
        notificheDTO.setLastModifiedDate( entity.getLastModifiedDate() );
        notificheDTO.setId( entity.getId() );
        notificheDTO.setCanaleNotifica( entity.getCanaleNotifica() );
        notificheDTO.setTipoNotifica( entity.getTipoNotifica() );
        notificheDTO.setCategoriaNotifica( entity.getCategoriaNotifica() );
        notificheDTO.setOggettoNotifica( entity.getOggettoNotifica() );
        notificheDTO.setTestoNotifica( entity.getTestoNotifica() );
        notificheDTO.setNumeroDestinatari( entity.getNumeroDestinatari() );
        notificheDTO.setDestinatariNotifica( entity.getDestinatariNotifica() );
        notificheDTO.setEsitoNotifica( entity.getEsitoNotifica() );
        notificheDTO.setDettagliEsito( entity.getDettagliEsito() );

        return notificheDTO;
    }

    @Override
    public List<Notifiche> toEntity(List<NotificheDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Notifiche> list = new ArrayList<Notifiche>( dtoList.size() );
        for ( NotificheDTO notificheDTO : dtoList ) {
            list.add( toEntity( notificheDTO ) );
        }

        return list;
    }

    @Override
    public List<NotificheDTO> toDto(List<Notifiche> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NotificheDTO> list = new ArrayList<NotificheDTO>( entityList.size() );
        for ( Notifiche notifiche : entityList ) {
            list.add( toDto( notifiche ) );
        }

        return list;
    }
}
