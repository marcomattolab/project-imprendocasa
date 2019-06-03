package it.arancia.domain;

import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.CategoriaIncarico;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.domain.enumeration.TipoNegoziazione;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Incarico.class)
public abstract class Incarico_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Incarico, LocalDate> dataAlertFiscali;
	public static volatile SingularAttribute<Incarico, TipoNegoziazione> tipo;
	public static volatile SingularAttribute<Incarico, Double> canoneCorrisposto;
	public static volatile SetAttribute<Incarico, Cliente> acquirenteLocatarios;
	public static volatile SingularAttribute<Incarico, BooleanStatus> alertFiscali;
	public static volatile SingularAttribute<Incarico, Boolean> privacy;
	public static volatile SingularAttribute<Incarico, String> agenteDiContatto;
	public static volatile SingularAttribute<Incarico, Boolean> oscuraIncarico;
	public static volatile SingularAttribute<Incarico, Double> importoProvvigione;
	public static volatile SingularAttribute<Incarico, Double> importoProvvigioneAcquirenti;
	public static volatile SingularAttribute<Incarico, LocalDate> dataScadenza;
	public static volatile SingularAttribute<Incarico, BooleanStatus> alertRicorrenza;
	public static volatile SingularAttribute<Incarico, StatoIncarico> stato;
	public static volatile SingularAttribute<Incarico, String> ricorrenzaAlertCortesia;
	public static volatile SingularAttribute<Incarico, Boolean> antiriciclaggioAcquirenti;
	public static volatile SingularAttribute<Incarico, BooleanStatus> alertCortesia;
	public static volatile SingularAttribute<Incarico, LocalDate> dataInizioLocazione;
	public static volatile SingularAttribute<Incarico, Long> id;
	public static volatile SingularAttribute<Incarico, CategoriaIncarico> categoriaIncarico;
	public static volatile SingularAttribute<Incarico, String> noteTrattativa;
	public static volatile SingularAttribute<Incarico, LocalDate> dataFineLocazione;
	public static volatile SingularAttribute<Incarico, String> noteLocazione;
	public static volatile SetAttribute<Incarico, ListaContatti> listaContattis;
	public static volatile SingularAttribute<Incarico, String> agente;
	public static volatile SingularAttribute<Incarico, LocalDate> dataContatto;
	public static volatile SingularAttribute<Incarico, LocalDate> dataAlertRicorrenza;
	public static volatile SetAttribute<Incarico, Cliente> segnalatores;
	public static volatile SingularAttribute<Incarico, String> cognomeConduttore;
	public static volatile SingularAttribute<Incarico, String> nomeConduttore;
	public static volatile SetAttribute<Incarico, Cliente> proponentes;
	public static volatile SingularAttribute<Incarico, Immobile> immobile;
	public static volatile SingularAttribute<Incarico, Double> prezzoMinimo;
	public static volatile SingularAttribute<Incarico, Double> prezzoRichiesta;
	public static volatile SingularAttribute<Incarico, String> ricorrenzaAlertRicorrenza;
	public static volatile SingularAttribute<Incarico, Boolean> antiriciclaggio;
	public static volatile SingularAttribute<Incarico, String> riferimento;
	public static volatile SingularAttribute<Incarico, String> datiFiscali;
	public static volatile SetAttribute<Incarico, Cliente> committentes;
	public static volatile SetAttribute<Incarico, Partner> partners;
	public static volatile SingularAttribute<Incarico, Boolean> privacyAcquirenti;
	public static volatile SingularAttribute<Incarico, Boolean> flagLocato;
	public static volatile SingularAttribute<Incarico, String> telefonoConduttore;
	public static volatile SingularAttribute<Incarico, LocalDate> dataAlertCortesia;
	public static volatile SingularAttribute<Incarico, Double> prezzoAcquisto;
	public static volatile SingularAttribute<Incarico, String> ricorrenzaAlertFiscali;

}

