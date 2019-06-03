package it.arancia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.arancia.domain.Incarico;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.service.dto.IdashboardDTO;
import it.arancia.service.dto.IncaricoDTO;


/**
 * Spring Data  repository for the Incarico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncaricoRepository extends JpaRepository<Incarico, Long>, JpaSpecificationExecutor<Incarico> {

	/**      
		incarico.clientes 		==> incarico.committentes
    	incarico.acquirentes 	==> incarico.proponentes
    			N.A.			==> incarico.acquirenteLocatarios
    			N.A.			==> incarico.segnalatores
	**/
	
	
    @Query(value = "select distinct incarico from Incarico incarico left join fetch incarico.partners left join fetch incarico.committentes left join fetch incarico.proponentes left join fetch incarico.acquirenteLocatarios left join fetch incarico.segnalatores",
            countQuery = "select count(distinct incarico) from Incarico incarico")
    Page<Incarico> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct incarico from Incarico incarico left join fetch incarico.partners left join fetch incarico.committentes left join fetch incarico.proponentes left join fetch incarico.acquirenteLocatarios left join fetch incarico.segnalatores")
    List<Incarico> findAllWithEagerRelationships();

    @Query("select incarico from Incarico incarico left join fetch incarico.partners left join fetch incarico.committentes left join fetch incarico.proponentes left join fetch incarico.acquirenteLocatarios left join fetch incarico.segnalatores where incarico.id =:id")
    Optional<Incarico> findOneWithEagerRelationships(@Param("id") Long id);
	
    @Query(value = "select distinct incarico from Incarico incarico left join fetch incarico.committentes committente left join fetch incarico.segnalatores segnalatore left join fetch incarico.proponentes proponente left join fetch incarico.acquirenteLocatarios acquirenteLocatario "
    		+ "where incarico.stato not in (:stato) and (committente.id=:idCliente or segnalatore.id=:idCliente or proponente.id=:idCliente or acquirenteLocatario.id=:idCliente)")
    List<Incarico> findAllByClienteAndStatoNotIn(@Param("idCliente") Long idCliente, @Param("stato") StatoIncarico stato);
    
//    @Query(value = "select count (distinct incarico.id) from Incarico incarico left join fetch incarico.committentes committente left join fetch incarico.segnalatores segnalatore left join fetch incarico.proponentes proponente left join fetch incarico.acquirenteLocatarios acquirenteLocatario "
//    		+ "where incarico.stato not in (:stato) and (committente.id=:idCliente or segnalatore.id=:idCliente or proponente.id=:idCliente or acquirenteLocatario.id=:idCliente)")
//    Long countByClienteAndStatoNotIn(Long idCliente, StatoIncarico bozza);
    
    List<IncaricoDTO> findAllByImmobileId(Long idImmobile);
    
	Long countByImmobileIdAndStatoNotIn(Long idImmobile, StatoIncarico stato);
    
//    @Query(value = "select distinct incarico from Incarico incarico left join fetch incarico.partners left join fetch incarico.committentes",
//        countQuery = "select count(distinct incarico) from Incarico incarico")
//    Page<Incarico> findAllWithEagerRelationships(Pageable pageable);
//
//    @Query(value = "select distinct incarico from Incarico incarico left join fetch incarico.partners left join fetch incarico.committentes")
//    List<Incarico> findAllWithEagerRelationships();
//
//    @Query("select incarico from Incarico incarico left join fetch incarico.partners left join fetch incarico.committentes where incarico.id =:id")
//    Optional<Incarico> findOneWithEagerRelationships(@Param("id") Long id);
    
    List<Incarico> findByStato(@Param("stato") StatoIncarico stato);

    @Query(value = "select new it.arancia.service.dto.IdashboardDTO(incarico.stato, count(incarico)) from Incarico incarico group by incarico.stato")
    List<IdashboardDTO> findIncaricoDashboard();

	


}
