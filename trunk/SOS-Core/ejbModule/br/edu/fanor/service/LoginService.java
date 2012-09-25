package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.LoginDAO;
import br.edu.fanor.entity.Usuario;

@Stateless
public class LoginService {

	@EJB
	private LoginDAO dao;
	
	public Usuario validaLogin(String email, String senha){
		Usuario usuario = dao.buscaUsuario(email);
		if (usuario.getSenha().equals(senha)) {
			return usuario;
		} else {
			return null;
		}
	}
	
	
}
