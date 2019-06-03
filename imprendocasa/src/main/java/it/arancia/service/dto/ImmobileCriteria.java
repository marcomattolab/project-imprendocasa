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
 * Criteria class for the Immobile entity. This class is used in ImmobileResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /immobiles?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ImmobileCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter agente;
    
    private StringFilter codice;

    private StringFilter indirizzo;

    private StringFilter cap;

    private StringFilter regione;

    private StringFilter provincia;

    private StringFilter citta;

    private StringFilter pathFolder;

    private StringFilter datiCatastali;

    private StringFilter foglio;

    private StringFilter particella;

    private StringFilter sub;
    
    private LongFilter geolocalizzazioneId;

    private LongFilter filesId;

    private LongFilter incaricoId;

    private StringFilter freeSearch;
    
    private StringFilter currentUser;
    
    public ImmobileCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }
    
    public StringFilter getAgente() {
        return agente;
    }

    public void setAgente(StringFilter agente) {
        this.agente = agente;
    }

    public StringFilter getCodice() {
        return codice;
    }

    public void setCodice(StringFilter codice) {
        this.codice = codice;
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

    public StringFilter getPathFolder() {
        return pathFolder;
    }

    public void setPathFolder(StringFilter pathFolder) {
        this.pathFolder = pathFolder;
    }

    public StringFilter getDatiCatastali() {
        return datiCatastali;
    }

    public void setDatiCatastali(StringFilter datiCatastali) {
        this.datiCatastali = datiCatastali;
    }

    public StringFilter getFoglio() {
        return foglio;
    }

    public void setFoglio(StringFilter foglio) {
        this.foglio = foglio;
    }

    public StringFilter getParticella() {
        return particella;
    }

    public void setParticella(StringFilter particella) {
        this.particella = particella;
    }

    public StringFilter getSub() {
        return sub;
    }

    public void setSub(StringFilter sub) {
        this.sub = sub;
    }
    
    public LongFilter getGeolocalizzazioneId() {
        return geolocalizzazioneId;
    }

    public void setGeolocalizzazioneId(LongFilter geolocalizzazioneId) {
        this.geolocalizzazioneId = geolocalizzazioneId;
    }

    public LongFilter getFilesId() {
        return filesId;
    }

    public void setFilesId(LongFilter filesId) {
        this.filesId = filesId;
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
	
    public StringFilter getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(StringFilter currentUser) {
		this.currentUser = currentUser;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ImmobileCriteria that = (ImmobileCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(agente, that.agente) &&
            Objects.equals(codice, that.codice) &&
            Objects.equals(indirizzo, that.indirizzo) &&
            Objects.equals(cap, that.cap) &&
            Objects.equals(regione, that.regione) &&
            Objects.equals(provincia, that.provincia) &&
            Objects.equals(citta, that.citta) &&
            Objects.equals(pathFolder, that.pathFolder) &&
            Objects.equals(datiCatastali, that.datiCatastali) &&
            Objects.equals(foglio, that.foglio) &&
            Objects.equals(particella, that.particella) &&
            Objects.equals(sub, that.sub) &&
            Objects.equals(geolocalizzazioneId, that.geolocalizzazioneId) &&
            Objects.equals(filesId, that.filesId) &&
            Objects.equals(incaricoId, that.incaricoId) &&
            Objects.equals(freeSearch, that.freeSearch) &&
            Objects.equals(currentUser, that.currentUser) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        agente,
        codice,
        indirizzo,
        cap,
        regione,
        provincia,
        citta,
        pathFolder,
        datiCatastali,
        foglio,
        particella,
        sub,
        geolocalizzazioneId,
        filesId,
        incaricoId,
        freeSearch,
        currentUser
        );
    }

    @Override
    public String toString() {
        return "ImmobileCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (agente != null ? "agente=" + agente + ", " : "") +
                (codice != null ? "codice=" + codice + ", " : "") +
                (indirizzo != null ? "indirizzo=" + indirizzo + ", " : "") +
                (cap != null ? "cap=" + cap + ", " : "") +
                (regione != null ? "regione=" + regione + ", " : "") +
                (provincia != null ? "provincia=" + provincia + ", " : "") +
                (citta != null ? "citta=" + citta + ", " : "") +
                (pathFolder != null ? "pathFolder=" + pathFolder + ", " : "") +
                (datiCatastali != null ? "datiCatastali=" + datiCatastali + ", " : "") +
                (foglio != null ? "foglio=" + foglio + ", " : "") +
                (particella != null ? "particella=" + particella + ", " : "") +
                (sub != null ? "sub=" + sub + ", " : "") +
                (geolocalizzazioneId != null ? "geolocalizzazioneId=" + geolocalizzazioneId + ", " : "") +
                (filesId != null ? "filesId=" + filesId + ", " : "") +
                (incaricoId != null ? "incaricoId=" + incaricoId + ", " : "") +
                (freeSearch != null ? "freeSearch=" + freeSearch + ", " : "") +
                (currentUser != null ? "currentUser=" + currentUser + ", " : "") +
            "}";
    }

}
