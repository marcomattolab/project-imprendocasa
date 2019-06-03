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
 * Criteria class for the Professione entity. This class is used in ProfessioneResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /professiones?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProfessioneCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter codice;

    private StringFilter denominazione;

    private BooleanFilter abilitato;

    public ProfessioneCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCodice() {
        return codice;
    }

    public void setCodice(StringFilter codice) {
        this.codice = codice;
    }

    public StringFilter getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(StringFilter denominazione) {
        this.denominazione = denominazione;
    }

    public BooleanFilter getAbilitato() {
        return abilitato;
    }

    public void setAbilitato(BooleanFilter abilitato) {
        this.abilitato = abilitato;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProfessioneCriteria that = (ProfessioneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(codice, that.codice) &&
            Objects.equals(denominazione, that.denominazione) &&
            Objects.equals(abilitato, that.abilitato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        codice,
        denominazione,
        abilitato
        );
    }

    @Override
    public String toString() {
        return "ProfessioneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (codice != null ? "codice=" + codice + ", " : "") +
                (denominazione != null ? "denominazione=" + denominazione + ", " : "") +
                (abilitato != null ? "abilitato=" + abilitato + ", " : "") +
            "}";
    }

}
