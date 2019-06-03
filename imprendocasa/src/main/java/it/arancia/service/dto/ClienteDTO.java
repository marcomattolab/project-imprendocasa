package it.arancia.service.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.TipoMese;

/**
 * A DTO for the Cliente entity.
 */
@SuppressWarnings("serial")
public class ClienteDTO extends AbstractAuditingDTO implements EditableDTO {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    private String codiceFiscale;
    
    private String titolo;

    private String ragioneSociale;

    private String agente;

    private BooleanStatus alertCompleanno;

    @NotNull
    private LocalDate dataNascita;

    private TipoMese meseNascita;

    private String telefonoFisso;

    private String telefonoCellulare;

    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    private String email;

    private String indirizzo;

    @Size(max = 20)
    private String cap;

    private String regione;

    private String provincia;

    private String citta;

    @Lob
    private String note;

    private String codiceAntiriciclaggio;

    private Double importoProvvigioni;

    private Double numeroPratiche;

    private Double numeroSegnalazioni;
    
    private Double importoProvvigioniDerivate;

    private Double punteggio;

    private Boolean abilitato;

    private Boolean editAvaiable;

    private Boolean deleteAvaiable;

    private Set<TagDTO> tags = new HashSet<>();

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

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public BooleanStatus getAlertCompleanno() {
        return alertCompleanno;
    }

    public void setAlertCompleanno(BooleanStatus alertCompleanno) {
        this.alertCompleanno = alertCompleanno;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public TipoMese getMeseNascita() {
        return meseNascita;
    }

    public void setMeseNascita(TipoMese meseNascita) {
        this.meseNascita = meseNascita;
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

    public String getCodiceAntiriciclaggio() {
        return codiceAntiriciclaggio;
    }

    public void setCodiceAntiriciclaggio(String codiceAntiriciclaggio) {
        this.codiceAntiriciclaggio = codiceAntiriciclaggio;
    }

    public Double getImportoProvvigioni() {
        return importoProvvigioni;
    }

    public void setImportoProvvigioni(Double importoProvvigioni) {
        this.importoProvvigioni = importoProvvigioni;
    }

    public Double getNumeroPratiche() {
        return numeroPratiche;
    }

    public void setNumeroPratiche(Double numeroPratiche) {
        this.numeroPratiche = numeroPratiche;
    }

    public Double getNumeroSegnalazioni() {
        return numeroSegnalazioni;
    }

    public void setNumeroSegnalazioni(Double numeroSegnalazioni) {
        this.numeroSegnalazioni = numeroSegnalazioni;
    }

    public Double getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(Double punteggio) {
        this.punteggio = punteggio;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public void setAbilitato(Boolean abilitato) {
        this.abilitato = abilitato;
    }

	@Override
	public Boolean getEditAvaiable() {
		return editAvaiable;
	}
	@Override
	public void setEditAvaiable(Boolean editAvaiable) {
		this.editAvaiable = editAvaiable;
	}
	@Override
	public Boolean getDeleteAvaiable() {
		return deleteAvaiable;
	}
	@Override
	public void setDeleteAvaiable(Boolean deleteAvaiable) {
		this.deleteAvaiable = deleteAvaiable;
	}
	
	@Override
	public String getAgenteCreatedBy() {
		return agente;
	}

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }
    
    public Double getImportoProvvigioniDerivate() {
        return importoProvvigioniDerivate;
    }

    public void setImportoProvvigioniDerivate(Double importoProvvigioniDerivate) {
        this.importoProvvigioniDerivate = importoProvvigioniDerivate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClienteDTO clienteDTO = (ClienteDTO) o;
        if (clienteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clienteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cognome='" + getCognome() + "'" +
            ", codiceFiscale='" + getCodiceFiscale() + "'" +
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
            ", numeroPratiche=" + getNumeroPratiche() +
            ", numeroSegnalazioni=" + getNumeroSegnalazioni() +
            ", punteggio=" + getPunteggio() +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
	
}
