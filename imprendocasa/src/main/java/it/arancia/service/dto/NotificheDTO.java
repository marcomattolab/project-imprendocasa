package it.arancia.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import it.arancia.domain.enumeration.CanaleNotifica;
import it.arancia.domain.enumeration.TipoNotifica;
import it.arancia.domain.enumeration.CategoriaNotifica;
import it.arancia.domain.enumeration.TipoEsito;

/**
 * A DTO for the Notifiche entity.
 */
public class NotificheDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private CanaleNotifica canaleNotifica;

    @NotNull
    private TipoNotifica tipoNotifica;

    @NotNull
    private CategoriaNotifica categoriaNotifica;

    private String oggettoNotifica;

    @Lob
    private String testoNotifica;

    private Integer numeroDestinatari;

    @Lob
    private String destinatariNotifica;

    @NotNull
    private TipoEsito esitoNotifica;

    @Lob
    private String dettagliEsito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CanaleNotifica getCanaleNotifica() {
        return canaleNotifica;
    }

    public void setCanaleNotifica(CanaleNotifica canaleNotifica) {
        this.canaleNotifica = canaleNotifica;
    }

    public TipoNotifica getTipoNotifica() {
        return tipoNotifica;
    }

    public void setTipoNotifica(TipoNotifica tipoNotifica) {
        this.tipoNotifica = tipoNotifica;
    }

    public CategoriaNotifica getCategoriaNotifica() {
        return categoriaNotifica;
    }

    public void setCategoriaNotifica(CategoriaNotifica categoriaNotifica) {
        this.categoriaNotifica = categoriaNotifica;
    }

    public String getOggettoNotifica() {
        return oggettoNotifica;
    }

    public void setOggettoNotifica(String oggettoNotifica) {
        this.oggettoNotifica = oggettoNotifica;
    }

    public String getTestoNotifica() {
        return testoNotifica;
    }

    public void setTestoNotifica(String testoNotifica) {
        this.testoNotifica = testoNotifica;
    }

    public Integer getNumeroDestinatari() {
        return numeroDestinatari;
    }

    public void setNumeroDestinatari(Integer numeroDestinatari) {
        this.numeroDestinatari = numeroDestinatari;
    }

    public String getDestinatariNotifica() {
        return destinatariNotifica;
    }

    public void setDestinatariNotifica(String destinatariNotifica) {
        this.destinatariNotifica = destinatariNotifica;
    }

    public TipoEsito getEsitoNotifica() {
        return esitoNotifica;
    }

    public void setEsitoNotifica(TipoEsito esitoNotifica) {
        this.esitoNotifica = esitoNotifica;
    }

    public String getDettagliEsito() {
        return dettagliEsito;
    }

    public void setDettagliEsito(String dettagliEsito) {
        this.dettagliEsito = dettagliEsito;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificheDTO notificheDTO = (NotificheDTO) o;
        if (notificheDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificheDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificheDTO{" +
            "id=" + getId() +
            ", canaleNotifica='" + getCanaleNotifica() + "'" +
            ", tipoNotifica='" + getTipoNotifica() + "'" +
            ", categoriaNotifica='" + getCategoriaNotifica() + "'" +
            ", oggettoNotifica='" + getOggettoNotifica() + "'" +
            ", testoNotifica='" + getTestoNotifica() + "'" +
            ", numeroDestinatari=" + getNumeroDestinatari() +
            ", destinatariNotifica='" + getDestinatariNotifica() + "'" +
            ", esitoNotifica='" + getEsitoNotifica() + "'" +
            ", dettagliEsito='" + getDettagliEsito() + "'" +
            "}";
    }
}
