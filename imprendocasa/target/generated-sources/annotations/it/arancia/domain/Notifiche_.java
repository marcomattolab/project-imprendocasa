package it.arancia.domain;

import it.arancia.domain.enumeration.CanaleNotifica;
import it.arancia.domain.enumeration.CategoriaNotifica;
import it.arancia.domain.enumeration.TipoEsito;
import it.arancia.domain.enumeration.TipoNotifica;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Notifiche.class)
public abstract class Notifiche_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Notifiche, Integer> numeroDestinatari;
	public static volatile SingularAttribute<Notifiche, CategoriaNotifica> categoriaNotifica;
	public static volatile SingularAttribute<Notifiche, TipoEsito> esitoNotifica;
	public static volatile SingularAttribute<Notifiche, String> dettagliEsito;
	public static volatile SingularAttribute<Notifiche, TipoNotifica> tipoNotifica;
	public static volatile SingularAttribute<Notifiche, String> testoNotifica;
	public static volatile SingularAttribute<Notifiche, CanaleNotifica> canaleNotifica;
	public static volatile SingularAttribute<Notifiche, String> oggettoNotifica;
	public static volatile SingularAttribute<Notifiche, Long> id;
	public static volatile SingularAttribute<Notifiche, String> destinatariNotifica;

}

