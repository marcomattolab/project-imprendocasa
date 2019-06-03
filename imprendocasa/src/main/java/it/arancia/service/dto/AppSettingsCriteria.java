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
 * Criteria class for the AppSettings entity. This class is used in AppSettingsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /app-settings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class AppSettingsCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter categoria;

    private StringFilter chiave;

    private StringFilter valore;

    private BooleanFilter abilitato;

    public AppSettingsCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCategoria() {
        return categoria;
    }

    public void setCategoria(StringFilter categoria) {
        this.categoria = categoria;
    }

    public StringFilter getChiave() {
        return chiave;
    }

    public void setChiave(StringFilter chiave) {
        this.chiave = chiave;
    }

    public StringFilter getValore() {
        return valore;
    }

    public void setValore(StringFilter valore) {
        this.valore = valore;
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
        final AppSettingsCriteria that = (AppSettingsCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(categoria, that.categoria) &&
            Objects.equals(chiave, that.chiave) &&
            Objects.equals(valore, that.valore) &&
            Objects.equals(abilitato, that.abilitato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        categoria,
        chiave,
        valore,
        abilitato
        );
    }

    @Override
    public String toString() {
        return "AppSettingsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (categoria != null ? "categoria=" + categoria + ", " : "") +
                (chiave != null ? "chiave=" + chiave + ", " : "") +
                (valore != null ? "valore=" + valore + ", " : "") +
                (abilitato != null ? "abilitato=" + abilitato + ", " : "") +
            "}";
    }

}
