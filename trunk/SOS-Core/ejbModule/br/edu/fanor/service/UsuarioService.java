package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.UsuarioDAO;
import br.edu.fanor.entity.Usuario;

@Stateless
public class UsuarioService extends GenericService<Usuario>{

	private static final long serialVersionUID = 5218604509449444278L;
	
	@EJB
	private UsuarioDAO dao;

	public Usuario findByEmail(String email) {
		Usuario usuario = dao.findByEmail(email);
		return usuario;
	}
	
}


