package it.arancia.domain;

import it.arancia.domain.enumeration.EsitoChiamata;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ListaContatti.class)
public abstract class ListaContatti_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<ListaContatti, ZonedDateTime> dateTime;
	public static volatile SingularAttribute<ListaContatti, String> note;
	public static volatile SingularAttribute<ListaContatti, Cliente> cliente;
	public static volatile SingularAttribute<ListaContatti, Incarico> incarico;
	public static volatile SingularAttribute<ListaContatti, EsitoChiamata> esito;
	public static volatile SingularAttribute<ListaContatti, String> motivazione;
	public static volatile SingularAttribute<ListaContatti, Long> id;

}

