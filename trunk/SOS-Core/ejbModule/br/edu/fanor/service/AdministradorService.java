package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.AdministradorDAO;
import br.edu.fanor.entity.Administrador;

@Stateless
public class AdministradorService extends GenericService<Administrador> {

	private static final long serialVersionUID = -3045197905744141478L;

	@EJB
	private AdministradorDAO dao; 
	
}
