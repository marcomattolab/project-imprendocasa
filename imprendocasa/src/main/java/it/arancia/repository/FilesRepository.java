package it.arancia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import it.arancia.domain.Files;


/**
 * Spring Data  repository for the Files entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FilesRepository extends JpaRepository<Files, Long>, JpaSpecificationExecutor<Files> {

	public List<Files> findByImmobileId(Long idImmobile);
	
}
