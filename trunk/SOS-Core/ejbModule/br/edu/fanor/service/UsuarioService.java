package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import antlr.collections.List;
import br.edu.fanor.dao.UsuarioDAO;
import br.edu.fanor.entity.Usuario;

@Stateless
public class UsuarioService extends GenericService<Usuario>{

	private static final long serialVersionUID = 5218604509449444278L;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	private List usuariosList;

	public Usuario findByEmail(String email) {
		Usuario usuario = usuarioDAO.findByEmail(email);
		return usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//TODO terminar metodos e criar paginação
	//TODO Criar paginação
	public List getUsuariosList() {
		return usuariosList;
	}

	public void setUsuariosList(List usuariosList) {
		this.usuariosList = usuariosList;
	}
}


