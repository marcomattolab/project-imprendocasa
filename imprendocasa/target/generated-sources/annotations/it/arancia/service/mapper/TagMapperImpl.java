package it.arancia.service.mapper;

import it.arancia.domain.Tag;
import it.arancia.service.dto.TagDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-29T14:29:18+0100",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_111 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public Tag toEntity(TagDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setCreatedBy( dto.getCreatedBy() );
        tag.setCreatedDate( dto.getCreatedDate() );
        tag.setLastModifiedBy( dto.getLastModifiedBy() );
        tag.setLastModifiedDate( dto.getLastModifiedDate() );
        tag.setId( dto.getId() );
        tag.setCodice( dto.getCodice() );
        tag.setDenominazione( dto.getDenominazione() );
        tag.setAbilitato( dto.isAbilitato() );

        return tag;
    }

    @Override
    public TagDTO toDto(Tag entity) {
        if ( entity == null ) {
            return null;
        }

        TagDTO tagDTO = new TagDTO();

        tagDTO.setCreatedBy( entity.getCreatedBy() );
        tagDTO.setCreatedDate( entity.getCreatedDate() );
        tagDTO.setLastModifiedBy( entity.getLastModifiedBy() );
        tagDTO.setLastModifiedDate( entity.getLastModifiedDate() );
        tagDTO.setId( entity.getId() );
        tagDTO.setCodice( entity.getCodice() );
        tagDTO.setDenominazione( entity.getDenominazione() );
        tagDTO.setAbilitato( entity.isAbilitato() );

        return tagDTO;
    }

    @Override
    public List<Tag> toEntity(List<TagDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Tag> list = new ArrayList<Tag>( dtoList.size() );
        for ( TagDTO tagDTO : dtoList ) {
            list.add( toEntity( tagDTO ) );
        }

        return list;
    }

    @Override
    public List<TagDTO> toDto(List<Tag> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TagDTO> list = new ArrayList<TagDTO>( entityList.size() );
        for ( Tag tag : entityList ) {
            list.add( toDto( tag ) );
        }

        return list;
    }
}
