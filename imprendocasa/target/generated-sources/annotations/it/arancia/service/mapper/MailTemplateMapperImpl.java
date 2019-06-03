package it.arancia.service.mapper;

import it.arancia.domain.MailTemplate;
import it.arancia.service.dto.MailTemplateDTO;
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
public class MailTemplateMapperImpl implements MailTemplateMapper {

    @Override
    public MailTemplate toEntity(MailTemplateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MailTemplate mailTemplate = new MailTemplate();

        mailTemplate.setCreatedBy( dto.getCreatedBy() );
        mailTemplate.setCreatedDate( dto.getCreatedDate() );
        mailTemplate.setLastModifiedBy( dto.getLastModifiedBy() );
        mailTemplate.setLastModifiedDate( dto.getLastModifiedDate() );
        mailTemplate.setId( dto.getId() );
        mailTemplate.setCodice( dto.getCodice() );
        mailTemplate.setCategoria( dto.getCategoria() );
        mailTemplate.setOggetto( dto.getOggetto() );
        mailTemplate.setModello( dto.getModello() );
        mailTemplate.setAbilitato( dto.isAbilitato() );

        return mailTemplate;
    }

    @Override
    public MailTemplateDTO toDto(MailTemplate entity) {
        if ( entity == null ) {
            return null;
        }

        MailTemplateDTO mailTemplateDTO = new MailTemplateDTO();

        mailTemplateDTO.setCreatedBy( entity.getCreatedBy() );
        mailTemplateDTO.setCreatedDate( entity.getCreatedDate() );
        mailTemplateDTO.setLastModifiedBy( entity.getLastModifiedBy() );
        mailTemplateDTO.setLastModifiedDate( entity.getLastModifiedDate() );
        mailTemplateDTO.setId( entity.getId() );
        mailTemplateDTO.setCodice( entity.getCodice() );
        mailTemplateDTO.setCategoria( entity.getCategoria() );
        mailTemplateDTO.setOggetto( entity.getOggetto() );
        mailTemplateDTO.setModello( entity.getModello() );
        mailTemplateDTO.setAbilitato( entity.isAbilitato() );

        return mailTemplateDTO;
    }

    @Override
    public List<MailTemplate> toEntity(List<MailTemplateDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MailTemplate> list = new ArrayList<MailTemplate>( dtoList.size() );
        for ( MailTemplateDTO mailTemplateDTO : dtoList ) {
            list.add( toEntity( mailTemplateDTO ) );
        }

        return list;
    }

    @Override
    public List<MailTemplateDTO> toDto(List<MailTemplate> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MailTemplateDTO> list = new ArrayList<MailTemplateDTO>( entityList.size() );
        for ( MailTemplate mailTemplate : entityList ) {
            list.add( toDto( mailTemplate ) );
        }

        return list;
    }
}
