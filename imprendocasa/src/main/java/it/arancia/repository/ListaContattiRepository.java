package it.arancia.repository;

import it.arancia.domain.ListaContatti;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ListaContatti entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ListaContattiRepository extends JpaRepository<ListaContatti, Long>, JpaSpecificationExecutor<ListaContatti> {

}
