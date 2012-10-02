package br.edu.fanor.manager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.fanor.dao.PerfilUsuarioDAO;
import br.edu.fanor.dao.UsuarioDAO;
import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Funcionario;
import br.edu.fanor.entity.PerfilUsuario;
import br.edu.fanor.entity.Professor;

@ViewScoped
@ManagedBean(name="teste")
public class TesteManager {

	@EJB
	UsuarioDAO usuarioDAO;
	
	@EJB
	PerfilUsuarioDAO perfilUsuarioDAO;
	
	public String novoProfessor(){
		PerfilUsuario perfilUsuario = new PerfilUsuario();
//		perfilUsuario = perfilUsuarioDAO.CarregaPerfil(1L);
		perfilUsuario =  new PerfilUsuario();
		perfilUsuario.setNome("perfil de professor");// acho q os perfis so serao necessários para os usuarios adm e funcionarios nao para os professores
		
		
		Professor prof = new Professor();
		prof.setNome("Gustavo");
		prof.setEmail("gmelo@fanor.edu.br");
		prof.setSenha("senha");
		prof.setPerfilUsuario(perfilUsuario);
		
		perfilUsuarioDAO.insert(perfilUsuario);
		usuarioDAO.insert(prof);
		return "login";
		
	}
	
	public String novoAdmin(){
		PerfilUsuario perfilUsuario = new PerfilUsuario();
		perfilUsuario = perfilUsuarioDAO.CarregaPerfil(2L);

		Administrador admin = new Administrador();
		admin.setNome("Herbeth");
		admin.setEmail("h2milhome");
		admin.setSenha("123");
		admin.setPerfilUsuario(perfilUsuario);
		
		usuarioDAO.insert(admin);
		return "login";
		
	}
	
	public String novoFuncionario(){
		PerfilUsuario perfilUsuario = new PerfilUsuario();
		perfilUsuario = perfilUsuarioDAO.CarregaPerfil(3L);

		Funcionario func = new Funcionario();
		func.setNome("Diego");
		func.setEmail("diego@");
		func.setSenha("123");
		func.setPerfilUsuario(perfilUsuario);
		
		
		usuarioDAO.insert(func);
		return "login";
		
	}
	
}
