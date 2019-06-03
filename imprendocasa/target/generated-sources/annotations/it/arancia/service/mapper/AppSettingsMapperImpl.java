package it.arancia.service.mapper;

import it.arancia.domain.AppSettings;
import it.arancia.service.dto.AppSettingsDTO;
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
public class AppSettingsMapperImpl implements AppSettingsMapper {

    @Override
    public AppSettings toEntity(AppSettingsDTO dto) {
        if ( dto == null ) {
            return null;
        }

        AppSettings appSettings = new AppSettings();

        appSettings.setCreatedBy( dto.getCreatedBy() );
        appSettings.setCreatedDate( dto.getCreatedDate() );
        appSettings.setLastModifiedBy( dto.getLastModifiedBy() );
        appSettings.setLastModifiedDate( dto.getLastModifiedDate() );
        appSettings.setId( dto.getId() );
        appSettings.setCategoria( dto.getCategoria() );
        appSettings.setChiave( dto.getChiave() );
        appSettings.setValore( dto.getValore() );
        appSettings.setAbilitato( dto.isAbilitato() );

        return appSettings;
    }

    @Override
    public AppSettingsDTO toDto(AppSettings entity) {
        if ( entity == null ) {
            return null;
        }

        AppSettingsDTO appSettingsDTO = new AppSettingsDTO();

        appSettingsDTO.setCreatedBy( entity.getCreatedBy() );
        appSettingsDTO.setCreatedDate( entity.getCreatedDate() );
        appSettingsDTO.setLastModifiedBy( entity.getLastModifiedBy() );
        appSettingsDTO.setLastModifiedDate( entity.getLastModifiedDate() );
        appSettingsDTO.setId( entity.getId() );
        appSettingsDTO.setCategoria( entity.getCategoria() );
        appSettingsDTO.setChiave( entity.getChiave() );
        appSettingsDTO.setValore( entity.getValore() );
        appSettingsDTO.setAbilitato( entity.isAbilitato() );

        return appSettingsDTO;
    }

    @Override
    public List<AppSettings> toEntity(List<AppSettingsDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<AppSettings> list = new ArrayList<AppSettings>( dtoList.size() );
        for ( AppSettingsDTO appSettingsDTO : dtoList ) {
            list.add( toEntity( appSettingsDTO ) );
        }

        return list;
    }

    @Override
    public List<AppSettingsDTO> toDto(List<AppSettings> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AppSettingsDTO> list = new ArrayList<AppSettingsDTO>( entityList.size() );
        for ( AppSettings appSettings : entityList ) {
            list.add( toDto( appSettings ) );
        }

        return list;
    }
}
