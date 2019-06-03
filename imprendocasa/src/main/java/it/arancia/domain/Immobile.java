package it.arancia.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;

/**
 * Entity Immobile
 */
@ApiModel(description = "Entity Immobile")
@Entity
@Table(name = "immobile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Immobile extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agente")
    private String agente;
    
    @Column(name = "codice")
    private String codice;

    @Lob
    @Column(name = "descrizione")
    private String descrizione;

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

    @Column(name = "path_folder")
    private String pathFolder;

    @Column(name = "dati_catastali")
    private String datiCatastali;
    
    @Column(name = "foglio")
    private String foglio;

    @Column(name = "particella")
    private String particella;

    @Column(name = "sub")
    private String sub;

    @OneToOne(cascade = CascadeType.REMOVE)    
    @JoinColumn(unique = true)
    private Geolocalizzazione geolocalizzazione;

    @OneToMany(mappedBy = "immobile", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Files> files = new HashSet<>();
    
    @OneToMany(mappedBy = "immobile")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Incarico> incaricos = new HashSet<>();
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgente() {
        return agente;
    }

    public Immobile agente(String agente) {
        this.agente = agente;
        return this;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }
    
    public String getCodice() {
        return codice;
    }

    public Immobile codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Immobile descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Immobile indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public Immobile cap(String cap) {
        this.cap = cap;
        return this;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getRegione() {
        return regione;
    }

    public Immobile regione(String regione) {
        this.regione = regione;
        return this;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public Immobile provincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public Immobile citta(String citta) {
        this.citta = citta;
        return this;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNote() {
        return note;
    }

    public Immobile note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPathFolder() {
        return pathFolder;
    }

    public Immobile pathFolder(String pathFolder) {
        this.pathFolder = pathFolder;
        return this;
    }

    public void setPathFolder(String pathFolder) {
        this.pathFolder = pathFolder;
    }

    public String getDatiCatastali() {
        return datiCatastali;
    }

    public Immobile datiCatastali(String datiCatastali) {
        this.datiCatastali = datiCatastali;
        return this;
    }

    public void setDatiCatastali(String datiCatastali) {
        this.datiCatastali = datiCatastali;
    }
    
    public String getFoglio() {
        return foglio;
    }

    public Immobile foglio(String foglio) {
        this.foglio = foglio;
        return this;
    }

    public void setFoglio(String foglio) {
        this.foglio = foglio;
    }

    public String getParticella() {
        return particella;
    }

    public Immobile particella(String particella) {
        this.particella = particella;
        return this;
    }

    public void setParticella(String particella) {
        this.particella = particella;
    }

    public String getSub() {
        return sub;
    }

    public Immobile sub(String sub) {
        this.sub = sub;
        return this;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Geolocalizzazione getGeolocalizzazione() {
        return geolocalizzazione;
    }

    public Immobile geolocalizzazione(Geolocalizzazione geolocalizzazione) {
        this.geolocalizzazione = geolocalizzazione;
        return this;
    }

    public void setGeolocalizzazione(Geolocalizzazione geolocalizzazione) {
        this.geolocalizzazione = geolocalizzazione;
    }

    public Set<Files> getFiles() {
        return files;
    }

    public Immobile files(Set<Files> files) {
        this.files = files;
        return this;
    }

    public Immobile addFiles(Files files) {
        this.files.add(files);
        files.setImmobile(this);
        return this;
    }

    public Immobile removeFiles(Files files) {
        this.files.remove(files);
        files.setImmobile(null);
        return this;
    }

    public void setFiles(Set<Files> files) {
        this.files = files;
    }

    public Set<Incarico> getIncaricos() {
        return incaricos;
    }

    public Immobile incaricos(Set<Incarico> incaricos) {
        this.incaricos = incaricos;
        return this;
    }

    public Immobile addIncarico(Incarico incarico) {
        this.incaricos.add(incarico);
        incarico.setImmobile(this);
        return this;
    }

    public Immobile removeIncarico(Incarico incarico) {
        this.incaricos.remove(incarico);
        incarico.setImmobile(null);
        return this;
    }

    public void setIncaricos(Set<Incarico> incaricos) {
        this.incaricos = incaricos;
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
        Immobile immobile = (Immobile) o;
        if (immobile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), immobile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Immobile{" +
            "id=" + getId() +
            ", agente='" + getAgente() + "'" +
            ", codice='" + getCodice() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", cap='" + getCap() + "'" +
            ", regione='" + getRegione() + "'" +
            ", provincia='" + getProvincia() + "'" +
            ", citta='" + getCitta() + "'" +
            ", note='" + getNote() + "'" +
            ", pathFolder='" + getPathFolder() + "'" +
            ", datiCatastali='" + getDatiCatastali() + "'" +
            ", foglio='" + getFoglio() + "'" +
            ", particella='" + getParticella() + "'" +
            ", sub='" + getSub() + "'" +
            "}";
    }
}
