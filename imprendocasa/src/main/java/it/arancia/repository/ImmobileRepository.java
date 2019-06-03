package it.arancia.repository;

import it.arancia.domain.Immobile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Immobile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImmobileRepository extends JpaRepository<Immobile, Long>, JpaSpecificationExecutor<Immobile> {

}
