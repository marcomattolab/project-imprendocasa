package it.arancia.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity AppSettings
 */
@ApiModel(description = "Entity AppSettings")
@Entity
@Table(name = "app_settings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AppSettings extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @NotNull
    @Column(name = "chiave", nullable = false)
    private String chiave;

    @NotNull
    @Column(name = "valore", nullable = false)
    private String valore;

    @Column(name = "abilitato")
    private Boolean abilitato;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public AppSettings categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getChiave() {
        return chiave;
    }

    public AppSettings chiave(String chiave) {
        this.chiave = chiave;
        return this;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    public String getValore() {
        return valore;
    }

    public AppSettings valore(String valore) {
        this.valore = valore;
        return this;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public AppSettings abilitato(Boolean abilitato) {
        this.abilitato = abilitato;
        return this;
    }

    public void setAbilitato(Boolean abilitato) {
        this.abilitato = abilitato;
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
        AppSettings appSettings = (AppSettings) o;
        if (appSettings.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), appSettings.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AppSettings{" +
            "id=" + getId() +
            ", categoria='" + getCategoria() + "'" +
            ", chiave='" + getChiave() + "'" +
            ", valore='" + getValore() + "'" +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
}
