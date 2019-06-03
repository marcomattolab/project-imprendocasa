package it.arancia.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Geolocalizzazione.class)
public abstract class Geolocalizzazione_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Geolocalizzazione, String> latitudine;
	public static volatile SingularAttribute<Geolocalizzazione, Immobile> posizione;
	public static volatile SingularAttribute<Geolocalizzazione, Long> id;
	public static volatile SingularAttribute<Geolocalizzazione, String> immobile;
	public static volatile SingularAttribute<Geolocalizzazione, String> longitudine;

}

