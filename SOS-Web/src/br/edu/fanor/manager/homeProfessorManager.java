package br.edu.fanor.manager;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.dao.ReservaDAO;
import br.edu.fanor.dao.SolicitacaoDAO;
import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Solicitacao;

@SessionScoped
@ManagedBean
public class homeProfessorManager implements Serializable{
	
	private static final long serialVersionUID = -7652074045374161311L;

	@EJB
	ReservaDAO reservaDAO;
	
	@EJB
	SolicitacaoDAO solicitacaoDAO;
	
	private Reserva reserva = new Reserva();
	private Solicitacao solicitacao = new Solicitacao();
	
	public String salvar(){
		
//		FacesContext context = FacesContext.getCurrentInstance();
		
		solicitacaoDAO.insert(solicitacao);
		reservaDAO.insert(reserva);
//		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Salvo com sucesso.",""));
		return "homeProfessor";
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

}
