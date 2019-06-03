package it.arancia.service.mapper;

import it.arancia.domain.Files;
import it.arancia.domain.Immobile;
import it.arancia.service.dto.FilesDTO;
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
public class FilesMapperImpl implements FilesMapper {

    @Autowired
    private ImmobileMapper immobileMapper;

    @Override
    public List<Files> toEntity(List<FilesDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Files> list = new ArrayList<Files>( dtoList.size() );
        for ( FilesDTO filesDTO : dtoList ) {
            list.add( toEntity( filesDTO ) );
        }

        return list;
    }

    @Override
    public List<FilesDTO> toDto(List<Files> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FilesDTO> list = new ArrayList<FilesDTO>( entityList.size() );
        for ( Files files : entityList ) {
            list.add( toDto( files ) );
        }

        return list;
    }

    @Override
    public FilesDTO toDto(Files files) {
        if ( files == null ) {
            return null;
        }

        FilesDTO filesDTO = new FilesDTO();

        String pathFolder = filesImmobilePathFolder( files );
        if ( pathFolder != null ) {
            filesDTO.setImmobilePathFolder( pathFolder );
        }
        Long id = filesImmobileId( files );
        if ( id != null ) {
            filesDTO.setImmobileId( id );
        }
        filesDTO.setCreatedBy( files.getCreatedBy() );
        filesDTO.setCreatedDate( files.getCreatedDate() );
        filesDTO.setLastModifiedBy( files.getLastModifiedBy() );
        filesDTO.setLastModifiedDate( files.getLastModifiedDate() );
        filesDTO.setId( files.getId() );
        filesDTO.setNome( files.getNome() );
        filesDTO.setDimensione( files.getDimensione() );
        filesDTO.setEstensione( files.getEstensione() );

        return filesDTO;
    }

    @Override
    public Files toEntity(FilesDTO filesDTO) {
        if ( filesDTO == null ) {
            return null;
        }

        Files files = new Files();

        files.setImmobile( immobileMapper.fromId( filesDTO.getImmobileId() ) );
        files.setCreatedBy( filesDTO.getCreatedBy() );
        files.setCreatedDate( filesDTO.getCreatedDate() );
        files.setLastModifiedBy( filesDTO.getLastModifiedBy() );
        files.setLastModifiedDate( filesDTO.getLastModifiedDate() );
        files.setId( filesDTO.getId() );
        files.setNome( filesDTO.getNome() );
        files.setDimensione( filesDTO.getDimensione() );
        files.setEstensione( filesDTO.getEstensione() );

        return files;
    }

    private String filesImmobilePathFolder(Files files) {
        if ( files == null ) {
            return null;
        }
        Immobile immobile = files.getImmobile();
        if ( immobile == null ) {
            return null;
        }
        String pathFolder = immobile.getPathFolder();
        if ( pathFolder == null ) {
            return null;
        }
        return pathFolder;
    }

    private Long filesImmobileId(Files files) {
        if ( files == null ) {
            return null;
        }
        Immobile immobile = files.getImmobile();
        if ( immobile == null ) {
            return null;
        }
        Long id = immobile.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
