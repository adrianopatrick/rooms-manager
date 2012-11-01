package br.edu.fanor.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.service.ReservaService;
import br.edu.fanor.service.SolicitacaoService;

@SessionScoped
@ManagedBean
public class ProfessorManager implements Serializable{
	
	private static final long serialVersionUID = -7652074045374161311L;

	@EJB
	SolicitacaoService solicitacaoService;
	
	@EJB
	ReservaService reservaService;
	
	private Reserva reserva = new Reserva();
	private Solicitacao solicitacao = new Solicitacao();
	private List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
	
	@SuppressWarnings("finally")
	public String salvar(){
		try {
			int a = reserva.getDataFinal().compareTo(reserva.getDataIncial());
			if (a == 1){
				//por que o professor est� salvando uma reserva???? professor apenas deve fazer a solicita��o
				reservaService.salvaReserva(reserva, solicitacao);
				reserva = null;
				solicitacao = null;
				infoOk();
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensagem:", "Horário não permitido."));
			}
			
		} catch (RuntimeException e) {
			error();
		} finally {
			return "homeProfessor";
		}
	}
	
	public void infoOk() {  
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Mensagem:", "Solicitação enviada com sucesso."));  
	}
	public void error() {  
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Mensagem:", "Erro ao enviar solicitação, por favor tente mais tarde."));
	}
	
	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Solicitacao> getListaSolicitacao() {
			listaSolicitacao = solicitacaoService.listSolicitacaoProf();
			return listaSolicitacao;
	}

}
