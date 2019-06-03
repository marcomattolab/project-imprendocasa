package it.arancia.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.CategoriaIncarico;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.domain.enumeration.TipoNegoziazione;

/**
 * Entity Incarico
 */
@ApiModel(description = "Entity Incarico")
@Entity
@Table(name = "incarico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Incarico extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "riferimento", nullable = false)
    private String riferimento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoNegoziazione tipo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_incarico")
    private CategoriaIncarico categoriaIncarico;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoIncarico stato;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @Column(name = "agente")
    private String agente;

    @Column(name = "agente_di_contatto")
    private String agenteDiContatto;

    @Column(name = "data_contatto")
    private LocalDate dataContatto;

    @Lob
    @Column(name = "note_trattativa")
    private String noteTrattativa;

    @Lob
    @Column(name = "dati_fiscali")
    private String datiFiscali;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_fiscali")
    private BooleanStatus alertFiscali;

    @Column(name = "data_alert_fiscali")
    private LocalDate dataAlertFiscali;

    @Column(name = "ricorrenza_alert_fiscali")
    private String ricorrenzaAlertFiscali;

    @Enumerated(EnumType.STRING)
    @Column(name = "alert_cortesia")
    private BooleanStatus alertCortesia;

    @Column(name = "data_alert_cortesia")
    private LocalDate dataAlertCortesia;
    
    @Column(name = "ricorrenza_alert_cortesia")
    private String ricorrenzaAlertCortesia;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "alert_ricorrenza")
    private BooleanStatus alertRicorrenza;
    
    @Column(name = "data_alert_ricorrenza")
    private LocalDate dataAlertRicorrenza;

    @Column(name = "ricorrenza_alert_ricorrenza")
    private String ricorrenzaAlertRicorrenza;
    
    @Column(name = "privacy")
    private Boolean privacy;

    @Column(name = "antiriciclaggio")
    private Boolean antiriciclaggio;

    @Column(name = "privacy_acquirenti")
    private Boolean privacyAcquirenti;

    @Column(name = "antiriciclaggio_acquirenti")
    private Boolean antiriciclaggioAcquirenti;
    
    @Column(name = "prezzo_richiesta")
    private Double prezzoRichiesta;

    @Column(name = "prezzo_minimo")
    private Double prezzoMinimo;

    @Column(name = "prezzo_acquisto")
    private Double prezzoAcquisto;

    @Column(name = "importo_provvigione")
    private Double importoProvvigione;
    
    @Column(name = "importo_provvigione_acquirenti")
    private Double importoProvvigioneAcquirenti;

    @Column(name = "oscura_incarico")
    private Boolean oscuraIncarico;

    @Column(name = "flag_locato")
    private Boolean flagLocato;

    @Column(name = "nome_conduttore")
    private String nomeConduttore;

    @Column(name = "cognome_conduttore")
    private String cognomeConduttore;

    @Column(name = "canone_corrisposto")
    private Double canoneCorrisposto;

    @Column(name = "telefono_conduttore")
    private String telefonoConduttore;
    
    @Column(name = "data_inizio_locazione")
    private LocalDate dataInizioLocazione;

    @Column(name = "data_fine_locazione")
    private LocalDate dataFineLocazione;

    @Lob
    @Column(name = "note_locazione")
    private String noteLocazione;

    @OneToMany(mappedBy = "incarico")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ListaContatti> listaContattis = new HashSet<>();
    
    @ManyToOne
    @JsonIgnoreProperties("incaricos")
    private Immobile immobile;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "incarico_partner",
               joinColumns = @JoinColumn(name = "incaricos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "partners_id", referencedColumnName = "id"))
    private Set<Partner> partners = new HashSet<>();

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "incarico_committente",
               joinColumns = @JoinColumn(name = "incaricos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "committentes_id", referencedColumnName = "id"))
    private Set<Cliente> committentes = new HashSet<>();

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "incarico_proponente",
               joinColumns = @JoinColumn(name = "incaricos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "proponentes_id", referencedColumnName = "id"))
    private Set<Cliente> proponentes = new HashSet<>();

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "incarico_acquirente_locatario",
               joinColumns = @JoinColumn(name = "incaricos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "acquirente_locatarios_id", referencedColumnName = "id"))
    private Set<Cliente> acquirenteLocatarios = new HashSet<>();

    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE })
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "incarico_segnalatore",
               joinColumns = @JoinColumn(name = "incaricos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "segnalatores_id", referencedColumnName = "id"))
    private Set<Cliente> segnalatores = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiferimento() {
        return riferimento;
    }

    public Incarico riferimento(String riferimento) {
        this.riferimento = riferimento;
        return this;
    }

    public void setRiferimento(String riferimento) {
        this.riferimento = riferimento;
    }

    public TipoNegoziazione getTipo() {
        return tipo;
    }

    public Incarico tipo(TipoNegoziazione tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoNegoziazione tipo) {
        this.tipo = tipo;
    }

    public StatoIncarico getStato() {
        return stato;
    }

    public Incarico stato(StatoIncarico stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(StatoIncarico stato) {
        this.stato = stato;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public Incarico dataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
        return this;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getAgente() {
        return agente;
    }

    public Incarico agente(String agente) {
        this.agente = agente;
        return this;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getAgenteDiContatto() {
        return agenteDiContatto;
    }

    public Incarico agenteDiContatto(String agenteDiContatto) {
        this.agenteDiContatto = agenteDiContatto;
        return this;
    }

    public void setAgenteDiContatto(String agenteDiContatto) {
        this.agenteDiContatto = agenteDiContatto;
    }

    public LocalDate getDataContatto() {
        return dataContatto;
    }

    public Incarico dataContatto(LocalDate dataContatto) {
        this.dataContatto = dataContatto;
        return this;
    }

    public void setDataContatto(LocalDate dataContatto) {
        this.dataContatto = dataContatto;
    }

    public String getNoteTrattativa() {
        return noteTrattativa;
    }

    public Incarico noteTrattativa(String noteTrattativa) {
        this.noteTrattativa = noteTrattativa;
        return this;
    }

    public void setNoteTrattativa(String noteTrattativa) {
        this.noteTrattativa = noteTrattativa;
    }

    public String getDatiFiscali() {
        return datiFiscali;
    }

    public Incarico datiFiscali(String datiFiscali) {
        this.datiFiscali = datiFiscali;
        return this;
    }

    public void setDatiFiscali(String datiFiscali) {
        this.datiFiscali = datiFiscali;
    }

    public BooleanStatus getAlertFiscali() {
        return alertFiscali;
    }

    public Incarico alertFiscali(BooleanStatus alertFiscali) {
        this.alertFiscali = alertFiscali;
        return this;
    }

    public void setAlertFiscali(BooleanStatus alertFiscali) {
        this.alertFiscali = alertFiscali;
    }

    public BooleanStatus getAlertCortesia() {
        return alertCortesia;
    }

    public Incarico alertCortesia(BooleanStatus alertCortesia) {
        this.alertCortesia = alertCortesia;
        return this;
    }

    public void setAlertCortesia(BooleanStatus alertCortesia) {
        this.alertCortesia = alertCortesia;
    }

    public Boolean isPrivacy() {
        return privacy;
    }

    public Incarico privacy(Boolean privacy) {
        this.privacy = privacy;
        return this;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public Boolean isAntiriciclaggio() {
        return antiriciclaggio;
    }

    public Incarico antiriciclaggio(Boolean antiriciclaggio) {
        this.antiriciclaggio = antiriciclaggio;
        return this;
    }

    public void setAntiriciclaggio(Boolean antiriciclaggio) {
        this.antiriciclaggio = antiriciclaggio;
    }

    public Double getPrezzoRichiesta() {
        return prezzoRichiesta;
    }

    public Incarico prezzoRichiesta(Double prezzoRichiesta) {
        this.prezzoRichiesta = prezzoRichiesta;
        return this;
    }

    public void setPrezzoRichiesta(Double prezzoRichiesta) {
        this.prezzoRichiesta = prezzoRichiesta;
    }

    public Double getPrezzoMinimo() {
        return prezzoMinimo;
    }

    public Incarico prezzoMinimo(Double prezzoMinimo) {
        this.prezzoMinimo = prezzoMinimo;
        return this;
    }

    public void setPrezzoMinimo(Double prezzoMinimo) {
        this.prezzoMinimo = prezzoMinimo;
    }

    public Double getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public Incarico prezzoAcquisto(Double prezzoAcquisto) {
        this.prezzoAcquisto = prezzoAcquisto;
        return this;
    }

    public void setPrezzoAcquisto(Double prezzoAcquisto) {
        this.prezzoAcquisto = prezzoAcquisto;
    }

    public CategoriaIncarico getCategoriaIncarico() {
        return categoriaIncarico;
    }

    public Incarico categoriaIncarico(CategoriaIncarico categoriaIncarico) {
        this.categoriaIncarico = categoriaIncarico;
        return this;
    }

    public void setCategoriaIncarico(CategoriaIncarico categoriaIncarico) {
        this.categoriaIncarico = categoriaIncarico;
    }
    
    public Double getImportoProvvigione() {
        return importoProvvigione;
    }

    public Incarico importoProvvigione(Double importoProvvigione) {
        this.importoProvvigione = importoProvvigione;
        return this;
    }

    public void setImportoProvvigione(Double importoProvvigione) {
        this.importoProvvigione = importoProvvigione;
    }

    public Boolean isOscuraIncarico() {
        return oscuraIncarico;
    }

    public Incarico oscuraIncarico(Boolean oscuraIncarico) {
        this.oscuraIncarico = oscuraIncarico;
        return this;
    }

    public void setOscuraIncarico(Boolean oscuraIncarico) {
        this.oscuraIncarico = oscuraIncarico;
    }
    
    public LocalDate getDataAlertFiscali() {
        return dataAlertFiscali;
    }

    public Incarico dataAlertFiscali(LocalDate dataAlertFiscali) {
        this.dataAlertFiscali = dataAlertFiscali;
        return this;
    }

    public void setDataAlertFiscali(LocalDate dataAlertFiscali) {
        this.dataAlertFiscali = dataAlertFiscali;
    }
    
    public String getRicorrenzaAlertFiscali() {
        return ricorrenzaAlertFiscali;
    }

    public Incarico ricorrenzaAlertFiscali(String ricorrenzaAlertFiscali) {
        this.ricorrenzaAlertFiscali = ricorrenzaAlertFiscali;
        return this;
    }

    public void setRicorrenzaAlertFiscali(String ricorrenzaAlertFiscali) {
        this.ricorrenzaAlertFiscali = ricorrenzaAlertFiscali;
    }
    
    public LocalDate getDataAlertCortesia() {
        return dataAlertCortesia;
    }

    public Incarico dataAlertCortesia(LocalDate dataAlertCortesia) {
        this.dataAlertCortesia = dataAlertCortesia;
        return this;
    }

    public void setDataAlertCortesia(LocalDate dataAlertCortesia) {
        this.dataAlertCortesia = dataAlertCortesia;
    }
    
    public String getRicorrenzaAlertCortesia() {
        return ricorrenzaAlertCortesia;
    }

    public Incarico ricorrenzaAlertCortesia(String ricorrenzaAlertCortesia) {
        this.ricorrenzaAlertCortesia = ricorrenzaAlertCortesia;
        return this;
    }

    public void setRicorrenzaAlertCortesia(String ricorrenzaAlertCortesia) {
        this.ricorrenzaAlertCortesia = ricorrenzaAlertCortesia;
    }
    
    public BooleanStatus getAlertRicorrenza() {
        return alertRicorrenza;
    }

    public Incarico alertRicorrenza(BooleanStatus alertRicorrenza) {
        this.alertRicorrenza = alertRicorrenza;
        return this;
    }

    public void setAlertRicorrenza(BooleanStatus alertRicorrenza) {
        this.alertRicorrenza = alertRicorrenza;
    }

    public LocalDate getDataAlertRicorrenza() {
        return dataAlertRicorrenza;
    }

    public Incarico dataAlertRicorrenza(LocalDate dataAlertRicorrenza) {
        this.dataAlertRicorrenza = dataAlertRicorrenza;
        return this;
    }

    public void setDataAlertRicorrenza(LocalDate dataAlertRicorrenza) {
        this.dataAlertRicorrenza = dataAlertRicorrenza;
    }

    public String getRicorrenzaAlertRicorrenza() {
        return ricorrenzaAlertRicorrenza;
    }

    public Incarico ricorrenzaAlertRicorrenza(String ricorrenzaAlertRicorrenza) {
        this.ricorrenzaAlertRicorrenza = ricorrenzaAlertRicorrenza;
        return this;
    }

    public void setRicorrenzaAlertRicorrenza(String ricorrenzaAlertRicorrenza) {
        this.ricorrenzaAlertRicorrenza = ricorrenzaAlertRicorrenza;
    }

    public Boolean isPrivacyAcquirenti() {
        return privacyAcquirenti;
    }

    public Incarico privacyAcquirenti(Boolean privacyAcquirenti) {
        this.privacyAcquirenti = privacyAcquirenti;
        return this;
    }

    public void setPrivacyAcquirenti(Boolean privacyAcquirenti) {
        this.privacyAcquirenti = privacyAcquirenti;
    }

    public Boolean isAntiriciclaggioAcquirenti() {
        return antiriciclaggioAcquirenti;
    }

    public Incarico antiriciclaggioAcquirenti(Boolean antiriciclaggioAcquirenti) {
        this.antiriciclaggioAcquirenti = antiriciclaggioAcquirenti;
        return this;
    }

    public void setAntiriciclaggioAcquirenti(Boolean antiriciclaggioAcquirenti) {
        this.antiriciclaggioAcquirenti = antiriciclaggioAcquirenti;
    }

    public Double getImportoProvvigioneAcquirenti() {
        return importoProvvigioneAcquirenti;
    }

    public Incarico importoProvvigioneAcquirenti(Double importoProvvigioneAcquirenti) {
        this.importoProvvigioneAcquirenti = importoProvvigioneAcquirenti;
        return this;
    }

    public void setImportoProvvigioneAcquirenti(Double importoProvvigioneAcquirenti) {
        this.importoProvvigioneAcquirenti = importoProvvigioneAcquirenti;
    }
    
    public Boolean isFlagLocato() {
        return flagLocato;
    }

    public Incarico flagLocato(Boolean flagLocato) {
        this.flagLocato = flagLocato;
        return this;
    }

    public void setFlagLocato(Boolean flagLocato) {
        this.flagLocato = flagLocato;
    }

    public String getNomeConduttore() {
        return nomeConduttore;
    }

    public Incarico nomeConduttore(String nomeConduttore) {
        this.nomeConduttore = nomeConduttore;
        return this;
    }

    public void setNomeConduttore(String nomeConduttore) {
        this.nomeConduttore = nomeConduttore;
    }

    public String getCognomeConduttore() {
        return cognomeConduttore;
    }

    public Incarico cognomeConduttore(String cognomeConduttore) {
        this.cognomeConduttore = cognomeConduttore;
        return this;
    }

    public void setCognomeConduttore(String cognomeConduttore) {
        this.cognomeConduttore = cognomeConduttore;
    }
    
    public Double getCanoneCorrisposto() {
        return canoneCorrisposto;
    }

    public Incarico canoneCorrisposto(Double canoneCorrisposto) {
        this.canoneCorrisposto = canoneCorrisposto;
        return this;
    }

    public void setCanoneCorrisposto(Double canoneCorrisposto) {
        this.canoneCorrisposto = canoneCorrisposto;
    }

    public String getTelefonoConduttore() {
        return telefonoConduttore;
    }

    public Incarico telefonoConduttore(String telefonoConduttore) {
        this.telefonoConduttore = telefonoConduttore;
        return this;
    }
    
    public LocalDate getDataInizioLocazione() {
        return dataInizioLocazione;
    }

    public Incarico dataInizioLocazione(LocalDate dataInizioLocazione) {
        this.dataInizioLocazione = dataInizioLocazione;
        return this;
    }

    public void setDataInizioLocazione(LocalDate dataInizioLocazione) {
        this.dataInizioLocazione = dataInizioLocazione;
    }

    public LocalDate getDataFineLocazione() {
        return dataFineLocazione;
    }

    public Incarico dataFineLocazione(LocalDate dataFineLocazione) {
        this.dataFineLocazione = dataFineLocazione;
        return this;
    }

    public void setDataFineLocazione(LocalDate dataFineLocazione) {
        this.dataFineLocazione = dataFineLocazione;
    }

    public String getNoteLocazione() {
        return noteLocazione;
    }

    public Incarico noteLocazione(String noteLocazione) {
        this.noteLocazione = noteLocazione;
        return this;
    }

    public void setNoteLocazione(String noteLocazione) {
        this.noteLocazione = noteLocazione;
    }

    public Set<ListaContatti> getListaContattis() {
        return listaContattis;
    }

    public Incarico listaContattis(Set<ListaContatti> listaContattis) {
        this.listaContattis = listaContattis;
        return this;
    }

    public Incarico addListaContatti(ListaContatti listaContatti) {
        this.listaContattis.add(listaContatti);
        listaContatti.setIncarico(this);
        return this;
    }

    public Incarico removeListaContatti(ListaContatti listaContatti) {
        this.listaContattis.remove(listaContatti);
        listaContatti.setIncarico(null);
        return this;
    }

    public void setListaContattis(Set<ListaContatti> listaContattis) {
        this.listaContattis = listaContattis;
    }

    public Immobile getImmobile() {
        return immobile;
    }

    public Incarico immobile(Immobile immobile) {
        this.immobile = immobile;
        return this;
    }

    public void setImmobile(Immobile immobile) {
        this.immobile = immobile;
    }

    public Set<Partner> getPartners() {
        return partners;
    }

    public Incarico partners(Set<Partner> partners) {
        this.partners = partners;
        return this;
    }

    public Incarico addPartner(Partner partner) {
        this.partners.add(partner);
        partner.getIncaricos().add(this);
        return this;
    }

    public Incarico removePartner(Partner partner) {
        this.partners.remove(partner);
        partner.getIncaricos().remove(this);
        return this;
    }

    public void setPartners(Set<Partner> partners) {
        this.partners = partners;
    }

    public Set<Cliente> getCommittentes() {
        return committentes;
    }

    public Incarico committentes(Set<Cliente> clientes) {
        this.committentes = clientes;
        return this;
    }

    public Incarico addCommittente(Cliente cliente) {
        this.committentes.add(cliente);
//        cliente.getIncaricos().add(this); //FIXME
        cliente.getIncaricoCommittentes().add(this);
        return this;
    }

    public Incarico removeCommittente(Cliente cliente) {
        this.committentes.remove(cliente);
//        cliente.getIncaricos().remove(this); //FIXME
        cliente.getIncaricoCommittentes().remove(this);
        return this;
    }

    public void setCommittentes(Set<Cliente> clientes) {
        this.committentes = clientes;
    }

    public Set<Cliente> getProponentes() {
        return proponentes;
    }

    public Incarico proponentes(Set<Cliente> clientes) {
        this.proponentes = clientes;
        return this;
    }

    public Incarico addProponente(Cliente cliente) {
        this.proponentes.add(cliente);
//        cliente.getIncaricos().add(this); //FIXME
        cliente.getIncaricoProponentes().add(this); 
        return this;
    }

    public Incarico removeProponente(Cliente cliente) {
        this.proponentes.remove(cliente);
//        cliente.getIncaricos().remove(this); //FIXME
        cliente.getIncaricoProponentes().remove(this); 
        return this;
    }

    public void setProponentes(Set<Cliente> clientes) {
        this.proponentes = clientes;
    }

    public Set<Cliente> getAcquirenteLocatarios() {
        return acquirenteLocatarios;
    }

    public Incarico acquirenteLocatarios(Set<Cliente> clientes) {
        this.acquirenteLocatarios = clientes;
        return this;
    }

    public Incarico addAcquirenteLocatario(Cliente cliente) {
        this.acquirenteLocatarios.add(cliente);
//        cliente.getIncaricos().add(this); //FIXME
        cliente.getIncaricoAcquirenteLocatarios().add(this);
        return this;
    }
    
    public Incarico addAllAcquirenteLocatario(Collection<Cliente> clienti) {
    	if(clienti!=null) {
	    	for(Cliente cliente : clienti) {
	    		this.addAcquirenteLocatario(cliente);
	    	}
    	}
    	return this;
    }
    
    public Incarico removeAcquirenteLocatario(Cliente cliente) {
        this.acquirenteLocatarios.remove(cliente);
//        cliente.getIncaricos().remove(this); //FIXME
        cliente.getIncaricoAcquirenteLocatarios().remove(this);
        return this;
    }

    public void setAcquirenteLocatarios(Set<Cliente> clientes) {
        this.acquirenteLocatarios = clientes;
    }

    public Set<Cliente> getSegnalatores() {
        return segnalatores;
    }

    public Incarico segnalatores(Set<Cliente> clientes) {
        this.segnalatores = clientes;
        return this;
    }

    public Incarico addSegnalatore(Cliente cliente) {
        this.segnalatores.add(cliente);
//        cliente.getIncaricos().add(this); //FIXME
        cliente.getIncaricoSegnalatores().add(this);
        return this;
    }

    public Incarico removeSegnalatore(Cliente cliente) {
        this.segnalatores.remove(cliente);
//        cliente.getIncaricos().remove(this); //FIXME
        cliente.getIncaricoSegnalatores().remove(this);
        return this;
    }

    public void setSegnalatores(Set<Cliente> clientes) {
        this.segnalatores = clientes;
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
        Incarico incarico = (Incarico) o;
        if (incarico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), incarico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Incarico{" +
            "id=" + getId() +
            ", riferimento='" + getRiferimento() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", categoriaIncarico='" + getCategoriaIncarico() + "'" +
            ", stato='" + getStato() + "'" +
            ", dataScadenza='" + getDataScadenza() + "'" +
            ", agente='" + getAgente() + "'" +
            ", agenteDiContatto='" + getAgenteDiContatto() + "'" +
            ", dataContatto='" + getDataContatto() + "'" +
            ", noteTrattativa='" + getNoteTrattativa() + "'" +
            ", datiFiscali='" + getDatiFiscali() + "'" +
            ", dataAlertFiscali='" + getDataAlertFiscali() + "'" +
            ", alertFiscali='" + getAlertFiscali() + "'" +
            ", ricorrenzaAlertFiscali='" + getRicorrenzaAlertFiscali() + "'" +
            ", alertCortesia='" + getAlertCortesia() + "'" +
            ", dataAlertCortesia='" + getDataAlertCortesia() + "'" +
            ", ricorrenzaAlertCortesia='" + getRicorrenzaAlertCortesia() + "'" +
            ", alertRicorrenza='" + getAlertRicorrenza() + "'" +
            ", dataAlertRicorrenza='" + getDataAlertRicorrenza() + "'" +
            ", ricorrenzaAlertRicorrenza='" + getRicorrenzaAlertRicorrenza() + "'" +
            ", privacy='" + isPrivacy() + "'" +
            ", antiriciclaggioAcquirenti='" + isAntiriciclaggioAcquirenti() + "'" +
            ", prezzoRichiesta=" + getPrezzoRichiesta() +
            ", prezzoMinimo=" + getPrezzoMinimo() +
            ", prezzoAcquisto=" + getPrezzoAcquisto() +
            ", importoProvvigione=" + getImportoProvvigione() +
            ", importoProvvigioneAcquirenti=" + getImportoProvvigioneAcquirenti() +
            ", oscuraIncarico='" + isOscuraIncarico() + "'" +
            ", flagLocato='" + isFlagLocato() + "'" +
            ", nomeConduttore='" + getNomeConduttore() + "'" +
            ", cognomeConduttore='" + getCognomeConduttore() + "'" +
            ", canoneCorrisposto=" + getCanoneCorrisposto() +
            ", telefonoConduttore='" + getTelefonoConduttore() + "'" +
            ", dataInizioLocazione='" + getDataInizioLocazione() + "'" +
            ", dataFineLocazione='" + getDataFineLocazione() + "'" +
            ", noteLocazione='" + getNoteLocazione() + "'" +
            "}";
    }
    
    public void updateFlgOscuraClienti() {
    	
    	updateFlgOscuraClienti(this.committentes, this.oscuraIncarico);
    	updateFlgOscuraClienti(this.proponentes, this.oscuraIncarico);
    	updateFlgOscuraClienti(this.acquirenteLocatarios, this.oscuraIncarico);
    	updateFlgOscuraClienti(this.segnalatores, this.oscuraIncarico);
    }
    
    private void updateFlgOscuraClienti(Collection<Cliente> clienti, Boolean flOscura) {
    	for (Cliente cliente : clienti) {
    		cliente.setAbilitato(BooleanUtils.isFalse(flOscura));
		}
    }
}
