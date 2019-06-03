package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.CategoriaIncarico;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.domain.enumeration.TipoNegoziazione;

/**
 * Criteria class for the Incarico entity. This class is used in IncaricoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /incaricos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class IncaricoCriteria implements Serializable {
    /**
     * Class for filtering TipoNegoziazione
     */
    public static class TipoNegoziazioneFilter extends Filter<TipoNegoziazione> {
    }
    /**
     * Class for filtering StatoIncarico
     */
    public static class StatoIncaricoFilter extends Filter<StatoIncarico> {
    }
    /**
     * Class for filtering BooleanStatus
     */
    public static class BooleanStatusFilter extends Filter<BooleanStatus> {
    }
    /**
     * Class for filtering CategoriaIncarico
     */
    public static class CategoriaIncaricoFilter extends Filter<CategoriaIncarico> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter riferimento;

    private TipoNegoziazioneFilter tipo;

    private CategoriaIncaricoFilter categoriaIncarico;

    private StatoIncaricoFilter stato;

    private LocalDateFilter dataScadenza;

    private StringFilter agente;

    private StringFilter agenteDiContatto;

    private LocalDateFilter dataContatto;

    private LocalDateFilter dataAlertFiscali;

    private BooleanStatusFilter alertFiscali;

    private StringFilter ricorrenzaAlertFiscali;

    private BooleanStatusFilter alertCortesia;

    private LocalDateFilter dataAlertCortesia;

    private StringFilter ricorrenzaAlertCortesia;

    private BooleanStatusFilter alertRicorrenza;

    private LocalDateFilter dataAlertRicorrenza;

    private StringFilter ricorrenzaAlertRicorrenza;

    private BooleanFilter privacy;

    private BooleanFilter antiriciclaggio;

    private BooleanFilter privacyAcquirenti;

    private BooleanFilter antiriciclaggioAcquirenti;

    private DoubleFilter prezzoRichiesta;

    private DoubleFilter prezzoMinimo;

    private DoubleFilter prezzoAcquisto;

    private DoubleFilter importoProvvigione;

    private DoubleFilter importoProvvigioneAcquirenti;

    private BooleanFilter oscuraIncarico;

    private BooleanFilter flagLocato;

    private StringFilter nomeConduttore;

    private StringFilter cognomeConduttore;

    private DoubleFilter canoneCorrisposto;

    private StringFilter telefonoConduttore;

    private LocalDateFilter dataInizioLocazione;

    private LocalDateFilter dataFineLocazione;

    private LongFilter listaContattiId;

    private LongFilter immobileId;

    private LongFilter partnerId;

    private LongFilter committenteId;

    private LongFilter proponenteId;

    private LongFilter acquirenteLocatarioId;

    private LongFilter segnalatoreId;
    
    private StringFilter currentUser;
    
    public IncaricoCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getRiferimento() {
        return riferimento;
    }

    public void setRiferimento(StringFilter riferimento) {
        this.riferimento = riferimento;
    }

    public TipoNegoziazioneFilter getTipo() {
        return tipo;
    }

    public void setTipo(TipoNegoziazioneFilter tipo) {
        this.tipo = tipo;
    }

    public CategoriaIncaricoFilter getCategoriaIncarico() {
        return categoriaIncarico;
    }

    public void setCategoriaIncarico(CategoriaIncaricoFilter categoriaIncarico) {
        this.categoriaIncarico = categoriaIncarico;
    }

    public StatoIncaricoFilter getStato() {
        return stato;
    }

    public void setStato(StatoIncaricoFilter stato) {
        this.stato = stato;
    }

    public LocalDateFilter getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDateFilter dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public StringFilter getAgente() {
        return agente;
    }

    public void setAgente(StringFilter agente) {
        this.agente = agente;
    }

    public StringFilter getAgenteDiContatto() {
        return agenteDiContatto;
    }

    public void setAgenteDiContatto(StringFilter agenteDiContatto) {
        this.agenteDiContatto = agenteDiContatto;
    }

    public LocalDateFilter getDataContatto() {
        return dataContatto;
    }

    public void setDataContatto(LocalDateFilter dataContatto) {
        this.dataContatto = dataContatto;
    }

    public LocalDateFilter getDataAlertFiscali() {
        return dataAlertFiscali;
    }

    public void setDataAlertFiscali(LocalDateFilter dataAlertFiscali) {
        this.dataAlertFiscali = dataAlertFiscali;
    }

    public BooleanStatusFilter getAlertFiscali() {
        return alertFiscali;
    }

    public void setAlertFiscali(BooleanStatusFilter alertFiscali) {
        this.alertFiscali = alertFiscali;
    }

    public StringFilter getRicorrenzaAlertFiscali() {
        return ricorrenzaAlertFiscali;
    }

    public void setRicorrenzaAlertFiscali(StringFilter ricorrenzaAlertFiscali) {
        this.ricorrenzaAlertFiscali = ricorrenzaAlertFiscali;
    }

    public BooleanStatusFilter getAlertCortesia() {
        return alertCortesia;
    }

    public void setAlertCortesia(BooleanStatusFilter alertCortesia) {
        this.alertCortesia = alertCortesia;
    }

    public LocalDateFilter getDataAlertCortesia() {
        return dataAlertCortesia;
    }

    public void setDataAlertCortesia(LocalDateFilter dataAlertCortesia) {
        this.dataAlertCortesia = dataAlertCortesia;
    }

    public StringFilter getRicorrenzaAlertCortesia() {
        return ricorrenzaAlertCortesia;
    }

    public void setRicorrenzaAlertCortesia(StringFilter ricorrenzaAlertCortesia) {
        this.ricorrenzaAlertCortesia = ricorrenzaAlertCortesia;
    }

    public BooleanStatusFilter getAlertRicorrenza() {
        return alertRicorrenza;
    }

    public void setAlertRicorrenza(BooleanStatusFilter alertRicorrenza) {
        this.alertRicorrenza = alertRicorrenza;
    }

    public LocalDateFilter getDataAlertRicorrenza() {
        return dataAlertRicorrenza;
    }

    public void setDataAlertRicorrenza(LocalDateFilter dataAlertRicorrenza) {
        this.dataAlertRicorrenza = dataAlertRicorrenza;
    }

    public StringFilter getRicorrenzaAlertRicorrenza() {
        return ricorrenzaAlertRicorrenza;
    }

    public void setRicorrenzaAlertRicorrenza(StringFilter ricorrenzaAlertRicorrenza) {
        this.ricorrenzaAlertRicorrenza = ricorrenzaAlertRicorrenza;
    }

    public BooleanFilter getPrivacy() {
        return privacy;
    }

    public void setPrivacy(BooleanFilter privacy) {
        this.privacy = privacy;
    }

    public BooleanFilter getAntiriciclaggio() {
        return antiriciclaggio;
    }

    public void setAntiriciclaggio(BooleanFilter antiriciclaggio) {
        this.antiriciclaggio = antiriciclaggio;
    }

    public BooleanFilter getPrivacyAcquirenti() {
        return privacyAcquirenti;
    }

    public void setPrivacyAcquirenti(BooleanFilter privacyAcquirenti) {
        this.privacyAcquirenti = privacyAcquirenti;
    }

    public BooleanFilter getAntiriciclaggioAcquirenti() {
        return antiriciclaggioAcquirenti;
    }

    public void setAntiriciclaggioAcquirenti(BooleanFilter antiriciclaggioAcquirenti) {
        this.antiriciclaggioAcquirenti = antiriciclaggioAcquirenti;
    }

    public DoubleFilter getPrezzoRichiesta() {
        return prezzoRichiesta;
    }

    public void setPrezzoRichiesta(DoubleFilter prezzoRichiesta) {
        this.prezzoRichiesta = prezzoRichiesta;
    }

    public DoubleFilter getPrezzoMinimo() {
        return prezzoMinimo;
    }

    public void setPrezzoMinimo(DoubleFilter prezzoMinimo) {
        this.prezzoMinimo = prezzoMinimo;
    }

    public DoubleFilter getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public void setPrezzoAcquisto(DoubleFilter prezzoAcquisto) {
        this.prezzoAcquisto = prezzoAcquisto;
    }

    public DoubleFilter getImportoProvvigione() {
        return importoProvvigione;
    }

    public void setImportoProvvigione(DoubleFilter importoProvvigione) {
        this.importoProvvigione = importoProvvigione;
    }

    public DoubleFilter getImportoProvvigioneAcquirenti() {
        return importoProvvigioneAcquirenti;
    }

    public void setImportoProvvigioneAcquirenti(DoubleFilter importoProvvigioneAcquirenti) {
        this.importoProvvigioneAcquirenti = importoProvvigioneAcquirenti;
    }

    public BooleanFilter getOscuraIncarico() {
        return oscuraIncarico;
    }

    public void setOscuraIncarico(BooleanFilter oscuraIncarico) {
        this.oscuraIncarico = oscuraIncarico;
    }

    public BooleanFilter getFlagLocato() {
        return flagLocato;
    }

    public void setFlagLocato(BooleanFilter flagLocato) {
        this.flagLocato = flagLocato;
    }

    public StringFilter getNomeConduttore() {
        return nomeConduttore;
    }

    public void setNomeConduttore(StringFilter nomeConduttore) {
        this.nomeConduttore = nomeConduttore;
    }

    public StringFilter getCognomeConduttore() {
        return cognomeConduttore;
    }

    public void setCognomeConduttore(StringFilter cognomeConduttore) {
        this.cognomeConduttore = cognomeConduttore;
    }

    public DoubleFilter getCanoneCorrisposto() {
        return canoneCorrisposto;
    }

    public void setCanoneCorrisposto(DoubleFilter canoneCorrisposto) {
        this.canoneCorrisposto = canoneCorrisposto;
    }

    public StringFilter getTelefonoConduttore() {
        return telefonoConduttore;
    }

    public void setTelefonoConduttore(StringFilter telefonoConduttore) {
        this.telefonoConduttore = telefonoConduttore;
    }

    public LocalDateFilter getDataInizioLocazione() {
        return dataInizioLocazione;
    }

    public void setDataInizioLocazione(LocalDateFilter dataInizioLocazione) {
        this.dataInizioLocazione = dataInizioLocazione;
    }

    public LocalDateFilter getDataFineLocazione() {
        return dataFineLocazione;
    }

    public void setDataFineLocazione(LocalDateFilter dataFineLocazione) {
        this.dataFineLocazione = dataFineLocazione;
    }

    public LongFilter getListaContattiId() {
        return listaContattiId;
    }

    public void setListaContattiId(LongFilter listaContattiId) {
        this.listaContattiId = listaContattiId;
    }

    public LongFilter getImmobileId() {
        return immobileId;
    }

    public void setImmobileId(LongFilter immobileId) {
        this.immobileId = immobileId;
    }

    public LongFilter getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(LongFilter partnerId) {
        this.partnerId = partnerId;
    }

    public LongFilter getCommittenteId() {
        return committenteId;
    }

    public void setCommittenteId(LongFilter committenteId) {
        this.committenteId = committenteId;
    }

    public LongFilter getProponenteId() {
        return proponenteId;
    }

    public void setProponenteId(LongFilter proponenteId) {
        this.proponenteId = proponenteId;
    }

    public LongFilter getAcquirenteLocatarioId() {
        return acquirenteLocatarioId;
    }

    public void setAcquirenteLocatarioId(LongFilter acquirenteLocatarioId) {
        this.acquirenteLocatarioId = acquirenteLocatarioId;
    }

    public LongFilter getSegnalatoreId() {
        return segnalatoreId;
    }

    public void setSegnalatoreId(LongFilter segnalatoreId) {
        this.segnalatoreId = segnalatoreId;
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
        final IncaricoCriteria that = (IncaricoCriteria) o;
        return
			 Objects.equals(id, that.id) &&
	         Objects.equals(riferimento, that.riferimento) &&
	         Objects.equals(tipo, that.tipo) &&
	         Objects.equals(categoriaIncarico, that.categoriaIncarico) &&
	         Objects.equals(stato, that.stato) &&
	         Objects.equals(dataScadenza, that.dataScadenza) &&
	         Objects.equals(agente, that.agente) &&
	         Objects.equals(agenteDiContatto, that.agenteDiContatto) &&
	         Objects.equals(dataContatto, that.dataContatto) &&
	         Objects.equals(dataAlertFiscali, that.dataAlertFiscali) &&
	         Objects.equals(alertFiscali, that.alertFiscali) &&
	         Objects.equals(ricorrenzaAlertFiscali, that.ricorrenzaAlertFiscali) &&
	         Objects.equals(alertCortesia, that.alertCortesia) &&
	         Objects.equals(dataAlertCortesia, that.dataAlertCortesia) &&
	         Objects.equals(ricorrenzaAlertCortesia, that.ricorrenzaAlertCortesia) &&
	         Objects.equals(alertRicorrenza, that.alertRicorrenza) &&
	         Objects.equals(dataAlertRicorrenza, that.dataAlertRicorrenza) &&
	         Objects.equals(ricorrenzaAlertRicorrenza, that.ricorrenzaAlertRicorrenza) &&
	         Objects.equals(privacy, that.privacy) &&
	         Objects.equals(antiriciclaggio, that.antiriciclaggio) &&
	         Objects.equals(privacyAcquirenti, that.privacyAcquirenti) &&
	         Objects.equals(antiriciclaggioAcquirenti, that.antiriciclaggioAcquirenti) &&
	         Objects.equals(prezzoRichiesta, that.prezzoRichiesta) &&
	         Objects.equals(prezzoMinimo, that.prezzoMinimo) &&
	         Objects.equals(prezzoAcquisto, that.prezzoAcquisto) &&
	         Objects.equals(importoProvvigione, that.importoProvvigione) &&
	         Objects.equals(importoProvvigioneAcquirenti, that.importoProvvigioneAcquirenti) &&
	         Objects.equals(oscuraIncarico, that.oscuraIncarico) &&
	         Objects.equals(flagLocato, that.flagLocato) &&
	         Objects.equals(nomeConduttore, that.nomeConduttore) &&
	         Objects.equals(cognomeConduttore, that.cognomeConduttore) &&
	         Objects.equals(canoneCorrisposto, that.canoneCorrisposto) &&
	         Objects.equals(telefonoConduttore, that.telefonoConduttore) &&
	         Objects.equals(dataInizioLocazione, that.dataInizioLocazione) &&
	         Objects.equals(dataFineLocazione, that.dataFineLocazione) &&
	         Objects.equals(listaContattiId, that.listaContattiId) &&
	         Objects.equals(immobileId, that.immobileId) &&
	         Objects.equals(partnerId, that.partnerId) &&
	         Objects.equals(committenteId, that.committenteId) &&
	         Objects.equals(proponenteId, that.proponenteId) &&
	         Objects.equals(acquirenteLocatarioId, that.acquirenteLocatarioId) &&
	         Objects.equals(segnalatoreId, that.segnalatoreId) &&
             Objects.equals(currentUser, that.currentUser) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
    		id,
	        riferimento,
	        tipo,
	        categoriaIncarico,
	        stato,
	        dataScadenza,
	        agente,
	        agenteDiContatto,
	        dataContatto,
	        dataAlertFiscali,
	        alertFiscali,
	        ricorrenzaAlertFiscali,
	        alertCortesia,
	        dataAlertCortesia,
	        ricorrenzaAlertCortesia,
	        alertRicorrenza,
	        dataAlertRicorrenza,
	        ricorrenzaAlertRicorrenza,
	        privacy,
	        antiriciclaggio,
	        privacyAcquirenti,
	        antiriciclaggioAcquirenti,
	        prezzoRichiesta,
	        prezzoMinimo,
	        prezzoAcquisto,
	        importoProvvigione,
	        importoProvvigioneAcquirenti,
	        oscuraIncarico,
	        flagLocato,
	        nomeConduttore,
	        cognomeConduttore,
	        canoneCorrisposto,
	        telefonoConduttore,
	        dataInizioLocazione,
	        dataFineLocazione,
	        listaContattiId,
	        immobileId,
	        partnerId,
	        committenteId,
	        proponenteId,
	        acquirenteLocatarioId,
	        segnalatoreId,
	        currentUser
        );
    }

    @Override
    public String toString() {
        return "IncaricoCriteria{" +
        		(id != null ? "id=" + id + ", " : "") +
                (riferimento != null ? "riferimento=" + riferimento + ", " : "") +
                (tipo != null ? "tipo=" + tipo + ", " : "") +
                (categoriaIncarico != null ? "categoriaIncarico=" + categoriaIncarico + ", " : "") +
                (stato != null ? "stato=" + stato + ", " : "") +
                (dataScadenza != null ? "dataScadenza=" + dataScadenza + ", " : "") +
                (agente != null ? "agente=" + agente + ", " : "") +
                (agenteDiContatto != null ? "agenteDiContatto=" + agenteDiContatto + ", " : "") +
                (dataContatto != null ? "dataContatto=" + dataContatto + ", " : "") +
                (dataAlertFiscali != null ? "dataAlertFiscali=" + dataAlertFiscali + ", " : "") +
                (alertFiscali != null ? "alertFiscali=" + alertFiscali + ", " : "") +
                (ricorrenzaAlertFiscali != null ? "ricorrenzaAlertFiscali=" + ricorrenzaAlertFiscali + ", " : "") +
                (alertCortesia != null ? "alertCortesia=" + alertCortesia + ", " : "") +
                (dataAlertCortesia != null ? "dataAlertCortesia=" + dataAlertCortesia + ", " : "") +
                (ricorrenzaAlertCortesia != null ? "ricorrenzaAlertCortesia=" + ricorrenzaAlertCortesia + ", " : "") +
                (alertRicorrenza != null ? "alertRicorrenza=" + alertRicorrenza + ", " : "") +
                (dataAlertRicorrenza != null ? "dataAlertRicorrenza=" + dataAlertRicorrenza + ", " : "") +
                (ricorrenzaAlertRicorrenza != null ? "ricorrenzaAlertRicorrenza=" + ricorrenzaAlertRicorrenza + ", " : "") +
                (privacy != null ? "privacy=" + privacy + ", " : "") +
                (antiriciclaggio != null ? "antiriciclaggio=" + antiriciclaggio + ", " : "") +
                (privacyAcquirenti != null ? "privacyAcquirenti=" + privacyAcquirenti + ", " : "") +
                (antiriciclaggioAcquirenti != null ? "antiriciclaggioAcquirenti=" + antiriciclaggioAcquirenti + ", " : "") +
                (prezzoRichiesta != null ? "prezzoRichiesta=" + prezzoRichiesta + ", " : "") +
                (prezzoMinimo != null ? "prezzoMinimo=" + prezzoMinimo + ", " : "") +
                (prezzoAcquisto != null ? "prezzoAcquisto=" + prezzoAcquisto + ", " : "") +
                (importoProvvigione != null ? "importoProvvigione=" + importoProvvigione + ", " : "") +
                (importoProvvigioneAcquirenti != null ? "importoProvvigioneAcquirenti=" + importoProvvigioneAcquirenti + ", " : "") +
                (oscuraIncarico != null ? "oscuraIncarico=" + oscuraIncarico + ", " : "") +
                (flagLocato != null ? "flagLocato=" + flagLocato + ", " : "") +
                (nomeConduttore != null ? "nomeConduttore=" + nomeConduttore + ", " : "") +
                (cognomeConduttore != null ? "cognomeConduttore=" + cognomeConduttore + ", " : "") +
                (canoneCorrisposto != null ? "canoneCorrisposto=" + canoneCorrisposto + ", " : "") +
                (telefonoConduttore != null ? "telefonoConduttore=" + telefonoConduttore + ", " : "") +
                (dataInizioLocazione != null ? "dataInizioLocazione=" + dataInizioLocazione + ", " : "") +
                (dataFineLocazione != null ? "dataFineLocazione=" + dataFineLocazione + ", " : "") +
                (listaContattiId != null ? "listaContattiId=" + listaContattiId + ", " : "") +
                (immobileId != null ? "immobileId=" + immobileId + ", " : "") +
                (partnerId != null ? "partnerId=" + partnerId + ", " : "") +
                (committenteId != null ? "committenteId=" + committenteId + ", " : "") +
                (proponenteId != null ? "proponenteId=" + proponenteId + ", " : "") +
                (acquirenteLocatarioId != null ? "acquirenteLocatarioId=" + acquirenteLocatarioId + ", " : "") +
                (segnalatoreId != null ? "segnalatoreId=" + segnalatoreId + ", " : "") +
                (currentUser != null ? "currentUser=" + currentUser + ", " : "") +
            "}";
    }

}
