package br.edu.fanor.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.SolicitacaoDAO;
import br.edu.fanor.entity.Solicitacao;

@Stateless
public class SolicitacaoService extends GenericService<Solicitacao>{

	private static final long serialVersionUID = -8469735845097835531L;

	@EJB
	SolicitacaoDAO solicitacaoDAO;
	
	public void salvaSolicitacao(Solicitacao solicitacao) {
		solicitacaoDAO.insert(solicitacao);
	}

	public List<Solicitacao> listSolicitacaoProf(Long id){
		List<Solicitacao> list = solicitacaoDAO.findAllProf(id);
		return list;
	}
	
	public List<Solicitacao> listSolicitacaoPendente(){
		List<Solicitacao> list = solicitacaoDAO.findSolicitacoesPendentes();
		return list;
	}

}