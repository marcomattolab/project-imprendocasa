package it.arancia.repository;

import it.arancia.domain.AppSettings;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AppSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppSettingsRepository extends JpaRepository<AppSettings, Long>, JpaSpecificationExecutor<AppSettings> {

	
	Optional<AppSettings> findOneByCategoriaAndChiave(String categoria, String chiave);
}
