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
//		Professor professor;
//		professor = (Professor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
//		solicitacao.setProfessor(professor);
		solicitacaoDAO.insert(solicitacao);

	}

	public List<Solicitacao> listSolicitacaoProf(Long id){
		List<Solicitacao> list = solicitacaoDAO.findAllProf(id);
		return list;
	}
	
	public List<Solicitacao> listSolicitacaoPendente(){
		List<Solicitacao> list = solicitacaoDAO.findAllPendente();
		return list;
	}

}