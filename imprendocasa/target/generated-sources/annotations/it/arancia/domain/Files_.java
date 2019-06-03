package it.arancia.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Files.class)
public abstract class Files_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Files, String> estensione;
	public static volatile SingularAttribute<Files, String> nome;
	public static volatile SingularAttribute<Files, Long> id;
	public static volatile SingularAttribute<Files, Immobile> immobile;
	public static volatile SingularAttribute<Files, String> dimensione;

}

