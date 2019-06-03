package it.arancia.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


import it.arancia.domain.enumeration.TipoIndirizzo;

/**
 * Entity Partner
 */
@ApiModel(description = "Entity Partner")
@Entity
@Table(name = "partner")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Partner extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "codice_fiscale")
    private String codiceFiscale;

    @Column(name = "telefono_fisso")
    private String telefonoFisso;

    @Column(name = "telefono_cellulare")
    private String telefonoCellulare;

    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_indirizzo")
    private TipoIndirizzo tipoIndirizzo;

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

    @Column(name = "abilitato")
    private Boolean abilitato;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Professione professione;

    @ManyToMany(mappedBy = "partners")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Incarico> incaricos = new HashSet<>();

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

    public Partner nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Partner cognome(String cognome) {
        this.cognome = cognome;
        return this;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public Partner codiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
        return this;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getTelefonoFisso() {
        return telefonoFisso;
    }

    public Partner telefonoFisso(String telefonoFisso) {
        this.telefonoFisso = telefonoFisso;
        return this;
    }

    public void setTelefonoFisso(String telefonoFisso) {
        this.telefonoFisso = telefonoFisso;
    }

    public String getTelefonoCellulare() {
        return telefonoCellulare;
    }

    public Partner telefonoCellulare(String telefonoCellulare) {
        this.telefonoCellulare = telefonoCellulare;
        return this;
    }

    public void setTelefonoCellulare(String telefonoCellulare) {
        this.telefonoCellulare = telefonoCellulare;
    }

    public String getEmail() {
        return email;
    }

    public Partner email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoIndirizzo getTipoIndirizzo() {
        return tipoIndirizzo;
    }

    public Partner tipoIndirizzo(TipoIndirizzo tipoIndirizzo) {
        this.tipoIndirizzo = tipoIndirizzo;
        return this;
    }

    public void setTipoIndirizzo(TipoIndirizzo tipoIndirizzo) {
        this.tipoIndirizzo = tipoIndirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Partner indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public Partner cap(String cap) {
        this.cap = cap;
        return this;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getRegione() {
        return regione;
    }

    public Partner regione(String regione) {
        this.regione = regione;
        return this;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public Partner provincia(String provincia) {
        this.provincia = provincia;
        return this;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public Partner citta(String citta) {
        this.citta = citta;
        return this;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNote() {
        return note;
    }

    public Partner note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public Partner abilitato(Boolean abilitato) {
        this.abilitato = abilitato;
        return this;
    }

    public void setAbilitato(Boolean abilitato) {
        this.abilitato = abilitato;
    }

    public Professione getProfessione() {
        return professione;
    }

    public Partner professione(Professione professione) {
        this.professione = professione;
        return this;
    }

    public void setProfessione(Professione professione) {
        this.professione = professione;
    }

    public Set<Incarico> getIncaricos() {
        return incaricos;
    }

    public Partner incaricos(Set<Incarico> incaricos) {
        this.incaricos = incaricos;
        return this;
    }

    public Partner addIncarico(Incarico incarico) {
        this.incaricos.add(incarico);
        incarico.getPartners().add(this);
        return this;
    }

    public Partner removeIncarico(Incarico incarico) {
        this.incaricos.remove(incarico);
        incarico.getPartners().remove(this);
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
        Partner partner = (Partner) o;
        if (partner.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), partner.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Partner{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
            ", telefonoFisso='" + getTelefonoFisso() + "'" +
            ", telefonoCellulare='" + getTelefonoCellulare() + "'" +
            ", email='" + getEmail() + "'" +
            ", tipoIndirizzo='" + getTipoIndirizzo() + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", cap='" + getCap() + "'" +
            ", regione='" + getRegione() + "'" +
            ", provincia='" + getProvincia() + "'" +
            ", citta='" + getCitta() + "'" +
            ", note='" + getNote() + "'" +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
}
