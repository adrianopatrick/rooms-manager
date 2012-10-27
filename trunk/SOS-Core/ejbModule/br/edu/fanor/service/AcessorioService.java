package br.edu.fanor.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.AcessorioDAO;
import br.edu.fanor.entity.Acessorio;

@Stateless
public class AcessorioService extends GenericService<Acessorio> {

	private static final long serialVersionUID = -8927232173401253677L;

	@EJB
	AcessorioDAO acessorioDAO;
	
	public List<Acessorio> listaTodos(){
		List<Acessorio> acessorios = acessorioDAO.lista();
		return acessorios;
	}

}
