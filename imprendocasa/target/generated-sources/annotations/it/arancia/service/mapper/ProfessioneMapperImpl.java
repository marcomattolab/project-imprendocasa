package it.arancia.service.mapper;

import it.arancia.domain.Professione;
import it.arancia.service.dto.ProfessioneDTO;
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
public class ProfessioneMapperImpl implements ProfessioneMapper {

    @Override
    public Professione toEntity(ProfessioneDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Professione professione = new Professione();

        professione.setCreatedBy( dto.getCreatedBy() );
        professione.setCreatedDate( dto.getCreatedDate() );
        professione.setLastModifiedBy( dto.getLastModifiedBy() );
        professione.setLastModifiedDate( dto.getLastModifiedDate() );
        professione.setId( dto.getId() );
        professione.setCodice( dto.getCodice() );
        professione.setDenominazione( dto.getDenominazione() );
        professione.setAbilitato( dto.isAbilitato() );

        return professione;
    }

    @Override
    public ProfessioneDTO toDto(Professione entity) {
        if ( entity == null ) {
            return null;
        }

        ProfessioneDTO professioneDTO = new ProfessioneDTO();

        professioneDTO.setCreatedBy( entity.getCreatedBy() );
        professioneDTO.setCreatedDate( entity.getCreatedDate() );
        professioneDTO.setLastModifiedBy( entity.getLastModifiedBy() );
        professioneDTO.setLastModifiedDate( entity.getLastModifiedDate() );
        professioneDTO.setId( entity.getId() );
        professioneDTO.setCodice( entity.getCodice() );
        professioneDTO.setDenominazione( entity.getDenominazione() );
        professioneDTO.setAbilitato( entity.isAbilitato() );

        return professioneDTO;
    }

    @Override
    public List<Professione> toEntity(List<ProfessioneDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Professione> list = new ArrayList<Professione>( dtoList.size() );
        for ( ProfessioneDTO professioneDTO : dtoList ) {
            list.add( toEntity( professioneDTO ) );
        }

        return list;
    }

    @Override
    public List<ProfessioneDTO> toDto(List<Professione> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ProfessioneDTO> list = new ArrayList<ProfessioneDTO>( entityList.size() );
        for ( Professione professione : entityList ) {
            list.add( toDto( professione ) );
        }

        return list;
    }
}
