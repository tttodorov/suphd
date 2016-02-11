package com.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("educationStatusConverter")
public class EducationStatusConverter implements Converter {
	 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
        	try {
                MesService service = (MesService) fc.getExternalContext().getApplicationMap().get("mesService");
                return service.getMesEducationStatuses().get(Integer.parseInt(value) - 1);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((MesObject) object).getId());
        }
        else {
            return null;
        }
    }
}