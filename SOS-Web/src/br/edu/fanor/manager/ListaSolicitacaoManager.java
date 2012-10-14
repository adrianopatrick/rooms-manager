package br.edu.fanor.manager;

import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.manager.Teste;
import br.edu.fanor.service.SolicitacaoService;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

@SuppressWarnings("unused")
@RequestScoped
@ManagedBean
public class ListaSolicitacaoManager {
	
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
	
}