package br.edu.fanor.manager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.fanor.dao.ProfessorDAO;
import br.edu.fanor.entity.Professor;

@ViewScoped
@ManagedBean(name="teste")
public class TesteManager {

	@EJB
	ProfessorDAO professorDAO;
	
	public String novoProfessor(){
		Professor prof = new Professor();
		prof.setNome("Gustavo");
		prof.setEmail("gmelo@nasdasd");
		prof.setSenha("senha");
		
		professorDAO.insert(prof);
		return "login";
		
	}
	
}
