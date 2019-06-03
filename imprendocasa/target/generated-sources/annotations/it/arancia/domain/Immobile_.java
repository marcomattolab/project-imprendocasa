package it.arancia.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Immobile.class)
public abstract class Immobile_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Immobile, String> agente;
	public static volatile SingularAttribute<Immobile, String> note;
	public static volatile SingularAttribute<Immobile, String> sub;
	public static volatile SingularAttribute<Immobile, Geolocalizzazione> geolocalizzazione;
	public static volatile SingularAttribute<Immobile, String> codice;
	public static volatile SingularAttribute<Immobile, String> indirizzo;
	public static volatile SingularAttribute<Immobile, String> provincia;
	public static volatile SingularAttribute<Immobile, String> regione;
	public static volatile SingularAttribute<Immobile, String> foglio;
	public static volatile SingularAttribute<Immobile, String> descrizione;
	public static volatile SingularAttribute<Immobile, String> pathFolder;
	public static volatile SingularAttribute<Immobile, String> cap;
	public static volatile SetAttribute<Immobile, Incarico> incaricos;
	public static volatile SetAttribute<Immobile, Files> files;
	public static volatile SingularAttribute<Immobile, Long> id;
	public static volatile SingularAttribute<Immobile, String> datiCatastali;
	public static volatile SingularAttribute<Immobile, String> particella;
	public static volatile SingularAttribute<Immobile, String> citta;

}

