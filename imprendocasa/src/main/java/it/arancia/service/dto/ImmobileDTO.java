package it.arancia.service.dto;

import java.util.Objects;

import javax.persistence.Lob;
import javax.validation.constraints.Size;

/**
 * A DTO for the Immobile entity.
 */
@SuppressWarnings("serial")
public class ImmobileDTO extends AbstractAuditingDTO implements EditableDTO {

    private Long id;

    private String codice;
    
    private String agente;

    @Lob
    private String descrizione;

    private String indirizzo;

    @Size(max = 20)
    private String cap;

    private String regione;

    private String provincia;

    private String citta;

    @Lob
    private String note;

    private String pathFolder;

    private String datiCatastali;
    
    private String foglio;

    private String particella;

    private String sub;

    private Long geolocalizzazioneId;

    private String geolocalizzazioneImmobile;
    
	private Boolean editAvaiable;

	private Boolean deleteAvaiable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }
    
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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

    public String getPathFolder() {
        return pathFolder;
    }

    public void setPathFolder(String pathFolder) {
        this.pathFolder = pathFolder;
    }

    public String getDatiCatastali() {
        return datiCatastali;
    }

    public void setDatiCatastali(String datiCatastali) {
        this.datiCatastali = datiCatastali;
    }

    public String getFoglio() {
        return foglio;
    }

    public void setFoglio(String foglio) {
        this.foglio = foglio;
    }

    public String getParticella() {
        return particella;
    }

    public void setParticella(String particella) {
        this.particella = particella;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
    
    public Long getGeolocalizzazioneId() {
        return geolocalizzazioneId;
    }

    public void setGeolocalizzazioneId(Long geolocalizzazioneId) {
        this.geolocalizzazioneId = geolocalizzazioneId;
    }

    public String getGeolocalizzazioneImmobile() {
        return geolocalizzazioneImmobile;
    }

    public void setGeolocalizzazioneImmobile(String geolocalizzazioneImmobile) {
        this.geolocalizzazioneImmobile = geolocalizzazioneImmobile;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ImmobileDTO immobileDTO = (ImmobileDTO) o;
        if (immobileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), immobileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ImmobileDTO{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", agente='" + getAgente() + "'" +
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
            ", geolocalizzazione=" + getGeolocalizzazioneId() +
            ", geolocalizzazione='" + getGeolocalizzazioneImmobile() + "'" +
            "}";
    }

	
}
