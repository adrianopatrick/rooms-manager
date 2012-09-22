package br.edu.fanor.manager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.fanor.dao.UsuarioDAO;
import br.edu.fanor.entity.Professor;

@ViewScoped
@ManagedBean(name="teste")
public class TesteManager {

	@EJB
	UsuarioDAO usuarioDAO;
	
	public String novoProfessor(){
		Professor prof = new Professor();
		prof.setNome("Gustavo");
		prof.setEmail("gmelo@fanor.edu.br");
		prof.setSenha("senha");
		
		usuarioDAO.insert(prof);
		return "login";
		
	}
	
}
