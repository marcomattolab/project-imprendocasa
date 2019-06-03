package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;
import it.arancia.domain.enumeration.CategoriaNotifica;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the MailTemplate entity. This class is used in MailTemplateResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /mail-templates?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MailTemplateCriteria implements Serializable {
    /**
     * Class for filtering CategoriaNotifica
     */
    public static class CategoriaNotificaFilter extends Filter<CategoriaNotifica> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter codice;

    private CategoriaNotificaFilter categoria;

    private StringFilter oggetto;

    private BooleanFilter abilitato;

    public MailTemplateCriteria() {
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

    public CategoriaNotificaFilter getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaNotificaFilter categoria) {
        this.categoria = categoria;
    }

    public StringFilter getOggetto() {
        return oggetto;
    }

    public void setOggetto(StringFilter oggetto) {
        this.oggetto = oggetto;
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
        final MailTemplateCriteria that = (MailTemplateCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(codice, that.codice) &&
            Objects.equals(categoria, that.categoria) &&
            Objects.equals(oggetto, that.oggetto) &&
            Objects.equals(abilitato, that.abilitato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        codice,
        categoria,
        oggetto,
        abilitato
        );
    }

    @Override
    public String toString() {
        return "MailTemplateCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (codice != null ? "codice=" + codice + ", " : "") +
                (categoria != null ? "categoria=" + categoria + ", " : "") +
                (oggetto != null ? "oggetto=" + oggetto + ", " : "") +
                (abilitato != null ? "abilitato=" + abilitato + ", " : "") +
            "}";
    }

}
