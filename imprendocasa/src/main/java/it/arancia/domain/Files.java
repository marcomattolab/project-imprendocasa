package it.arancia.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity Files
 */
@ApiModel(description = "Entity Files")
@Entity
@Table(name = "files")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Files extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dimensione")
    private String dimensione;

    @Column(name = "estensione")
    private String estensione;

    @ManyToOne
    @JsonIgnoreProperties("files")
    private Immobile immobile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Files nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDimensione() {
        return dimensione;
    }

    public Files dimensione(String dimensione) {
        this.dimensione = dimensione;
        return this;
    }

    public void setDimensione(String dimensione) {
        this.dimensione = dimensione;
    }

    public String getEstensione() {
        return estensione;
    }

    public Files estensione(String estensione) {
        this.estensione = estensione;
        return this;
    }

    public void setEstensione(String estensione) {
        this.estensione = estensione;
    }

    public Immobile getImmobile() {
        return immobile;
    }

    public Files immobile(Immobile immobile) {
        this.immobile = immobile;
        return this;
    }

    public void setImmobile(Immobile immobile) {
        this.immobile = immobile;
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
        Files files = (Files) o;
        if (files.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), files.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Files{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dimensione='" + getDimensione() + "'" +
            ", estensione='" + getEstensione() + "'" +
            "}";
    }
}
