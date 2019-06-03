package it.arancia.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AppSettings entity.
 */
public class AppSettingsDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String categoria;

    @NotNull
    private String chiave;

    @NotNull
    private String valore;

    private Boolean abilitato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
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

        AppSettingsDTO appSettingsDTO = (AppSettingsDTO) o;
        if (appSettingsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appSettingsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppSettingsDTO{" +
            "id=" + getId() +
            ", categoria='" + getCategoria() + "'" +
            ", chiave='" + getChiave() + "'" +
            ", valore='" + getValore() + "'" +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
}
