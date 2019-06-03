package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;

import it.arancia.domain.enumeration.StatoIncarico;

/**
 * A DTO for the DashboardDTO entity.
 */
public class IdashboardDTO implements Serializable {
	private StatoIncarico label;
    private Long value;

    public IdashboardDTO(StatoIncarico label, Long value) {
		super();
		this.label = label;
		this.value = value;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public StatoIncarico getLabel() {
		return label;
	}

	public void setLabel(StatoIncarico label) {
		this.label = label;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IdashboardDTO incaricoDTO = (IdashboardDTO) o;
        if (incaricoDTO.getValue() == null || getValue() == null) {
            return false;
        }
        return Objects.equals(getValue(), incaricoDTO.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }

    @Override
    public String toString() {
        return "IncaricoDTO{" +
            "label=" + getLabel().toString() +
            ", value='" + getValue() + "'" +
            "}";
    }
}
