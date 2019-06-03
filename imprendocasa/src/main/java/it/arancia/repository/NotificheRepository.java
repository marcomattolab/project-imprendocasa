package it.arancia.repository;

import it.arancia.domain.Notifiche;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Notifiche entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificheRepository extends JpaRepository<Notifiche, Long>, JpaSpecificationExecutor<Notifiche> {

}
