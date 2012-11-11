package br.edu.fanor.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.service.SolicitacaoService;

@ManagedBean
@SessionScoped
public class ProfessorManager implements Serializable{
	
	private static final long serialVersionUID = -7652074045374161311L;

	@EJB
	SolicitacaoService solicitacaoService;
	
	private Solicitacao solicitacao = new Solicitacao();
	
	private Date data;

	private List<Solicitacao> listaSolicitacao = new ArrayList<Solicitacao>();
	
	
	public String salvarSolicitacao(){
		//TODO: usar uma variavel statica para saber qual o nome do objeto do usuario na sessao
		solicitacao.setProfessor((Professor)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
		solicitacao.setData(new Date());
		sincronyzeData(solicitacao, data);
		
		//TODO: tipo da sala nao salvo (campo inexistente)
		solicitacaoService.salvaSolicitacao(solicitacao);
		solicitacao = new Solicitacao();
		data = null;
		listaSolicitacao = solicitacaoService.listSolicitacaoProf();
		return "homeProfessor";
	}
	
	public void sincronyzeData(Solicitacao solicitacao, Date date){
		Calendar data = Calendar.getInstance();
		data.setTime(date);
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial.setTime(solicitacao.getDataInicial());
		dataInicial.set(data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DATE));
		solicitacao.setDataInicial(dataInicial.getTime());
		
		Calendar dataFinal = Calendar.getInstance();
		dataFinal.setTime(solicitacao.getDataFinal());
		dataFinal.set(data.get(Calendar.YEAR), data.get(Calendar.MONTH), data.get(Calendar.DATE));
		solicitacao.setDataFinal(dataFinal.getTime());
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

	public List<Solicitacao> getListaSolicitacao() {
			listaSolicitacao = solicitacaoService.listSolicitacaoProf();
			return listaSolicitacao;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
