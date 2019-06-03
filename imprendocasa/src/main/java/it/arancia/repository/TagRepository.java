package it.arancia.repository;

import it.arancia.domain.Tag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Tag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

	/**
	 * @param codeTag
	 * @return Tag
	 */
	Tag findByCodice(String codeTag);

}
