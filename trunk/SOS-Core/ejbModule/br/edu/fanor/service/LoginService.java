package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.LoginDAO;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.EmailNotFoundException;
import br.edu.fanor.exceptions.IncorrectPasswordException;

@Stateless
public class LoginService {

	@EJB
	private LoginDAO dao;

	public Usuario validaLogin(String email, String senha) throws EmailNotFoundException, IncorrectPasswordException{
		Usuario usuario = dao.buscaUsuario(email);
		if (usuario != null) {
			if (usuario.getSenha().equals(senha)) {
				return usuario;
			} else {
				throw new IncorrectPasswordException("Senha incorreta");
			}
		} else {
			throw new EmailNotFoundException("Email não cadastrado em nosso database");
		}

	}

}
