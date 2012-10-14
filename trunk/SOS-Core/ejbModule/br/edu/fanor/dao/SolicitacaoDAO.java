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
	public List<Solicitacao> findAllProf() {
		List<Solicitacao> list = new ArrayList<Solicitacao>();
		Query query = getEntityManager().createQuery("from solicitacoes");
		list = query.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Solicitacao> findAllPendente() {
		List<Solicitacao> list = new ArrayList<Solicitacao>();
		Query query = getEntityManager().createQuery("from solicitacoes where estado = 'PENDENTE'");
		list = query.getResultList();
		return list;
	}

}
