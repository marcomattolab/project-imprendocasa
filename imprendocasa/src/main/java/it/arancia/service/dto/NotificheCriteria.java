package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;
import it.arancia.domain.enumeration.CanaleNotifica;
import it.arancia.domain.enumeration.TipoNotifica;
import it.arancia.domain.enumeration.CategoriaNotifica;
import it.arancia.domain.enumeration.TipoEsito;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the Notifiche entity. This class is used in NotificheResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /notifiches?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NotificheCriteria implements Serializable {
    /**
     * Class for filtering CanaleNotifica
     */
    public static class CanaleNotificaFilter extends Filter<CanaleNotifica> {
    }
    /**
     * Class for filtering TipoNotifica
     */
    public static class TipoNotificaFilter extends Filter<TipoNotifica> {
    }
    /**
     * Class for filtering CategoriaNotifica
     */
    public static class CategoriaNotificaFilter extends Filter<CategoriaNotifica> {
    }
    /**
     * Class for filtering TipoEsito
     */
    public static class TipoEsitoFilter extends Filter<TipoEsito> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private CanaleNotificaFilter canaleNotifica;

    private TipoNotificaFilter tipoNotifica;

    private CategoriaNotificaFilter categoriaNotifica;

    private StringFilter oggettoNotifica;

    private IntegerFilter numeroDestinatari;

    private TipoEsitoFilter esitoNotifica;

    public NotificheCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public CanaleNotificaFilter getCanaleNotifica() {
        return canaleNotifica;
    }

    public void setCanaleNotifica(CanaleNotificaFilter canaleNotifica) {
        this.canaleNotifica = canaleNotifica;
    }

    public TipoNotificaFilter getTipoNotifica() {
        return tipoNotifica;
    }

    public void setTipoNotifica(TipoNotificaFilter tipoNotifica) {
        this.tipoNotifica = tipoNotifica;
    }

    public CategoriaNotificaFilter getCategoriaNotifica() {
        return categoriaNotifica;
    }

    public void setCategoriaNotifica(CategoriaNotificaFilter categoriaNotifica) {
        this.categoriaNotifica = categoriaNotifica;
    }

    public StringFilter getOggettoNotifica() {
        return oggettoNotifica;
    }

    public void setOggettoNotifica(StringFilter oggettoNotifica) {
        this.oggettoNotifica = oggettoNotifica;
    }

    public IntegerFilter getNumeroDestinatari() {
        return numeroDestinatari;
    }

    public void setNumeroDestinatari(IntegerFilter numeroDestinatari) {
        this.numeroDestinatari = numeroDestinatari;
    }

    public TipoEsitoFilter getEsitoNotifica() {
        return esitoNotifica;
    }

    public void setEsitoNotifica(TipoEsitoFilter esitoNotifica) {
        this.esitoNotifica = esitoNotifica;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NotificheCriteria that = (NotificheCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(canaleNotifica, that.canaleNotifica) &&
            Objects.equals(tipoNotifica, that.tipoNotifica) &&
            Objects.equals(categoriaNotifica, that.categoriaNotifica) &&
            Objects.equals(oggettoNotifica, that.oggettoNotifica) &&
            Objects.equals(numeroDestinatari, that.numeroDestinatari) &&
            Objects.equals(esitoNotifica, that.esitoNotifica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        canaleNotifica,
        tipoNotifica,
        categoriaNotifica,
        oggettoNotifica,
        numeroDestinatari,
        esitoNotifica
        );
    }

    @Override
    public String toString() {
        return "NotificheCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (canaleNotifica != null ? "canaleNotifica=" + canaleNotifica + ", " : "") +
                (tipoNotifica != null ? "tipoNotifica=" + tipoNotifica + ", " : "") +
                (categoriaNotifica != null ? "categoriaNotifica=" + categoriaNotifica + ", " : "") +
                (oggettoNotifica != null ? "oggettoNotifica=" + oggettoNotifica + ", " : "") +
                (numeroDestinatari != null ? "numeroDestinatari=" + numeroDestinatari + ", " : "") +
                (esitoNotifica != null ? "esitoNotifica=" + esitoNotifica + ", " : "") +
            "}";
    }

}
