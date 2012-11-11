package br.edu.fanor.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.SolicitacaoDAO;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.entity.Usuario;

@Stateless
public class SolicitacaoService extends GenericService<Usuario>{

	private static final long serialVersionUID = -8469735845097835531L;

	@EJB
	SolicitacaoDAO solicitacaoDAO;

	public void salvaSolicitacao(Solicitacao solicitacao) {
		solicitacaoDAO.insert(solicitacao);
	}
	

	/*
	 * TODO: pra quem fez esse m�todo..... listar solicita��o professor.... 
	 * tem q passar o professor como parametro... e listar apenas as solicita��es
	 * do respectivo professor passado.
	 */
	public List<Solicitacao> listSolicitacaoProf(){
		List<Solicitacao> list = solicitacaoDAO.findAllProf();
		return list;
	}
	
	public List<Solicitacao> listSolicitacaoPendente(){
		List<Solicitacao> list = solicitacaoDAO.findAllPendente();
		return list;
	}

}