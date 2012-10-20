package br.edu.fanor.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.UsuarioDAO;
import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Usuario;

@Stateless
public class UsuarioService extends GenericService<Usuario>{

	private static final long serialVersionUID = 5218604509449444278L;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	public Usuario findByEmail(String email) {
		Usuario usuario = usuarioDAO.findByEmail(email);
		return usuario;
	}

	//TODO terminar metodos e criar paginação
	public List<Usuario> getUsuariosList() {
		Class<Usuario> usuario = null;
		return usuarioDAO.findAll(usuario, true);
	}

	public List<Administrador> findAllUsuario() {
		List<Administrador> administradors = usuarioDAO.findAllUsuario();
		return administradors;
	}

	public List<Administrador> pesquisaUsuario(String nome) {
		List<Administrador> funcionarios = usuarioDAO.pesquisaFuncionario(nome);
		return funcionarios;
	}

}


