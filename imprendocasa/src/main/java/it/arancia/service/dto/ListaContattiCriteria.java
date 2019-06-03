package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;
import it.arancia.domain.enumeration.EsitoChiamata;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the ListaContatti entity. This class is used in ListaContattiResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /lista-contattis?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ListaContattiCriteria implements Serializable {
    /**
     * Class for filtering EsitoChiamata
     */
    public static class EsitoChiamataFilter extends Filter<EsitoChiamata> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private ZonedDateTimeFilter dateTime;

    private EsitoChiamataFilter esito;

    private StringFilter motivazione;

    private LongFilter clienteId;

    private LongFilter incaricoId;

    public ListaContattiCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public ZonedDateTimeFilter getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTimeFilter dateTime) {
        this.dateTime = dateTime;
    }

    public EsitoChiamataFilter getEsito() {
        return esito;
    }

    public void setEsito(EsitoChiamataFilter esito) {
        this.esito = esito;
    }

    public StringFilter getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(StringFilter motivazione) {
        this.motivazione = motivazione;
    }

    public LongFilter getClienteId() {
        return clienteId;
    }

    public void setClienteId(LongFilter clienteId) {
        this.clienteId = clienteId;
    }

    public LongFilter getIncaricoId() {
        return incaricoId;
    }

    public void setIncaricoId(LongFilter incaricoId) {
        this.incaricoId = incaricoId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ListaContattiCriteria that = (ListaContattiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(dateTime, that.dateTime) &&
            Objects.equals(esito, that.esito) &&
            Objects.equals(motivazione, that.motivazione) &&
            Objects.equals(clienteId, that.clienteId) &&
            Objects.equals(incaricoId, that.incaricoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        dateTime,
        esito,
        motivazione,
        clienteId,
        incaricoId
        );
    }

    @Override
    public String toString() {
        return "ListaContattiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (dateTime != null ? "dateTime=" + dateTime + ", " : "") +
                (esito != null ? "esito=" + esito + ", " : "") +
                (motivazione != null ? "motivazione=" + motivazione + ", " : "") +
                (clienteId != null ? "clienteId=" + clienteId + ", " : "") +
                (incaricoId != null ? "incaricoId=" + incaricoId + ", " : "") +
            "}";
    }

}
