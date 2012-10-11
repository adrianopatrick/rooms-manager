package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.SolicitacaoDAO;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.entity.Usuario;

@Stateless
public class EstadoSolicitacaoService extends GenericService<Usuario>{

	private static final long serialVersionUID = -8469735845097835531L;

	@EJB
	SolicitacaoDAO solicitacaoDAO;
	
	public void salvaSolicitacao(Solicitacao solicitacao) {
		solicitacaoDAO.insert(solicitacao);
	}

}