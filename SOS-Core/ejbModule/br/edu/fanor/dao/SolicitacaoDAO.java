package br.edu.fanor.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.Criteria;

import br.edu.fanor.entity.Solicitacao;

@Stateless
public class SolicitacaoDAO extends GenericDAO<Solicitacao>{

	private static final long serialVersionUID = -2937516627590134125L;

	@SuppressWarnings("unchecked")
	public List<Solicitacao> findAll() {
		Criteria criteria = getCriteria(Solicitacao.class);
		return criteria.list();
	}

}
