package br.edu.fanor.util;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.edu.fanor.entity.Usuario;
import br.edu.fanor.service.UsuarioService;

public class UsuarioConverter implements Converter{

	@EJB
	UsuarioService usuarioService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().equals("")) {  
            return null;  
        }
		return usuarioService.findById(Usuario.class, value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {  
            return "";  
        }
		return ((Usuario) value).getId().toString();
	}

}
