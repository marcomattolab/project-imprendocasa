package it.arancia.domain;

import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.TipoMese;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends it.arancia.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<Cliente, Double> numeroSegnalazioni;
	public static volatile SetAttribute<Cliente, Incarico> incaricoAcquirenteLocatarios;
	public static volatile SingularAttribute<Cliente, String> note;
	public static volatile SingularAttribute<Cliente, String> titolo;
	public static volatile SingularAttribute<Cliente, LocalDate> dataNascita;
	public static volatile SingularAttribute<Cliente, String> cognome;
	public static volatile SingularAttribute<Cliente, String> indirizzo;
	public static volatile SingularAttribute<Cliente, String> provincia;
	public static volatile SingularAttribute<Cliente, String> codiceFiscale;
	public static volatile SingularAttribute<Cliente, String> regione;
	public static volatile SingularAttribute<Cliente, String> cap;
	public static volatile SetAttribute<Cliente, Incarico> incaricoSegnalatores;
	public static volatile SingularAttribute<Cliente, Boolean> abilitato;
	public static volatile SingularAttribute<Cliente, String> telefonoCellulare;
	public static volatile SingularAttribute<Cliente, String> ragioneSociale;
	public static volatile SingularAttribute<Cliente, Long> id;
	public static volatile SingularAttribute<Cliente, BooleanStatus> alertCompleanno;
	public static volatile SingularAttribute<Cliente, String> email;
	public static volatile SingularAttribute<Cliente, String> citta;
	public static volatile SetAttribute<Cliente, ListaContatti> listaContattis;
	public static volatile SetAttribute<Cliente, Incarico> incaricoProponentes;
	public static volatile SingularAttribute<Cliente, String> agente;
	public static volatile SingularAttribute<Cliente, Double> numeroPratiche;
	public static volatile SingularAttribute<Cliente, String> nome;
	public static volatile SingularAttribute<Cliente, String> telefonoFisso;
	public static volatile SetAttribute<Cliente, Tag> tags;
	public static volatile SingularAttribute<Cliente, Double> importoProvvigioni;
	public static volatile SingularAttribute<Cliente, TipoMese> meseNascita;
	public static volatile SetAttribute<Cliente, Incarico> incaricoCommittentes;
	public static volatile SingularAttribute<Cliente, Double> punteggio;
	public static volatile SingularAttribute<Cliente, String> codiceAntiriciclaggio;
	public static volatile SingularAttribute<Cliente, Double> importoProvvigioniDerivate;

}

