package it.arancia.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import it.arancia.domain.enumeration.CategoriaNotifica;

/**
 * A DTO for the MailTemplate entity.
 */
public class MailTemplateDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String codice;

    @NotNull
    private CategoriaNotifica categoria;

    private String oggetto;

    
    @Lob
    private String modello;

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

    public CategoriaNotifica getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaNotifica categoria) {
        this.categoria = categoria;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
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

        MailTemplateDTO mailTemplateDTO = (MailTemplateDTO) o;
        if (mailTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mailTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MailTemplateDTO{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", oggetto='" + getOggetto() + "'" +
            ", modello='" + getModello() + "'" +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
}
