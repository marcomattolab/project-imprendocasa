package it.arancia.repository;

import it.arancia.domain.MailTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MailTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long>, JpaSpecificationExecutor<MailTemplate> {

}
