package it.arancia.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Professione.class)
public abstract class Professione_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Professione, Boolean> abilitato;
	public static volatile SingularAttribute<Professione, String> codice;
	public static volatile SingularAttribute<Professione, String> denominazione;
	public static volatile SingularAttribute<Professione, Long> id;

}

