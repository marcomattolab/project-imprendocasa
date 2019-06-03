package it.arancia.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.arancia.domain.enumeration.StatoIncarico;

/**
 * A DTO for the Incarico Statutes entity: BOZZA, ATTIVO, CONCLUSO, INTERROTTO, SCADUTO
 */
public class IncaricoStatutesDTO implements Serializable {
    private Long id;
    private boolean changeStatusEnabled;
    private StatoIncarico status;							     //Stato corrente
    private List<StatoIncarico> statuses = new ArrayList<>();    //Possibili Stati Destinazione
    private List<StatoIncarico> allStatuses = new ArrayList<>(); //Elenco Tutti Possibili Stati
    private String errorCode;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatoIncarico getStatus() {
		return status;
	}

	public void setStatus(StatoIncarico status) {
		this.status = status;
	}

	public boolean isChangeStatusEnabled() {
		return changeStatusEnabled;
	}

	public void setChangeStatusEnabled(boolean changeStatusEnabled) {
		this.changeStatusEnabled = changeStatusEnabled;
	}

	public List<StatoIncarico> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<StatoIncarico> statuses) {
		this.statuses = statuses;
	}

	public List<StatoIncarico> getAllStatuses() {
		return allStatuses;
	}

	public void setAllStatuses(List<StatoIncarico> allStatuses) {
		this.allStatuses = allStatuses;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IncaricoStatutesDTO incaricoDTO = (IncaricoStatutesDTO) o;
        if (incaricoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), incaricoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IncaricoStatutesDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", statuses='" + getStatuses() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", changeStatusEnabled='" + isChangeStatusEnabled() + "'" +
            "}";
    }
}
