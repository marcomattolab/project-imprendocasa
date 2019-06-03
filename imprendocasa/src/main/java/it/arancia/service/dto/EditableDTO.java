package it.arancia.service.dto;

import java.io.Serializable;

public interface EditableDTO extends Serializable{

    public Boolean getEditAvaiable();

    public void setEditAvaiable(Boolean editAvaiable);

    public Boolean getDeleteAvaiable();

    public void setDeleteAvaiable(Boolean deleteAvaiable);
    
    public String getAgenteCreatedBy();
}
