package it.arancia.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import it.arancia.domain.enumeration.EsitoChiamata;

/**
 * A DTO for the ListaContatti entity.
 */
@SuppressWarnings("serial")
public class ListaContattiExtDTO extends AbstractAuditingDTO implements Serializable {

	private Long id;

	@NotNull
	private ZonedDateTime dateTime;

	@NotNull
	private EsitoChiamata esito;

	@NotNull
	private String motivazione;

	@Lob
	private String note;

	private ClienteDTO cliente;

	private Long incaricoId;

	private String incaricoRiferimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(ZonedDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public EsitoChiamata getEsito() {
		return esito;
	}

	public void setEsito(EsitoChiamata esito) {
		this.esito = esito;
	}

	public String getMotivazione() {
		return motivazione;
	}

	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public Long getIncaricoId() {
		return incaricoId;
	}

	public void setIncaricoId(Long incaricoId) {
		this.incaricoId = incaricoId;
	}

	public String getIncaricoRiferimento() {
		return incaricoRiferimento;
	}

	public void setIncaricoRiferimento(String incaricoRiferimento) {
		this.incaricoRiferimento = incaricoRiferimento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		ListaContattiExtDTO listaContattiDTO = (ListaContattiExtDTO) o;
		if (listaContattiDTO.getId() == null || getId() == null) {
			return false;
		}
		return Objects.equals(getId(), listaContattiDTO.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public String toString() {
		return "ListaContattiExtDTO [id=" + id + ", dateTime=" + dateTime + ", esito=" + esito + ", motivazione="
				+ motivazione + ", note=" + note + ", cliente=" + cliente + ", incaricoId=" + incaricoId
				+ ", incaricoRiferimento=" + incaricoRiferimento + "]";
	}

}
