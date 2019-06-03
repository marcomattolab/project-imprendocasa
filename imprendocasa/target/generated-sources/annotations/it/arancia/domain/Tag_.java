package it.arancia.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public abstract class Tag_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Tag, Boolean> abilitato;
	public static volatile SingularAttribute<Tag, String> codice;
	public static volatile SingularAttribute<Tag, String> denominazione;
	public static volatile SingularAttribute<Tag, Long> id;

}

