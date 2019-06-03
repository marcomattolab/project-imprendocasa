package it.arancia.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Files entity.
 */
public class FilesDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    private String nome;

    private String dimensione;

    private String estensione;

    private Long immobileId;

    private String immobilePathFolder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDimensione() {
        return dimensione;
    }

    public void setDimensione(String dimensione) {
        this.dimensione = dimensione;
    }

    public String getEstensione() {
        return estensione;
    }

    public void setEstensione(String estensione) {
        this.estensione = estensione;
    }

    public Long getImmobileId() {
        return immobileId;
    }

    public void setImmobileId(Long immobileId) {
        this.immobileId = immobileId;
    }

    public String getImmobilePathFolder() {
        return immobilePathFolder;
    }

    public void setImmobilePathFolder(String immobilePathFolder) {
        this.immobilePathFolder = immobilePathFolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilesDTO filesDTO = (FilesDTO) o;
        if (filesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), filesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FilesDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dimensione='" + getDimensione() + "'" +
            ", estensione='" + getEstensione() + "'" +
            ", immobile=" + getImmobileId() +
            ", immobile='" + getImmobilePathFolder() + "'" +
            "}";
    }
}
