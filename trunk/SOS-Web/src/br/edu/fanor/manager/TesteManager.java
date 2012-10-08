package br.edu.fanor.manager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.fanor.dao.PerfilAdminDAO;
import br.edu.fanor.dao.UsuarioDAO;
import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.PerfilAdmin;
import br.edu.fanor.entity.Professor;

@ViewScoped
@ManagedBean(name="teste")
public class TesteManager {

	@EJB
	UsuarioDAO usuarioDAO;
	
	@EJB
	PerfilAdminDAO perfilAdminDAO;
	
	public String novoProfessor(){	
		Professor prof = new Professor();
		prof.setNome("Gustavo");
		prof.setEmail("gmelo@fanor.edu.br");
		prof.setSenha("senha");
		
		usuarioDAO.insert(prof);
		return "login";
		
	}
	
	public String novoAdmin(){
		PerfilAdmin perfilAdmin = new PerfilAdmin();
		perfilAdmin = perfilAdminDAO.CarregaPerfil(2L);

		Administrador admin = new Administrador();
		admin.setNome("Herbeth");
		admin.setEmail("h2milhome");
		admin.setSenha("123");
		admin.setPerfil(perfilAdmin);
		
		usuarioDAO.insert(admin);
		return "login";
		
	}
	
	public String novoFuncionario(){
		PerfilAdmin perfilAdmin = new PerfilAdmin();
		perfilAdmin = perfilAdminDAO.CarregaPerfil(3L);

		Administrador admin = new Administrador();
		admin.setNome("Erom");
		admin.setEmail("eromweb");
		admin.setSenha("123");
		admin.setPerfil(perfilAdmin);
		
		usuarioDAO.insert(admin);
		return "login";
		
	}
	
}
