package it.arancia.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.TipoMese;

/**
 * Entity Cliente
 */
@ApiModel(description = "Entity Cliente")
@Entity
@Table(name = "cliente")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Cliente extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "ragione_sociale")
    private String ragioneSociale;
    
    @Column(name = "agente")
    private String agente;
    
    @Column(name = "codice_fiscale")
    private String codiceFiscale;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_compleanno")
    private BooleanStatus alertCompleanno;

    @NotNull
    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @Enumerated(EnumType.STRING)
    @Column(name = "mese_nascita")
    private TipoMese meseNascita;

    @Column(name = "telefono_fisso")
    private String telefonoFisso;

    @Column(name = "telefono_cellulare")
    private String telefonoCellulare;

    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email")
    private String email;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Size(max = 20)
    @Column(name = "cap", length = 20)
    private String cap;

    @Column(name = "regione")
    private String regione;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "citta")
    private String citta;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "codice_antiriciclaggio")
    private String codiceAntiriciclaggio;

    @Column(name = "importo_provvigioni")
    private Double importoProvvigioni;

    @Column(name = "importo_provvigioni_derivate")
    private Double importoProvvigioniDerivate;
    
    @Column(name = "numero_pratiche")
    private Double numeroPratiche;

    @Column(name = "numero_segnalazioni")
    private Double numeroSegnalazioni;

    @Column(name = "punteggio")
    private Double punteggio;

    @Column(name = "abilitato")
    private Boolean abilitato = true;

    @OneToMany(mappedBy = "cliente")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ListaContatti> listaContattis = new HashSet<>();
    
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "cliente_tag",
               joinColumns = @JoinColumn(name = "clientes_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "tags_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();

    //TODO FIXME COMMENT THIS
//    @ManyToMany(mappedBy = "committentes")
//    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//    @JsonIgnore
//    private Set<Incarico> incaricos = new HashSet<>();

    //TODO FIXME DECOMMENT THESE
    @ManyToMany(mappedBy = "committentes")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Incarico> incaricoCommittentes = new HashSet<>();

    @ManyToMany(mappedBy = "proponentes")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Incarico> incaricoProponentes = new HashSet<>();

    @ManyToMany(mappedBy = "acquirenteLocatarios")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Incarico> incaricoAcquirenteLocatarios = new HashSet<>();

    @ManyToMany(mappedBy = "segnalatores")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Incarico> incaricoSegnalatores = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Cliente nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Cliente cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public Cliente codiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
        return this;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public BooleanStatus getAlertCompleanno() {
        return alertCompleanno;
    }

    public Cliente alertCompleanno(BooleanStatus alertCompleanno) {
        this.alertCompleanno = alertCompleanno;
        return this;
    }

    public void setAlertCompleanno(BooleanStatus alertCompleanno) {
        this.alertCompleanno = alertCompleanno;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public Cliente dataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
        return this;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public TipoMese getMeseNascita() {
        return meseNascita;
    }

    public Cliente meseNascita(TipoMese meseNascita) {
        this.meseNascita = meseNascita;
        return this;
    }

    public void setMeseNascita(TipoMese meseNascita) {
        this.meseNascita = meseNascita;
    }

    public String getTelefonoFisso() {
        return telefonoFisso;
    }

    public Cliente telefonoFisso(String telefonoFisso) {
        this.telefonoFisso = telefonoFisso;
        return this;
    }

    public void setTelefonoFisso(String telefonoFisso) {
        this.telefonoFisso = telefonoFisso;
    }

    public String getTelefonoCellulare() {
        return telefonoCellulare;
    }

    public Cliente telefonoCellulare(String telefonoCellulare) {
        this.telefonoCellulare = telefonoCellulare;
        return this;
    }

    public void setTelefonoCellulare(String telefonoCellulare) {
        this.telefonoCellulare = telefonoCellulare;
    }

    public String getEmail() {
        return email;
    }

    public Cliente email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Cliente indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public Cliente cap(String cap) {
        this.cap = cap;
        return this;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getRegione() {
        return regione;
    }

    public Cliente regione(String regione) {
        this.regione = regione;
        return this;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public Cliente provincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public Cliente citta(String citta) {
        this.citta = citta;
        return this;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNote() {
        return note;
    }

    public Cliente note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCodiceAntiriciclaggio() {
        return codiceAntiriciclaggio;
    }

    public Cliente codiceAntiriciclaggio(String codiceAntiriciclaggio) {
        this.codiceAntiriciclaggio = codiceAntiriciclaggio;
        return this;
    }

    public void setCodiceAntiriciclaggio(String codiceAntiriciclaggio) {
        this.codiceAntiriciclaggio = codiceAntiriciclaggio;
    }

    public Double getImportoProvvigioni() {
        return importoProvvigioni;
    }

    public Cliente importoProvvigioni(Double importoProvvigioni) {
        this.importoProvvigioni = importoProvvigioni;
        return this;
    }

    public void setImportoProvvigioni(Double importoProvvigioni) {
        this.importoProvvigioni = importoProvvigioni;
    }

    public Double getNumeroPratiche() {
        return numeroPratiche;
    }

    public Cliente numeroPratiche(Double numeroPratiche) {
        this.numeroPratiche = numeroPratiche;
        return this;
    }

    public void setNumeroPratiche(Double numeroPratiche) {
        this.numeroPratiche = numeroPratiche;
    }

    public Double getNumeroSegnalazioni() {
        return numeroSegnalazioni;
    }

    public Cliente numeroSegnalazioni(Double numeroSegnalazioni) {
        this.numeroSegnalazioni = numeroSegnalazioni;
        return this;
    }

    public void setNumeroSegnalazioni(Double numeroSegnalazioni) {
        this.numeroSegnalazioni = numeroSegnalazioni;
    }

    public Double getPunteggio() {
        return punteggio;
    }

    public Cliente punteggio(Double punteggio) {
        this.punteggio = punteggio;
        return this;
    }

    public void setPunteggio(Double punteggio) {
        this.punteggio = punteggio;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public Cliente abilitato(Boolean abilitato) {
        this.abilitato = abilitato;
        return this;
    }

    public void setAbilitato(Boolean abilitato) {
        this.abilitato = abilitato;
    }

    public String getTitolo() {
        return titolo;
    }

    public Cliente titolo(String titolo) {
        this.titolo = titolo;
        return this;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public Cliente ragioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
        return this;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getAgente() {
        return agente;
    }

    public Cliente agente(String agente) {
        this.agente = agente;
        return this;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }
    
    public Double getImportoProvvigioniDerivate() {
        return importoProvvigioniDerivate;
    }

    public Cliente importoProvvigioniDerivate(Double importoProvvigioniDerivate) {
        this.importoProvvigioniDerivate = importoProvvigioniDerivate;
        return this;
    }

    public void setImportoProvvigioniDerivate(Double importoProvvigioniDerivate) {
        this.importoProvvigioniDerivate = importoProvvigioniDerivate;
    }
    

    public Set<ListaContatti> getListaContattis() {
        return listaContattis;
    }

    public Cliente listaContattis(Set<ListaContatti> listaContattis) {
        this.listaContattis = listaContattis;
        return this;
    }

    public Cliente addListaContatti(ListaContatti listaContatti) {
        this.listaContattis.add(listaContatti);
        listaContatti.setCliente(this);
        return this;
    }

    public Cliente removeListaContatti(ListaContatti listaContatti) {
        this.listaContattis.remove(listaContatti);
        listaContatti.setCliente(null);
        return this;
    }

    public void setListaContattis(Set<ListaContatti> listaContattis) {
        this.listaContattis = listaContattis;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Cliente tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Cliente addTag(Tag tag) {
        this.tags.add(tag);
        return this;
    }

    public Cliente removeTag(Tag tag) {
        this.tags.remove(tag);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    //TODO FIXME COMMENT THIS
//    public Set<Incarico> getIncaricos() {
//        return incaricos;
//    }
//
//    public Cliente incaricos(Set<Incarico> incaricos) {
//        this.incaricos = incaricos; //FIXME
//        return this;
//    }
//
//    public Cliente addIncarico(Incarico incarico) {
//        this.incaricos.add(incarico);
//        incarico.getCommittentes().add(this);
//        return this;
//    }
//
//    public Cliente removeIncarico(Incarico incarico) {
//        this.incaricos.remove(incarico);
//        incarico.getCommittentes().remove(this);
//        return this;
//    }
    
	//TODO FIXME DECOMMENT THESE
    public Set<Incarico> getIncaricoCommittentes() {
        return incaricoCommittentes;
    }

    public Cliente incaricoCommittentes(Set<Incarico> incaricos) {
        this.incaricoCommittentes = incaricos;
        return this;
    }

    public Cliente addIncaricoCommittente(Incarico incarico) {
        this.incaricoCommittentes.add(incarico);
        incarico.getCommittentes().add(this);
        return this;
    }

    public Cliente removeIncaricoCommittente(Incarico incarico) {
        this.incaricoCommittentes.remove(incarico);
        incarico.getCommittentes().remove(this);
        return this;
    }

    public void setIncaricoCommittentes(Set<Incarico> incaricos) {
        this.incaricoCommittentes = incaricos;
    }

    public Set<Incarico> getIncaricoProponentes() {
        return incaricoProponentes;
    }

    public Cliente incaricoProponentes(Set<Incarico> incaricos) {
        this.incaricoProponentes = incaricos;
        return this;
    }

    public Cliente addIncaricoProponente(Incarico incarico) {
        this.incaricoProponentes.add(incarico);
        incarico.getProponentes().add(this);
        return this;
    }

    public Cliente removeIncaricoProponente(Incarico incarico) {
        this.incaricoProponentes.remove(incarico);
        incarico.getProponentes().remove(this);
        return this;
    }

    public void setIncaricoProponentes(Set<Incarico> incaricos) {
        this.incaricoProponentes = incaricos;
    }

    public Set<Incarico> getIncaricoAcquirenteLocatarios() {
        return incaricoAcquirenteLocatarios;
    }

    public Cliente incaricoAcquirenteLocatarios(Set<Incarico> incaricos) {
        this.incaricoAcquirenteLocatarios = incaricos;
        return this;
    }

    public Cliente addIncaricoAcquirenteLocatario(Incarico incarico) {
        this.incaricoAcquirenteLocatarios.add(incarico);
        incarico.getAcquirenteLocatarios().add(this);
        return this;
    }

    public Cliente removeIncaricoAcquirenteLocatario(Incarico incarico) {
        this.incaricoAcquirenteLocatarios.remove(incarico);
        incarico.getAcquirenteLocatarios().remove(this);
        return this;
    }

    public void setIncaricoAcquirenteLocatarios(Set<Incarico> incaricos) {
        this.incaricoAcquirenteLocatarios = incaricos;
    }

    public Set<Incarico> getIncaricoSegnalatores() {
        return incaricoSegnalatores;
    }

    public Cliente incaricoSegnalatores(Set<Incarico> incaricos) {
        this.incaricoSegnalatores = incaricos;
        return this;
    }

    public Cliente addIncaricoSegnalatore(Incarico incarico) {
        this.incaricoSegnalatores.add(incarico);
        incarico.getSegnalatores().add(this);
        return this;
    }

    public Cliente removeIncaricoSegnalatore(Incarico incarico) {
        this.incaricoSegnalatores.remove(incarico);
        incarico.getSegnalatores().remove(this);
        return this;
    }

    public void setIncaricoSegnalatores(Set<Incarico> incaricos) {
        this.incaricoSegnalatores = incaricos;
    }
    

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        if (cliente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
            ", titolo='" + getTitolo() + "'" +
            ", ragioneSociale='" + getRagioneSociale() + "'" +
            ", agente='" + getAgente() + "'" +
            ", alertCompleanno='" + getAlertCompleanno() + "'" +
            ", dataNascita='" + getDataNascita() + "'" +
            ", meseNascita='" + getMeseNascita() + "'" +
            ", telefonoFisso='" + getTelefonoFisso() + "'" +
            ", telefonoCellulare='" + getTelefonoCellulare() + "'" +
            ", email='" + getEmail() + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", cap='" + getCap() + "'" +
            ", regione='" + getRegione() + "'" +
            ", provincia='" + getProvincia() + "'" +
            ", citta='" + getCitta() + "'" +
            ", note='" + getNote() + "'" +
            ", codiceAntiriciclaggio='" + getCodiceAntiriciclaggio() + "'" +
            ", importoProvvigioni=" + getImportoProvvigioni() +
            ", importoProvvigioniDerivate=" + getImportoProvvigioniDerivate() +
            ", numeroPratiche=" + getNumeroPratiche() +
            ", numeroSegnalazioni=" + getNumeroSegnalazioni() +
            ", punteggio=" + getPunteggio() +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
}
