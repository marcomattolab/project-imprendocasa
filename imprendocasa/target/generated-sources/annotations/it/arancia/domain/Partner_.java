package it.arancia.domain;

import it.arancia.domain.enumeration.TipoIndirizzo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Partner.class)
public abstract class Partner_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Partner, TipoIndirizzo> tipoIndirizzo;
	public static volatile SingularAttribute<Partner, String> note;
	public static volatile SingularAttribute<Partner, String> cognome;
	public static volatile SingularAttribute<Partner, String> indirizzo;
	public static volatile SingularAttribute<Partner, String> nome;
	public static volatile SingularAttribute<Partner, String> telefonoFisso;
	public static volatile SingularAttribute<Partner, String> provincia;
	public static volatile SingularAttribute<Partner, String> codiceFiscale;
	public static volatile SingularAttribute<Partner, String> regione;
	public static volatile SingularAttribute<Partner, String> cap;
	public static volatile SetAttribute<Partner, Incarico> incaricos;
	public static volatile SingularAttribute<Partner, Boolean> abilitato;
	public static volatile SingularAttribute<Partner, Professione> professione;
	public static volatile SingularAttribute<Partner, String> telefonoCellulare;
	public static volatile SingularAttribute<Partner, Long> id;
	public static volatile SingularAttribute<Partner, String> email;
	public static volatile SingularAttribute<Partner, String> citta;

}

