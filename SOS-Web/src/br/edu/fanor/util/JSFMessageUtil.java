package br.edu.fanor.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class JSFMessageUtil {
	public void sendInfoMessageToUser(String message) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO,"Informação!", message);
		addMessageToJsfContext(facesMessage);
	}

	public void sendErrorMessageToUser(String message) {
		FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_WARN,"Erro!", message);
		addMessageToJsfContext(facesMessage);
	}

	private FacesMessage createMessage(Severity severity,String summary, String mensagemErro) {
		return new FacesMessage(severity, summary, mensagemErro);
	}

	private void addMessageToJsfContext(FacesMessage facesMessage) {
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
}