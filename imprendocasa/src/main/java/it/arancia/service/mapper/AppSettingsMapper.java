package it.arancia.service.mapper;

import it.arancia.domain.*;
import it.arancia.service.dto.AppSettingsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppSettings and its DTO AppSettingsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppSettingsMapper extends EntityMapper<AppSettingsDTO, AppSettings> {



    default AppSettings fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppSettings appSettings = new AppSettings();
        appSettings.setId(id);
        return appSettings;
    }
}
