package it.arancia.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import it.arancia.domain.enumeration.TipoIndirizzo;

/**
 * A DTO for the Partner entity.
 */
public class PartnerDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    private String codiceFiscale;

    private String telefonoFisso;

    private String telefonoCellulare;

    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String email;

    private TipoIndirizzo tipoIndirizzo;

    private String indirizzo;

    @Size(max = 20)
    private String cap;

    private String regione;

    private String provincia;

    private String citta;

    @Lob
    private String note;

    private Boolean abilitato;

    private Long professioneId;

    private String professioneDenominazione;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getTelefonoFisso() {
        return telefonoFisso;
    }

    public void setTelefonoFisso(String telefonoFisso) {
        this.telefonoFisso = telefonoFisso;
    }

    public String getTelefonoCellulare() {
        return telefonoCellulare;
    }

    public void setTelefonoCellulare(String telefonoCellulare) {
        this.telefonoCellulare = telefonoCellulare;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoIndirizzo getTipoIndirizzo() {
        return tipoIndirizzo;
    }

    public void setTipoIndirizzo(TipoIndirizzo tipoIndirizzo) {
        this.tipoIndirizzo = tipoIndirizzo;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public void setAbilitato(Boolean abilitato) {
        this.abilitato = abilitato;
    }

    public Long getProfessioneId() {
        return professioneId;
    }

    public void setProfessioneId(Long professioneId) {
        this.professioneId = professioneId;
    }

    public String getProfessioneDenominazione() {
        return professioneDenominazione;
    }

    public void setProfessioneDenominazione(String professioneDenominazione) {
        this.professioneDenominazione = professioneDenominazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PartnerDTO partnerDTO = (PartnerDTO) o;
        if (partnerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), partnerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PartnerDTO{" +
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
            ", professione=" + getProfessioneId() +
            ", professione='" + getProfessioneDenominazione() + "'" +
            "}";
    }
}
