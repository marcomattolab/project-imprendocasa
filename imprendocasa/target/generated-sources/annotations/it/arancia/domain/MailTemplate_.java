package it.arancia.domain;

import it.arancia.domain.enumeration.CategoriaNotifica;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MailTemplate.class)
public abstract class MailTemplate_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<MailTemplate, Boolean> abilitato;
	public static volatile SingularAttribute<MailTemplate, CategoriaNotifica> categoria;
	public static volatile SingularAttribute<MailTemplate, String> codice;
	public static volatile SingularAttribute<MailTemplate, String> oggetto;
	public static volatile SingularAttribute<MailTemplate, Long> id;
	public static volatile SingularAttribute<MailTemplate, String> modello;

}

