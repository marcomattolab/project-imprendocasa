package it.arancia.domain;

import io.swagger.annotations.ApiModel;
import it.arancia.domain.enumeration.CategoriaNotifica;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entity MailTemplate
 */
@ApiModel(description = "Entity MailTemplate")
@Entity
@Table(name = "mail_template")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MailTemplate extends AbstractAuditingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "codice", nullable = false)
    private String codice;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private CategoriaNotifica categoria;

    @Column(name = "oggetto")
    private String oggetto;

    
    @Lob
    @Column(name = "modello", nullable = false)
    private String modello;

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

    public MailTemplate codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public CategoriaNotifica getCategoria() {
        return categoria;
    }

    public MailTemplate categoria(CategoriaNotifica categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(CategoriaNotifica categoria) {
        this.categoria = categoria;
    }

    public String getOggetto() {
        return oggetto;
    }

    public MailTemplate oggetto(String oggetto) {
        this.oggetto = oggetto;
        return this;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getModello() {
        return modello;
    }

    public MailTemplate modello(String modello) {
        this.modello = modello;
        return this;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Boolean isAbilitato() {
        return abilitato;
    }

    public MailTemplate abilitato(Boolean abilitato) {
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
        MailTemplate mailTemplate = (MailTemplate) o;
        if (mailTemplate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mailTemplate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MailTemplate{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", oggetto='" + getOggetto() + "'" +
            ", modello='" + getModello() + "'" +
            ", abilitato='" + isAbilitato() + "'" +
            "}";
    }
}
