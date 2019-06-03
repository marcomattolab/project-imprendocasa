package it.arancia.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

import it.arancia.domain.enumeration.CanaleNotifica;

import it.arancia.domain.enumeration.TipoNotifica;

import it.arancia.domain.enumeration.CategoriaNotifica;

import it.arancia.domain.enumeration.TipoEsito;

/**
 * Entity Notifiche
 */
@ApiModel(description = "Entity Notifiche")
@Entity
@Table(name = "notifiche")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Notifiche extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "canale_notifica", nullable = false)
    private CanaleNotifica canaleNotifica;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_notifica", nullable = false)
    private TipoNotifica tipoNotifica;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_notifica", nullable = false)
    private CategoriaNotifica categoriaNotifica;

    @Column(name = "oggetto_notifica")
    private String oggettoNotifica;

    @Lob
    @Column(name = "testo_notifica")
    private String testoNotifica;

    @Column(name = "numero_destinatari")
    private Integer numeroDestinatari;

    @Lob
    @Column(name = "destinatari_notifica")
    private String destinatariNotifica;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "esito_notifica", nullable = false)
    private TipoEsito esitoNotifica;

    @Lob
    @Column(name = "dettagli_esito")
    private String dettagliEsito;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CanaleNotifica getCanaleNotifica() {
        return canaleNotifica;
    }

    public Notifiche canaleNotifica(CanaleNotifica canaleNotifica) {
        this.canaleNotifica = canaleNotifica;
        return this;
    }

    public void setCanaleNotifica(CanaleNotifica canaleNotifica) {
        this.canaleNotifica = canaleNotifica;
    }

    public TipoNotifica getTipoNotifica() {
        return tipoNotifica;
    }

    public Notifiche tipoNotifica(TipoNotifica tipoNotifica) {
        this.tipoNotifica = tipoNotifica;
        return this;
    }

    public void setTipoNotifica(TipoNotifica tipoNotifica) {
        this.tipoNotifica = tipoNotifica;
    }

    public CategoriaNotifica getCategoriaNotifica() {
        return categoriaNotifica;
    }

    public Notifiche categoriaNotifica(CategoriaNotifica categoriaNotifica) {
        this.categoriaNotifica = categoriaNotifica;
        return this;
    }

    public void setCategoriaNotifica(CategoriaNotifica categoriaNotifica) {
        this.categoriaNotifica = categoriaNotifica;
    }

    public String getOggettoNotifica() {
        return oggettoNotifica;
    }

    public Notifiche oggettoNotifica(String oggettoNotifica) {
        this.oggettoNotifica = oggettoNotifica;
        return this;
    }

    public void setOggettoNotifica(String oggettoNotifica) {
        this.oggettoNotifica = oggettoNotifica;
    }

    public String getTestoNotifica() {
        return testoNotifica;
    }

    public Notifiche testoNotifica(String testoNotifica) {
        this.testoNotifica = testoNotifica;
        return this;
    }

    public void setTestoNotifica(String testoNotifica) {
        this.testoNotifica = testoNotifica;
    }

    public Integer getNumeroDestinatari() {
        return numeroDestinatari;
    }

    public Notifiche numeroDestinatari(Integer numeroDestinatari) {
        this.numeroDestinatari = numeroDestinatari;
        return this;
    }

    public void setNumeroDestinatari(Integer numeroDestinatari) {
        this.numeroDestinatari = numeroDestinatari;
    }

    public String getDestinatariNotifica() {
        return destinatariNotifica;
    }

    public Notifiche destinatariNotifica(String destinatariNotifica) {
        this.destinatariNotifica = destinatariNotifica;
        return this;
    }

    public void setDestinatariNotifica(String destinatariNotifica) {
        this.destinatariNotifica = destinatariNotifica;
    }

    public TipoEsito getEsitoNotifica() {
        return esitoNotifica;
    }

    public Notifiche esitoNotifica(TipoEsito esitoNotifica) {
        this.esitoNotifica = esitoNotifica;
        return this;
    }

    public void setEsitoNotifica(TipoEsito esitoNotifica) {
        this.esitoNotifica = esitoNotifica;
    }

    public String getDettagliEsito() {
        return dettagliEsito;
    }

    public Notifiche dettagliEsito(String dettagliEsito) {
        this.dettagliEsito = dettagliEsito;
        return this;
    }

    public void setDettagliEsito(String dettagliEsito) {
        this.dettagliEsito = dettagliEsito;
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
        Notifiche notifiche = (Notifiche) o;
        if (notifiche.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notifiche.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Notifiche{" +
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
