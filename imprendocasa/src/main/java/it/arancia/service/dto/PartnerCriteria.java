package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;
import it.arancia.domain.enumeration.TipoIndirizzo;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the Partner entity. This class is used in PartnerResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /partners?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PartnerCriteria implements Serializable {
    /**
     * Class for filtering TipoIndirizzo
     */
    public static class TipoIndirizzoFilter extends Filter<TipoIndirizzo> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nome;

    private StringFilter cognome;

    private StringFilter codiceFiscale;

    private StringFilter telefonoFisso;

    private StringFilter telefonoCellulare;

    private StringFilter email;

    private TipoIndirizzoFilter tipoIndirizzo;

    private StringFilter indirizzo;

    private StringFilter cap;

    private StringFilter regione;

    private StringFilter provincia;

    private StringFilter citta;

    private BooleanFilter abilitato;

    private LongFilter professioneId;

    private LongFilter incaricoId;

    private StringFilter freeSearch;
    
    public PartnerCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNome() {
        return nome;
    }

    public void setNome(StringFilter nome) {
        this.nome = nome;
    }

    public StringFilter getCognome() {
        return cognome;
    }

    public void setCognome(StringFilter cognome) {
        this.cognome = cognome;
    }

    public StringFilter getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(StringFilter codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public StringFilter getTelefonoFisso() {
        return telefonoFisso;
    }

    public void setTelefonoFisso(StringFilter telefonoFisso) {
        this.telefonoFisso = telefonoFisso;
    }

    public StringFilter getTelefonoCellulare() {
        return telefonoCellulare;
    }

    public void setTelefonoCellulare(StringFilter telefonoCellulare) {
        this.telefonoCellulare = telefonoCellulare;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public TipoIndirizzoFilter getTipoIndirizzo() {
        return tipoIndirizzo;
    }

    public void setTipoIndirizzo(TipoIndirizzoFilter tipoIndirizzo) {
        this.tipoIndirizzo = tipoIndirizzo;
    }

    public StringFilter getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(StringFilter indirizzo) {
        this.indirizzo = indirizzo;
    }

    public StringFilter getCap() {
        return cap;
    }

    public void setCap(StringFilter cap) {
        this.cap = cap;
    }

    public StringFilter getRegione() {
        return regione;
    }

    public void setRegione(StringFilter regione) {
        this.regione = regione;
    }

    public StringFilter getProvincia() {
        return provincia;
    }

    public void setProvincia(StringFilter provincia) {
        this.provincia = provincia;
    }

    public StringFilter getCitta() {
        return citta;
    }

    public void setCitta(StringFilter citta) {
        this.citta = citta;
    }

    public BooleanFilter getAbilitato() {
        return abilitato;
    }

    public void setAbilitato(BooleanFilter abilitato) {
        this.abilitato = abilitato;
    }

    public LongFilter getProfessioneId() {
        return professioneId;
    }

    public void setProfessioneId(LongFilter professioneId) {
        this.professioneId = professioneId;
    }

    public LongFilter getIncaricoId() {
        return incaricoId;
    }

    public void setIncaricoId(LongFilter incaricoId) {
        this.incaricoId = incaricoId;
    }

    public StringFilter getFreeSearch() {
		return freeSearch;
	}

	public void setFreeSearch(StringFilter freeSearch) {
		this.freeSearch = freeSearch;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PartnerCriteria that = (PartnerCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nome, that.nome) &&
            Objects.equals(cognome, that.cognome) &&
            Objects.equals(codiceFiscale, that.codiceFiscale) &&
            Objects.equals(telefonoFisso, that.telefonoFisso) &&
            Objects.equals(telefonoCellulare, that.telefonoCellulare) &&
            Objects.equals(email, that.email) &&
            Objects.equals(tipoIndirizzo, that.tipoIndirizzo) &&
            Objects.equals(indirizzo, that.indirizzo) &&
            Objects.equals(cap, that.cap) &&
            Objects.equals(regione, that.regione) &&
            Objects.equals(provincia, that.provincia) &&
            Objects.equals(citta, that.citta) &&
            Objects.equals(abilitato, that.abilitato) &&
            Objects.equals(professioneId, that.professioneId) &&
            Objects.equals(incaricoId, that.incaricoId) &&
            Objects.equals(freeSearch, that.freeSearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nome,
        cognome,
        codiceFiscale,
        telefonoFisso,
        telefonoCellulare,
        email,
        tipoIndirizzo,
        indirizzo,
        cap,
        regione,
        provincia,
        citta,
        abilitato,
        professioneId,
        incaricoId,
        freeSearch
        );
    }

    @Override
    public String toString() {
        return "PartnerCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nome != null ? "nome=" + nome + ", " : "") +
                (cognome != null ? "cognome=" + cognome + ", " : "") +
                (codiceFiscale != null ? "codiceFiscale=" + codiceFiscale + ", " : "") +
                (telefonoFisso != null ? "telefonoFisso=" + telefonoFisso + ", " : "") +
                (telefonoCellulare != null ? "telefonoCellulare=" + telefonoCellulare + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (tipoIndirizzo != null ? "tipoIndirizzo=" + tipoIndirizzo + ", " : "") +
                (indirizzo != null ? "indirizzo=" + indirizzo + ", " : "") +
                (cap != null ? "cap=" + cap + ", " : "") +
                (regione != null ? "regione=" + regione + ", " : "") +
                (provincia != null ? "provincia=" + provincia + ", " : "") +
                (citta != null ? "citta=" + citta + ", " : "") +
                (abilitato != null ? "abilitato=" + abilitato + ", " : "") +
                (professioneId != null ? "professioneId=" + professioneId + ", " : "") +
                (incaricoId != null ? "incaricoId=" + incaricoId + ", " : "") +
                (freeSearch != null ? "freeSearch=" + freeSearch + ", " : "") +
            "}";
    }

}
