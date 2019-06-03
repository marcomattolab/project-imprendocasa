package it.arancia.repository;

import it.arancia.domain.Geolocalizzazione;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Geolocalizzazione entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeolocalizzazioneRepository extends JpaRepository<Geolocalizzazione, Long>, JpaSpecificationExecutor<Geolocalizzazione> {

}
