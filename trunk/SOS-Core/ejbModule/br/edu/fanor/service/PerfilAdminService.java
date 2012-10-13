package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.PerfilAdminDAO;
import br.edu.fanor.entity.PerfilAdmin;

@Stateless
public class PerfilAdminService extends GenericService<PerfilAdmin>{

	private static final long serialVersionUID = 5218604509449444278L;
	
	@EJB
	private PerfilAdminDAO dao;
	
	public PerfilAdmin findByName(String name) {
		return dao.findByName(name);
	}
}

