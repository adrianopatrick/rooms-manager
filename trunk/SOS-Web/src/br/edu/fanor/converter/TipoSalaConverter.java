package br.edu.fanor.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.fanor.enums.TipoSala;

@FacesConverter("tipoSala")
public class TipoSalaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 != null && !arg2.equals("")) {
			return TipoSala.get(Integer.parseInt(arg2));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof TipoSala) {
			TipoSala tipoSala = (TipoSala) value;
			return tipoSala.getKey().toString();
		}
		return null;
	}

	
	
}
