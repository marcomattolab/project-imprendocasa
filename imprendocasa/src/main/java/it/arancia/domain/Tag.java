package it.arancia.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity Tag
 */
@ApiModel(description = "Entity Tag")
@Entity
@Table(name = "tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tag extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codice", nullable = false)
    private String codice;

    @NotNull
    @Column(name = "denominazione", nullable = false)
    private String denominazione;

    @Column(name = "abilitato")
    private Boolean abilitato;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public Tag codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDenominazione() {
        return denominazione;
    }

    public Tag denominazione(String denominazione) {
        this.denominazione = denominazione;
        return this;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public Tag abilitato(Boolean abilitato) {
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
        Tag tag = (Tag) o;
        if (tag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tag{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", denominazione='" + getDenominazione() + "'" +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
}
