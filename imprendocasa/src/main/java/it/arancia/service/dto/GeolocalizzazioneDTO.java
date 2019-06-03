package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Geolocalizzazione entity.
 */
public class GeolocalizzazioneDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private String immobile;

    private String latitudine;

    private String longitudine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImmobile() {
        return immobile;
    }

    public void setImmobile(String immobile) {
        this.immobile = immobile;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeolocalizzazioneDTO geolocalizzazioneDTO = (GeolocalizzazioneDTO) o;
        if (geolocalizzazioneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), geolocalizzazioneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GeolocalizzazioneDTO{" +
            "id=" + getId() +
            ", immobile='" + getImmobile() + "'" +
            ", latitudine='" + getLatitudine() + "'" +
            ", longitudine='" + getLongitudine() + "'" +
            "}";
    }
}
