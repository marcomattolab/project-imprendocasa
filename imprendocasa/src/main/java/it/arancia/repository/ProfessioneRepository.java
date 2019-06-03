package it.arancia.repository;

import it.arancia.domain.Professione;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Professione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfessioneRepository extends JpaRepository<Professione, Long>, JpaSpecificationExecutor<Professione> {

}
