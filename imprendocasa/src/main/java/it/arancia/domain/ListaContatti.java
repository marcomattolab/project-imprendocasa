package it.arancia.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import it.arancia.domain.enumeration.EsitoChiamata;

/**
 * Entity Lista contatti
 */
@ApiModel(description = "Entity Lista contatti")
@Entity
@Table(name = "lista_contatti")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ListaContatti extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "date_time", nullable = false)
    private ZonedDateTime dateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "esito", nullable = false)
    private EsitoChiamata esito;

    @NotNull
    @Column(name = "motivazione", nullable = false)
    private String motivazione;

    @Lob
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JsonIgnoreProperties("listaContattis")
    private Cliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("listaContattis")
    private Incarico incarico;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public ListaContatti dateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public EsitoChiamata getEsito() {
        return esito;
    }

    public ListaContatti esito(EsitoChiamata esito) {
        this.esito = esito;
        return this;
    }

    public void setEsito(EsitoChiamata esito) {
        this.esito = esito;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public ListaContatti motivazione(String motivazione) {
        this.motivazione = motivazione;
        return this;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public String getNote() {
        return note;
    }

    public ListaContatti note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ListaContatti cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Incarico getIncarico() {
        return incarico;
    }

    public ListaContatti incarico(Incarico incarico) {
        this.incarico = incarico;
        return this;
    }

    public void setIncarico(Incarico incarico) {
        this.incarico = incarico;
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
        ListaContatti listaContatti = (ListaContatti) o;
        if (listaContatti.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), listaContatti.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ListaContatti{" +
            "id=" + getId() +
            ", dateTime='" + getDateTime() + "'" +
            ", esito='" + getEsito() + "'" +
            ", motivazione='" + getMotivazione() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
