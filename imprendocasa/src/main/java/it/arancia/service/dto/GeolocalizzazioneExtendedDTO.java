package it.arancia.service.dto;

import it.arancia.domain.enumeration.StatoIncarico;

import javax.persistence.Lob;
import java.io.Serializable;

public class GeolocalizzazioneExtendedDTO  extends AbstractAuditingDTO implements Serializable {
    private Long idGeolocalizzazione;

    private String immobile;

    private String latitudine;

    private String longitudine;

    private Long idIncarico;

    private String riferimentoIncarico;

    private StatoIncarico statoIncarico;

    private Long idImmobile;

    private String codiceImmobile;

    @Lob
    private String descrizioneImmobile;

    public Long getIdGeolocalizzazione() {
        return idGeolocalizzazione;
    }

    public void setIdGeolocalizzazione(Long idGeolocalizzazione) {
        this.idGeolocalizzazione = idGeolocalizzazione;
    }

    public String getImmobile() { return immobile; }

    public void setImmobile(String immobile) { this.immobile = immobile; }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public Long getIdIncarico() {
        return idIncarico;
    }

    public void setIdIncarico(Long idIncarico) {
        this.idIncarico = idIncarico;
    }

    public String getRiferimentoIncarico() {
        return riferimentoIncarico;
    }

    public void setRiferimentoIncarico(String riferimentoIncarico) {
        this.riferimentoIncarico = riferimentoIncarico;
    }

    public StatoIncarico getStatoIncarico() { return statoIncarico; }

    public void setStatoIncarico(StatoIncarico statoIncarico) {
        this.statoIncarico = statoIncarico;
    }

    public Long getIdImmobile() {
        return idImmobile;
    }

    public void setIdImmobile(Long idImmobile) {
        this.idImmobile = idImmobile;
    }

    public String getCodiceImmobile() {
        return codiceImmobile;
    }

    public void setCodiceImmobile(String codiceImmobile) {
        this.codiceImmobile = codiceImmobile;
    }

    public String getDescrizioneImmobile() {
        return descrizioneImmobile;
    }

    public void setDescrizioneImmobile(String descrizioneImmobile) {
        this.descrizioneImmobile = descrizioneImmobile;
    }
}
