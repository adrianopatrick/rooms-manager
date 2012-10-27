package br.edu.fanor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.fanor.entity.Acessorio;

@Stateless
public class AcessorioDAO extends GenericDAO<Acessorio> {

	private static final long serialVersionUID = 1918252677010484914L;

	@SuppressWarnings("unchecked")
	public List<Acessorio> lista() {
		List<Acessorio> acessorios = new ArrayList<Acessorio>();
		Query query = getEntityManager().createQuery("from acessorios");
		acessorios = query.getResultList();
		return acessorios;
	}

}
