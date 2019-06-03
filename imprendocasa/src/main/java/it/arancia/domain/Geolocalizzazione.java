package it.arancia.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity Geolocalizzazione
 */
@ApiModel(description = "Entity Geolocalizzazione")
@Entity
@Table(name = "geolocalizzazione")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Geolocalizzazione extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "immobile")
    private String immobile;

    @Column(name = "latitudine")
    private String latitudine;

    @Column(name = "longitudine")
    private String longitudine;

    @OneToOne(mappedBy = "geolocalizzazione")
    @JsonIgnore
    private Immobile posizione;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImmobile() {
        return immobile;
    }

    public Geolocalizzazione immobile(String immobile) {
        this.immobile = immobile;
        return this;
    }

    public void setImmobile(String immobile) {
        this.immobile = immobile;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public Geolocalizzazione latitudine(String latitudine) {
        this.latitudine = latitudine;
        return this;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public Geolocalizzazione longitudine(String longitudine) {
        this.longitudine = longitudine;
        return this;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public Immobile getPosizione() {
        return posizione;
    }

    public Geolocalizzazione posizione(Immobile immobile) {
        this.posizione = immobile;
        return this;
    }

    public void setPosizione(Immobile immobile) {
        this.posizione = immobile;
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
        Geolocalizzazione geolocalizzazione = (Geolocalizzazione) o;
        if (geolocalizzazione.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), geolocalizzazione.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Geolocalizzazione{" +
            "id=" + getId() +
            ", immobile='" + getImmobile() + "'" +
            ", latitudine='" + getLatitudine() + "'" +
            ", longitudine='" + getLongitudine() + "'" +
            "}";
    }
}
