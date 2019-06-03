package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the Geolocalizzazione entity. This class is used in GeolocalizzazioneResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /geolocalizzaziones?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GeolocalizzazioneCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter immobile;

    private StringFilter latitudine;

    private StringFilter longitudine;

    private LongFilter posizioneId;

    public GeolocalizzazioneCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getImmobile() {
        return immobile;
    }

    public void setImmobile(StringFilter immobile) {
        this.immobile = immobile;
    }

    public StringFilter getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(StringFilter latitudine) {
        this.latitudine = latitudine;
    }

    public StringFilter getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(StringFilter longitudine) {
        this.longitudine = longitudine;
    }

    public LongFilter getPosizioneId() {
        return posizioneId;
    }

    public void setPosizioneId(LongFilter posizioneId) {
        this.posizioneId = posizioneId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final GeolocalizzazioneCriteria that = (GeolocalizzazioneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(immobile, that.immobile) &&
            Objects.equals(latitudine, that.latitudine) &&
            Objects.equals(longitudine, that.longitudine) &&
            Objects.equals(posizioneId, that.posizioneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        immobile,
        latitudine,
        longitudine,
        posizioneId
        );
    }

    @Override
    public String toString() {
        return "GeolocalizzazioneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (immobile != null ? "immobile=" + immobile + ", " : "") +
                (latitudine != null ? "latitudine=" + latitudine + ", " : "") +
                (longitudine != null ? "longitudine=" + longitudine + ", " : "") +
                (posizioneId != null ? "posizioneId=" + posizioneId + ", " : "") +
            "}";
    }

}
