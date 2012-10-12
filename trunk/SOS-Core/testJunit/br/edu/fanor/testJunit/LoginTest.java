package br.edu.fanor.testJunit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import br.edu.fanor.dao.LoginDAO;
import br.edu.fanor.entity.Usuario;

public class LoginTest {
	
	@Mock private LoginDAO dao;

	@Test public void 
	test() {
		when(dao.buscaUsuario(anyString())).thenReturn(userAdmin());
	}
	
	public Usuario userAdmin(){
		Usuario usuario = null;
		return usuario;
	}

}
