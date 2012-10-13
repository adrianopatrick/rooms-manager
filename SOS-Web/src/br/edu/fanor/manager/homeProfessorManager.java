package br.edu.fanor.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.service.ReservaService;
import br.edu.fanor.service.SolicitacaoService;

@SessionScoped
@ManagedBean
public class homeProfessorManager implements Serializable{
	
	private static final long serialVersionUID = -7652074045374161311L;

	@EJB
	SolicitacaoService solicitacaoService;
	
	@EJB
	ReservaService reservaService;
	
	private Reserva reserva = new Reserva();
	private Solicitacao solicitacao = new Solicitacao();
	private List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
	
	public String salvar(){
		reservaService.salvaReserva(reserva, solicitacao);
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

	public List<Solicitacao> getListaSolicitacao() {
		if (listaSolicitacao.size() == 0) {
			listaSolicitacao = solicitacaoService.listSolicitacao();
			System.out.println(listaSolicitacao.toString());
			return listaSolicitacao;
		}else {
			System.out.println(listaSolicitacao.toString());
			return listaSolicitacao;
		}
	}

}
