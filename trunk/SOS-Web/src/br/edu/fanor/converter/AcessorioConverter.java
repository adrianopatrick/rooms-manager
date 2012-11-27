package br.edu.fanor.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.edu.fanor.entity.Acessorio;
import br.edu.fanor.service.AcessorioService;

@FacesConverter("acessorio")
public class AcessorioConverter implements Converter{

	AcessorioService acessorioService;

	public AcessorioConverter() {	
		try {
			InitialContext ctx = new InitialContext();
			acessorioService = (AcessorioService) ctx.lookup("java:global/SOS-EAR/SOS-Core/AcessorioService");
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
		return acessorioService.findById(Acessorio.class, Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null || value.equals("")) {  
            return "";  
        }
		return ((Acessorio) value).getId().toString();
	}

	public AcessorioService getAcessorioService() {
		return acessorioService;
	}

	public void setAcessorioService(AcessorioService acessorioService) {
		this.acessorioService = acessorioService;
	}
	
}
