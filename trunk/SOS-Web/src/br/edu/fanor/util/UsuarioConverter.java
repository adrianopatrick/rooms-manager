package br.edu.fanor.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.edu.fanor.entity.Usuario;
import br.edu.fanor.service.UsuarioService;

public class UsuarioConverter implements Converter{

	UsuarioService usuarioService;
	
	public UsuarioConverter() {
		try {
			InitialContext ctx = new InitialContext();
			usuarioService = (UsuarioService) ctx.lookup("java:global/SOS-EAR/SOS-Core/UsuarioService");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.trim().equals("")) {  
            return null;  
        }
		return usuarioService.findById(Usuario.class, Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {  
            return "";  
        }
		return ((Usuario) value).getId().toString();
	}

}
