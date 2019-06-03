package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;
import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.TipoMese;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the Cliente entity. This class is used in ClienteResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /clientes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ClienteCriteria implements Serializable {
    /**
     * Class for filtering BooleanStatus
     */
    public static class BooleanStatusFilter extends Filter<BooleanStatus> {
    }
    /**
     * Class for filtering TipoMese
     */
    public static class TipoMeseFilter extends Filter<TipoMese> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nome;

    private StringFilter cognome;

    private StringFilter codiceFiscale;

    private StringFilter titolo;

    private StringFilter ragioneSociale;

    private StringFilter agente;
    
    private BooleanStatusFilter alertCompleanno;

    private LocalDateFilter dataNascita;

    private TipoMeseFilter meseNascita;

    private StringFilter telefonoFisso;

    private StringFilter telefonoCellulare;

    private StringFilter email;

    private StringFilter indirizzo;

    private StringFilter cap;

    private StringFilter regione;

    private StringFilter provincia;

    private StringFilter citta;

    private StringFilter codiceAntiriciclaggio;

    private DoubleFilter importoProvvigioni;
    
    private DoubleFilter importoProvvigioniDerivate;

    private DoubleFilter numeroPratiche;

    private DoubleFilter numeroSegnalazioni;

    private DoubleFilter punteggio;

    private BooleanFilter abilitato;

    private LongFilter listaContattiId;

    private LongFilter tagId;

    private LongFilter incaricoId; //FIXME
    
    private LongFilter incaricoCommittenteId;

    private LongFilter incaricoProponenteId;

    private LongFilter incaricoAcquirenteLocatarioId;

    private LongFilter incaricoSegnalatoreId;

    private StringFilter freeSearch;
    
    private StringFilter currentUser;
    
    public ClienteCriteria() {
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
    
    public StringFilter getTitolo() {
        return titolo;
    }

    public void setTitolo(StringFilter titolo) {
        this.titolo = titolo;
    }

    public StringFilter getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(StringFilter ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public StringFilter getAgente() {
        return agente;
    }

    public void setAgente(StringFilter agente) {
        this.agente = agente;
    }


    public BooleanStatusFilter getAlertCompleanno() {
        return alertCompleanno;
    }

    public void setAlertCompleanno(BooleanStatusFilter alertCompleanno) {
        this.alertCompleanno = alertCompleanno;
    }

    public LocalDateFilter getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDateFilter dataNascita) {
        this.dataNascita = dataNascita;
    }

    public TipoMeseFilter getMeseNascita() {
        return meseNascita;
    }

    public void setMeseNascita(TipoMeseFilter meseNascita) {
        this.meseNascita = meseNascita;
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

    public StringFilter getCodiceAntiriciclaggio() {
        return codiceAntiriciclaggio;
    }

    public void setCodiceAntiriciclaggio(StringFilter codiceAntiriciclaggio) {
        this.codiceAntiriciclaggio = codiceAntiriciclaggio;
    }

    public DoubleFilter getImportoProvvigioni() {
        return importoProvvigioni;
    }

    public void setImportoProvvigioni(DoubleFilter importoProvvigioni) {
        this.importoProvvigioni = importoProvvigioni;
    }

    public DoubleFilter getImportoProvvigioniDerivate() {
        return importoProvvigioniDerivate;
    }

    public void setImportoProvvigioniDerivate(DoubleFilter importoProvvigioniDerivate) {
        this.importoProvvigioniDerivate = importoProvvigioniDerivate;
    }
    
    public DoubleFilter getNumeroPratiche() {
        return numeroPratiche;
    }

    public void setNumeroPratiche(DoubleFilter numeroPratiche) {
        this.numeroPratiche = numeroPratiche;
    }

    public DoubleFilter getNumeroSegnalazioni() {
        return numeroSegnalazioni;
    }

    public void setNumeroSegnalazioni(DoubleFilter numeroSegnalazioni) {
        this.numeroSegnalazioni = numeroSegnalazioni;
    }

    public DoubleFilter getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(DoubleFilter punteggio) {
        this.punteggio = punteggio;
    }

    public BooleanFilter getAbilitato() {
        return abilitato;
    }

    public void setAbilitato(BooleanFilter abilitato) {
        this.abilitato = abilitato;
    }

    public LongFilter getListaContattiId() {
        return listaContattiId;
    }

    public void setListaContattiId(LongFilter listaContattiId) {
        this.listaContattiId = listaContattiId;
    }

    public LongFilter getTagId() {
        return tagId;
    }

    public void setTagId(LongFilter tagId) {
        this.tagId = tagId;
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

	public LongFilter getIncaricoId() {
		return incaricoId;
	}
	
	public void setIncaricoId(LongFilter incaricoId) {
		this.incaricoId = incaricoId;
	}
	
    public LongFilter getIncaricoCommittenteId() {
        return incaricoCommittenteId;
    }

    public void setIncaricoCommittenteId(LongFilter incaricoCommittenteId) {
        this.incaricoCommittenteId = incaricoCommittenteId;
    }

    public LongFilter getIncaricoProponenteId() {
        return incaricoProponenteId;
    }

    public void setIncaricoProponenteId(LongFilter incaricoProponenteId) {
        this.incaricoProponenteId = incaricoProponenteId;
    }

    public LongFilter getIncaricoAcquirenteLocatarioId() {
        return incaricoAcquirenteLocatarioId;
    }

    public void setIncaricoAcquirenteLocatarioId(LongFilter incaricoAcquirenteLocatarioId) {
        this.incaricoAcquirenteLocatarioId = incaricoAcquirenteLocatarioId;
    }

    public LongFilter getIncaricoSegnalatoreId() {
        return incaricoSegnalatoreId;
    }

    public void setIncaricoSegnalatoreId(LongFilter incaricoSegnalatoreId) {
        this.incaricoSegnalatoreId = incaricoSegnalatoreId;
    }


	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClienteCriteria that = (ClienteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nome, that.nome) &&
            Objects.equals(cognome, that.cognome) &&
            Objects.equals(codiceFiscale, that.codiceFiscale) &&
            Objects.equals(titolo, that.titolo) &&
            Objects.equals(ragioneSociale, that.ragioneSociale) &&
            Objects.equals(agente, that.agente) &&
            Objects.equals(alertCompleanno, that.alertCompleanno) &&
            Objects.equals(dataNascita, that.dataNascita) &&
            Objects.equals(meseNascita, that.meseNascita) &&
            Objects.equals(telefonoFisso, that.telefonoFisso) &&
            Objects.equals(telefonoCellulare, that.telefonoCellulare) &&
            Objects.equals(email, that.email) &&
            Objects.equals(indirizzo, that.indirizzo) &&
            Objects.equals(cap, that.cap) &&
            Objects.equals(regione, that.regione) &&
            Objects.equals(provincia, that.provincia) &&
            Objects.equals(citta, that.citta) &&
            Objects.equals(codiceAntiriciclaggio, that.codiceAntiriciclaggio) &&
            Objects.equals(importoProvvigioni, that.importoProvvigioni) &&
            Objects.equals(numeroPratiche, that.numeroPratiche) &&
            Objects.equals(numeroSegnalazioni, that.numeroSegnalazioni) &&
            Objects.equals(punteggio, that.punteggio) &&
            Objects.equals(abilitato, that.abilitato) &&
            Objects.equals(listaContattiId, that.listaContattiId) &&
            Objects.equals(tagId, that.tagId) &&
            Objects.equals(incaricoId, that.incaricoId) &&
            Objects.equals(incaricoCommittenteId, that.incaricoCommittenteId) &&
            Objects.equals(incaricoProponenteId, that.incaricoProponenteId) &&
            Objects.equals(incaricoAcquirenteLocatarioId, that.incaricoAcquirenteLocatarioId) &&
            Objects.equals(incaricoSegnalatoreId, that.incaricoSegnalatoreId) &&
            Objects.equals(freeSearch, that.freeSearch) &&
        	Objects.equals(currentUser, that.currentUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nome,
        cognome,
        codiceFiscale,
        titolo,
        ragioneSociale,
        agente,
        alertCompleanno,
        dataNascita,
        meseNascita,
        telefonoFisso,
        telefonoCellulare,
        email,
        indirizzo,
        cap,
        regione,
        provincia,
        citta,
        codiceAntiriciclaggio,
        importoProvvigioni,
        numeroPratiche,
        numeroSegnalazioni,
        punteggio,
        abilitato,
        listaContattiId,
        tagId,
        incaricoId,
        incaricoCommittenteId,
        incaricoProponenteId,
        incaricoAcquirenteLocatarioId,
        incaricoSegnalatoreId,
        freeSearch,
        currentUser
        );
    }

    @Override
    public String toString() {
        return "ClienteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nome != null ? "nome=" + nome + ", " : "") +
                (cognome != null ? "cognome=" + cognome + ", " : "") +
                (codiceFiscale != null ? "codiceFiscale=" + codiceFiscale + ", " : "") +
                (titolo != null ? "titolo=" + titolo + ", " : "") +
                (ragioneSociale != null ? "ragioneSociale=" + ragioneSociale + ", " : "") +
                (agente != null ? "agente=" + agente + ", " : "") +
                (alertCompleanno != null ? "alertCompleanno=" + alertCompleanno + ", " : "") +
                (dataNascita != null ? "dataNascita=" + dataNascita + ", " : "") +
                (meseNascita != null ? "meseNascita=" + meseNascita + ", " : "") +
                (telefonoFisso != null ? "telefonoFisso=" + telefonoFisso + ", " : "") +
                (telefonoCellulare != null ? "telefonoCellulare=" + telefonoCellulare + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (indirizzo != null ? "indirizzo=" + indirizzo + ", " : "") +
                (cap != null ? "cap=" + cap + ", " : "") +
                (regione != null ? "regione=" + regione + ", " : "") +
                (provincia != null ? "provincia=" + provincia + ", " : "") +
                (citta != null ? "citta=" + citta + ", " : "") +
                (codiceAntiriciclaggio != null ? "codiceAntiriciclaggio=" + codiceAntiriciclaggio + ", " : "") +
                (importoProvvigioni != null ? "importoProvvigioni=" + importoProvvigioni + ", " : "") +
                (numeroPratiche != null ? "numeroPratiche=" + numeroPratiche + ", " : "") +
                (numeroSegnalazioni != null ? "numeroSegnalazioni=" + numeroSegnalazioni + ", " : "") +
                (punteggio != null ? "punteggio=" + punteggio + ", " : "") +
                (abilitato != null ? "abilitato=" + abilitato + ", " : "") +
                (listaContattiId != null ? "listaContattiId=" + listaContattiId + ", " : "") +
                (tagId != null ? "tagId=" + tagId + ", " : "") +
                (incaricoId != null ? "incaricoId=" + incaricoId + ", " : "") +
                (incaricoCommittenteId != null ? "incaricoCommittenteId=" + incaricoCommittenteId + ", " : "") +
                (incaricoProponenteId != null ? "incaricoProponenteId=" + incaricoProponenteId + ", " : "") +
                (incaricoAcquirenteLocatarioId != null ? "incaricoAcquirenteLocatarioId=" + incaricoAcquirenteLocatarioId + ", " : "") +
                (incaricoSegnalatoreId != null ? "incaricoSegnalatoreId=" + incaricoSegnalatoreId + ", " : "") +
                (freeSearch != null ? "freeSearch=" + freeSearch + ", " : "") +
                (currentUser != null ? "currentUser=" + currentUser + ", " : "") +
            "}";
    }

}
