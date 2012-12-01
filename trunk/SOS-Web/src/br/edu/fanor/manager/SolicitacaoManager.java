package br.edu.fanor.manager;

import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.manager.Teste;
import br.edu.fanor.service.SolicitacaoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.component.calendar.Calendar;

@SuppressWarnings("unused")
@RequestScoped
@ManagedBean
public class SolicitacaoManager {
	
	@EJB private SolicitacaoService solicitacaoService;
	
	private List<Solicitacao> listaSolicitacaoPendente = new ArrayList<Solicitacao>();
	
	public List<Solicitacao> getListaSolicitacaoPendente(){
		List<Solicitacao> list = solicitacaoService.listSolicitacaoPendente();
		return list;
	}
	
	public void setListaSolicitacaoPendente(List<Solicitacao> listaSolicitacaoPendente) {
		this.listaSolicitacaoPendente = listaSolicitacaoPendente;
	}

	public SolicitacaoService getSolicitacaoService() {
		return solicitacaoService;
	}

	public void setSolicitacaoService(SolicitacaoService solicitacaoService) {
		this.solicitacaoService = solicitacaoService;
	}

	public void validarHora(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    
		Date inicio = (Date) ((Calendar) component.findComponent("inicio")).getValue();
		Date fim = (Date) value;
		
		
        if(inicio.after(fim)) {
            FacesMessage message = new FacesMessage();	
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            message.setSummary("Time Error.");
            message.setDetail("A hora inicial tem que ser anterior a hora final");
            context.addMessage("formSolicitacao:inicio", message);
            throw new ValidatorException(message);
        }
    
	}
	
}