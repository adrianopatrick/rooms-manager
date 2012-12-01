package br.edu.fanor.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.fanor.entity.Solicitacao;

@Stateless
public class SolicitacaoDAO extends GenericDAO<Solicitacao>{

	private static final long serialVersionUID = -2937516627590134125L;

	@SuppressWarnings("unchecked")
	public List<Solicitacao> findAllProf(Long id) {
		List<Solicitacao> list = new ArrayList<Solicitacao>();
		Query query = getEntityManager().createQuery("from solicitacoes");
		list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Solicitacao> findSolicitacoesPendentes(){
		List<Solicitacao> list = new ArrayList<Solicitacao>();
		Query query = getEntityManager().createQuery("from solicitacoes s where s.id not in (select r.solicitacao.id from reservas r where r.solicitacao.id = s.id)");
		list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Solicitacao> findSolicitacoesDoDia() {
		List<Solicitacao> list = new ArrayList<Solicitacao>();
		Query query = getEntityManager().createQuery("from solicitacoes ");
		list = query.getResultList();
		return list;
	}

}
