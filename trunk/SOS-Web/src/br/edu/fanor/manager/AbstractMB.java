package br.edu.fanor.manager;

import java.util.Calendar;
import java.util.Date;

import org.primefaces.context.RequestContext;

import br.edu.fanor.util.JSFMessageUtil;

public class AbstractMB {

	public AbstractMB() {
		super();
	}

	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}
	
	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}
	
	protected RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}
	
	public Date synchronizeDate(Date hora, Date date){
		Calendar data = Calendar.getInstance();
		data.setTime(date);
		
		Calendar dataCompleta = Calendar.getInstance();
		dataCompleta.setTime(hora);
		dataCompleta.set(data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DATE));
		return dataCompleta.getTime();
		
	}
}