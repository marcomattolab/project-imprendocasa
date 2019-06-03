package it.arancia.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Professione entity.
 */
public class ProfessioneDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String codice;

    @NotNull
    private String denominazione;

    private Boolean abilitato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public void setAbilitato(Boolean abilitato) {
        this.abilitato = abilitato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfessioneDTO professioneDTO = (ProfessioneDTO) o;
        if (professioneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), professioneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfessioneDTO{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", denominazione='" + getDenominazione() + "'" +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
}
