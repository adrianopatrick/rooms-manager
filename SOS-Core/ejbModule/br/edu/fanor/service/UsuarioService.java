package br.edu.fanor.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.UsuarioDAO;
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


	public List<Usuario> getUsuariosList() {
		Class<Usuario> usuario = null;
		return usuarioDAO.findAll(usuario, true);
	}

	public List<Usuario> findAllUsuario() {
		List<Usuario> administradors = usuarioDAO.findAllUsuario();
		return administradors;
	}

	public List<Usuario> pesquisaUsuario(String nome) {
		List<Usuario> funcionarios = usuarioDAO.pesquisaFuncionario(nome);
		return funcionarios;
	}

}


