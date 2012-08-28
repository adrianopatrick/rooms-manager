package br.edu.fanor.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Solicitacao;

@ManagedBean
public class listaSolicitacaoManager {
	
	private List<Solicitacao> listaSolicitacaos = solicitacaos();
	
	private Solicitacao solicitacao;
	
	public List<Solicitacao> getListaSolicitacaos() {
		return listaSolicitacaos;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	public List<Solicitacao> solicitacaos(){
		List<Solicitacao> list = new ArrayList<Solicitacao>();

		Professor professor = new Professor();
		professor.setNome("Chuck Noia");
		professor.setEmail("@.com");
		
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setData(Calendar.getInstance());
		solicitacao.setId(1L);
		solicitacao.setNrAlunos(20);
		solicitacao.setObservacao("olhando pra ver se funciona");
		solicitacao.setProfessor(professor);
		
		Solicitacao solicitacao2 = new Solicitacao();
		solicitacao2.setData(Calendar.getInstance());
		solicitacao2.setId(2L);
		solicitacao2.setNrAlunos(22);
		solicitacao2.setObservacao("sei ... se funcionar");
		solicitacao2.setProfessor(professor);
		
		Solicitacao solicitacao3 = new Solicitacao();
		solicitacao3.setData(Calendar.getInstance());
		solicitacao3.setId(3L);
		solicitacao3.setNrAlunos(22);
		solicitacao3.setObservacao("sei ... ");
		solicitacao3.setProfessor(professor);
		
		list.add(solicitacao);
		list.add(solicitacao2);
		list.add(solicitacao3);
		
		return list;
		
	}

}