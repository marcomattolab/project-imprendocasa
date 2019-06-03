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
 * Criteria class for the Files entity. This class is used in FilesResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /files?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FilesCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nome;

    private StringFilter dimensione;

    private StringFilter estensione;

    private LongFilter immobileId;

    public FilesCriteria() {
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

    public StringFilter getDimensione() {
        return dimensione;
    }

    public void setDimensione(StringFilter dimensione) {
        this.dimensione = dimensione;
    }

    public StringFilter getEstensione() {
        return estensione;
    }

    public void setEstensione(StringFilter estensione) {
        this.estensione = estensione;
    }

    public LongFilter getImmobileId() {
        return immobileId;
    }

    public void setImmobileId(LongFilter immobileId) {
        this.immobileId = immobileId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FilesCriteria that = (FilesCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nome, that.nome) &&
            Objects.equals(dimensione, that.dimensione) &&
            Objects.equals(estensione, that.estensione) &&
            Objects.equals(immobileId, that.immobileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nome,
        dimensione,
        estensione,
        immobileId
        );
    }

    @Override
    public String toString() {
        return "FilesCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nome != null ? "nome=" + nome + ", " : "") +
                (dimensione != null ? "dimensione=" + dimensione + ", " : "") +
                (estensione != null ? "estensione=" + estensione + ", " : "") +
                (immobileId != null ? "immobileId=" + immobileId + ", " : "") +
            "}";
    }

}
