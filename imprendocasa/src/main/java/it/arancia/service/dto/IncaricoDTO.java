package it.arancia.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import it.arancia.domain.enumeration.BooleanStatus;
import it.arancia.domain.enumeration.CategoriaIncarico;
import it.arancia.domain.enumeration.StatoIncarico;
import it.arancia.domain.enumeration.TipoNegoziazione;

/**
 * A DTO for the Incarico entity.
 */
public class IncaricoDTO extends AbstractAuditingDTO implements EditableDTO {

    private Long id;

//    @NotNull
    private String riferimento;

    @NotNull
    private TipoNegoziazione tipo;

    private StatoIncarico stato;

    private CategoriaIncarico categoriaIncarico;
    
    private LocalDate dataScadenza;

    private String agente;

    private String agenteDiContatto;

    private LocalDate dataContatto;

    @Lob
    private String noteTrattativa;

    @Lob
    private String datiFiscali;

    private LocalDate dataAlertFiscali;

    private BooleanStatus alertFiscali;

    private String ricorrenzaAlertFiscali;

    private BooleanStatus alertCortesia;

    private LocalDate dataAlertCortesia;

    private String ricorrenzaAlertCortesia;

    private BooleanStatus alertRicorrenza;

    private LocalDate dataAlertRicorrenza;

    private String ricorrenzaAlertRicorrenza;

    private Boolean privacy;

    private Boolean antiriciclaggio;
    
    private Boolean privacyAcquirenti;

    private Boolean antiriciclaggioAcquirenti;

    private Double prezzoRichiesta;

    private Double prezzoMinimo;

    private Double prezzoAcquisto;

    private Double importoProvvigione;
    
    private Double importoProvvigioneAcquirenti;

    private Boolean oscuraIncarico;

    private Boolean flagLocato;

    private String nomeConduttore;

    private String cognomeConduttore;

    private Double canoneCorrisposto;

    private String telefonoConduttore;

    private LocalDate dataInizioLocazione;

    private LocalDate dataFineLocazione;

    @Lob
    private String noteLocazione;

    private Long immobileId;

    private String immobileCodice;

    private Set<PartnerDTO> partners = new HashSet<>();

    private Set<ClienteDTO> committentes = new HashSet<>();

    private Set<ClienteDTO> proponentes = new HashSet<>();

    private Set<ClienteDTO> acquirenteLocatarios = new HashSet<>();

    private Set<ClienteDTO> segnalatores = new HashSet<>();
    
    private Boolean editAvaiable;

    private Boolean deleteAvaiable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiferimento() {
        return riferimento;
    }

    public void setRiferimento(String riferimento) {
        this.riferimento = riferimento;
    }

    public TipoNegoziazione getTipo() {
        return tipo;
    }

    public void setTipo(TipoNegoziazione tipo) {
        this.tipo = tipo;
    }

    public StatoIncarico getStato() {
        return stato;
    }

    public void setStato(StatoIncarico stato) {
        this.stato = stato;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getAgenteDiContatto() {
        return agenteDiContatto;
    }

    public void setAgenteDiContatto(String agenteDiContatto) {
        this.agenteDiContatto = agenteDiContatto;
    }

    public LocalDate getDataContatto() {
        return dataContatto;
    }

    public void setDataContatto(LocalDate dataContatto) {
        this.dataContatto = dataContatto;
    }

    public String getNoteTrattativa() {
        return noteTrattativa;
    }

    public void setNoteTrattativa(String noteTrattativa) {
        this.noteTrattativa = noteTrattativa;
    }

    public String getDatiFiscali() {
        return datiFiscali;
    }

    public void setDatiFiscali(String datiFiscali) {
        this.datiFiscali = datiFiscali;
    }

    public BooleanStatus getAlertFiscali() {
        return alertFiscali;
    }

    public void setAlertFiscali(BooleanStatus alertFiscali) {
        this.alertFiscali = alertFiscali;
    }

    public BooleanStatus getAlertCortesia() {
        return alertCortesia;
    }

    public void setAlertCortesia(BooleanStatus alertCortesia) {
        this.alertCortesia = alertCortesia;
    }

    public Boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public Boolean isAntiriciclaggio() {
        return antiriciclaggio;
    }

    public void setAntiriciclaggio(Boolean antiriciclaggio) {
        this.antiriciclaggio = antiriciclaggio;
    }

    public Double getPrezzoRichiesta() {
        return prezzoRichiesta;
    }

    public void setPrezzoRichiesta(Double prezzoRichiesta) {
        this.prezzoRichiesta = prezzoRichiesta;
    }

    public Double getPrezzoMinimo() {
        return prezzoMinimo;
    }

    public void setPrezzoMinimo(Double prezzoMinimo) {
        this.prezzoMinimo = prezzoMinimo;
    }

    public Double getPrezzoAcquisto() {
        return prezzoAcquisto;
    }

    public void setPrezzoAcquisto(Double prezzoAcquisto) {
        this.prezzoAcquisto = prezzoAcquisto;
    }

    public Double getImportoProvvigione() {
        return importoProvvigione;
    }

    public void setImportoProvvigione(Double importoProvvigione) {
        this.importoProvvigione = importoProvvigione;
    }

    public Boolean isOscuraIncarico() {
        return oscuraIncarico;
    }

    public void setOscuraIncarico(Boolean oscuraIncarico) {
        this.oscuraIncarico = oscuraIncarico;
    }

    public Boolean isFlagLocato() {
        return flagLocato;
    }

    public void setFlagLocato(Boolean flagLocato) {
        this.flagLocato = flagLocato;
    }

    public String getNomeConduttore() {
        return nomeConduttore;
    }

    public void setNomeConduttore(String nomeConduttore) {
        this.nomeConduttore = nomeConduttore;
    }

    public String getCognomeConduttore() {
        return cognomeConduttore;
    }

    public void setCognomeConduttore(String cognomeConduttore) {
        this.cognomeConduttore = cognomeConduttore;
    }

    public LocalDate getDataInizioLocazione() {
        return dataInizioLocazione;
    }

    public void setDataInizioLocazione(LocalDate dataInizioLocazione) {
        this.dataInizioLocazione = dataInizioLocazione;
    }

    public LocalDate getDataFineLocazione() {
        return dataFineLocazione;
    }

    public void setDataFineLocazione(LocalDate dataFineLocazione) {
        this.dataFineLocazione = dataFineLocazione;
    }

    public String getNoteLocazione() {
        return noteLocazione;
    }

    public void setNoteLocazione(String noteLocazione) {
        this.noteLocazione = noteLocazione;
    }

    public Long getImmobileId() {
        return immobileId;
    }

    public void setImmobileId(Long immobileId) {
        this.immobileId = immobileId;
    }

    public String getImmobileCodice() {
        return immobileCodice;
    }

    public void setImmobileCodice(String immobileCodice) {
        this.immobileCodice = immobileCodice;
    }

    public Set<PartnerDTO> getPartners() {
        return partners;
    }

    public void setPartners(Set<PartnerDTO> partners) {
        this.partners = partners;
    }

    public Set<ClienteDTO> getCommittentes() {
        return committentes;
    }

    public void setCommittentes(Set<ClienteDTO> clientes) {
        this.committentes = clientes;
    }

    public Set<ClienteDTO> getProponentes() {
        return proponentes;
    }

    public void setProponentes(Set<ClienteDTO> clientes) {
        this.proponentes = clientes;
    }

    public Set<ClienteDTO> getAcquirenteLocatarios() {
        return acquirenteLocatarios;
    }

    public void setAcquirenteLocatarios(Set<ClienteDTO> clientes) {
        this.acquirenteLocatarios = clientes;
    }

    public Set<ClienteDTO> getSegnalatores() {
        return segnalatores;
    }

    public void setSegnalatores(Set<ClienteDTO> clientes) {
        this.segnalatores = clientes;
    }

    public CategoriaIncarico getCategoriaIncarico() {
		return categoriaIncarico;
	}

	public void setCategoriaIncarico(CategoriaIncarico categoriaIncarico) {
		this.categoriaIncarico = categoriaIncarico;
	}

	public LocalDate getDataAlertFiscali() {
		return dataAlertFiscali;
	}

	public void setDataAlertFiscali(LocalDate dataAlertFiscali) {
		this.dataAlertFiscali = dataAlertFiscali;
	}

	public String getRicorrenzaAlertFiscali() {
		return ricorrenzaAlertFiscali;
	}

	public void setRicorrenzaAlertFiscali(String ricorrenzaAlertFiscali) {
		this.ricorrenzaAlertFiscali = ricorrenzaAlertFiscali;
	}

	public LocalDate getDataAlertCortesia() {
		return dataAlertCortesia;
	}

	public void setDataAlertCortesia(LocalDate dataAlertCortesia) {
		this.dataAlertCortesia = dataAlertCortesia;
	}

	public String getRicorrenzaAlertCortesia() {
		return ricorrenzaAlertCortesia;
	}

	public void setRicorrenzaAlertCortesia(String ricorrenzaAlertCortesia) {
		this.ricorrenzaAlertCortesia = ricorrenzaAlertCortesia;
	}

	public BooleanStatus getAlertRicorrenza() {
		return alertRicorrenza;
	}

	public void setAlertRicorrenza(BooleanStatus alertRicorrenza) {
		this.alertRicorrenza = alertRicorrenza;
	}

	public LocalDate getDataAlertRicorrenza() {
		return dataAlertRicorrenza;
	}

	public void setDataAlertRicorrenza(LocalDate dataAlertRicorrenza) {
		this.dataAlertRicorrenza = dataAlertRicorrenza;
	}

	public String getRicorrenzaAlertRicorrenza() {
		return ricorrenzaAlertRicorrenza;
	}

	public void setRicorrenzaAlertRicorrenza(String ricorrenzaAlertRicorrenza) {
		this.ricorrenzaAlertRicorrenza = ricorrenzaAlertRicorrenza;
	}

	public Boolean getPrivacyAcquirenti() {
		return privacyAcquirenti;
	}

    public Boolean isPrivacyAcquirenti() {
        return privacyAcquirenti;
    }

    public Boolean isAntiriciclaggioAcquirenti() {
        return antiriciclaggioAcquirenti;
    }

	public void setAntiriciclaggioAcquirenti(Boolean antiriciclaggioAcquirenti) {
		this.antiriciclaggioAcquirenti = antiriciclaggioAcquirenti;
	}

	public Double getImportoProvvigioneAcquirenti() {
		return importoProvvigioneAcquirenti;
	}

	public void setImportoProvvigioneAcquirenti(Double importoProvvigioneAcquirenti) {
		this.importoProvvigioneAcquirenti = importoProvvigioneAcquirenti;
	}

	public Double getCanoneCorrisposto() {
		return canoneCorrisposto;
	}

	public void setCanoneCorrisposto(Double canoneCorrisposto) {
		this.canoneCorrisposto = canoneCorrisposto;
	}

	public String getTelefonoConduttore() {
		return telefonoConduttore;
	}

	public void setTelefonoConduttore(String telefonoConduttore) {
		this.telefonoConduttore = telefonoConduttore;
	}

	public Boolean getPrivacy() {
		return privacy;
	}

	public Boolean getAntiriciclaggio() {
		return antiriciclaggio;
	}

	public Boolean getOscuraIncarico() {
		return oscuraIncarico;
	}

	public Boolean getFlagLocato() {
		return flagLocato;
	}
	
	@Override
	public Boolean getEditAvaiable() {
		return editAvaiable;
	}
	@Override
	public void setEditAvaiable(Boolean editAvaiable) {
		this.editAvaiable = editAvaiable;
	}
	@Override
	public Boolean getDeleteAvaiable() {
		return deleteAvaiable;
	}
	@Override
	public void setDeleteAvaiable(Boolean deleteAvaiable) {
		this.deleteAvaiable = deleteAvaiable;
	}
	
	@Override
	public String getAgenteCreatedBy() {
		return agente;
	}
	
	public boolean isStatoBozza() {
		return StatoIncarico.BOZZA.equals(stato);
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IncaricoDTO incaricoDTO = (IncaricoDTO) o;
        if (incaricoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), incaricoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IncaricoDTO{" +
                "id=" + getId() +
                ", riferimento='" + getRiferimento() + "'" +
                ", tipo='" + getTipo() + "'" +
                ", categoriaIncarico='" + getCategoriaIncarico() + "'" +
                ", stato='" + getStato() + "'" +
                ", dataScadenza='" + getDataScadenza() + "'" +
                ", agente='" + getAgente() + "'" +
                ", agenteDiContatto='" + getAgenteDiContatto() + "'" +
                ", dataContatto='" + getDataContatto() + "'" +
                ", noteTrattativa='" + getNoteTrattativa() + "'" +
                ", datiFiscali='" + getDatiFiscali() + "'" +
                ", dataAlertFiscali='" + getDataAlertFiscali() + "'" +
                ", alertFiscali='" + getAlertFiscali() + "'" +
                ", ricorrenzaAlertFiscali='" + getRicorrenzaAlertFiscali() + "'" +
                ", alertCortesia='" + getAlertCortesia() + "'" +
                ", dataAlertCortesia='" + getDataAlertCortesia() + "'" +
                ", ricorrenzaAlertCortesia='" + getRicorrenzaAlertCortesia() + "'" +
                ", alertRicorrenza='" + getAlertRicorrenza() + "'" +
                ", dataAlertRicorrenza='" + getDataAlertRicorrenza() + "'" +
                ", ricorrenzaAlertRicorrenza='" + getRicorrenzaAlertRicorrenza() + "'" +
                ", privacy='" + isPrivacy() + "'" +
                ", antiriciclaggio='" + isAntiriciclaggio() + "'" +
                ", privacyAcquirenti='" + isPrivacyAcquirenti() + "'" +
                ", antiriciclaggioAcquirenti='" + isAntiriciclaggioAcquirenti() + "'" +
                ", prezzoRichiesta=" + getPrezzoRichiesta() +
                ", prezzoMinimo=" + getPrezzoMinimo() +
                ", prezzoAcquisto=" + getPrezzoAcquisto() +
                ", importoProvvigione=" + getImportoProvvigione() +
                ", importoProvvigioneAcquirenti=" + getImportoProvvigioneAcquirenti() +
                ", oscuraIncarico='" + isOscuraIncarico() + "'" +
                ", flagLocato='" + isFlagLocato() + "'" +
                ", nomeConduttore='" + getNomeConduttore() + "'" +
                ", cognomeConduttore='" + getCognomeConduttore() + "'" +
                ", canoneCorrisposto=" + getCanoneCorrisposto() +
                ", telefonoConduttore='" + getTelefonoConduttore() + "'" +
                ", dataInizioLocazione='" + getDataInizioLocazione() + "'" +
                ", dataFineLocazione='" + getDataFineLocazione() + "'" +
                ", noteLocazione='" + getNoteLocazione() + "'" +
                ", immobile=" + getImmobileId() +
                ", immobile='" + getImmobileCodice() + "'" +
            "}";
    }
}
