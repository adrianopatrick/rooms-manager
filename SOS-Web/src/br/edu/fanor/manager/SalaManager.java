package br.edu.fanor.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.fanor.entity.Sala;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.SalaService;

@RequestScoped
@ManagedBean
public class SalaManager {

	@EJB
	SalaService salaService;
	
	private List<Sala> listarSalas = new ArrayList<Sala>(); 
	
	private Sala sala = new Sala();

	public void saveSala(){
		try {

			System.out.println("======================== TESTANDO METODO SALVAR===================");
			
//			Acessorio ace1 = new Acessorio();
//			ace1.setNome("Projetor");
//			ace1.setDescricao("Projetor, tanto faz");
//			
//			Acessorio ace2 = new Acessorio();
//			ace2.setNome("Notebook");
//			ace2.setDescricao("Notebook, tanto faz");
//			
//			sala.getAcessorios().add(ace1);
//			sala.getAcessorios().add(ace2);
			
			salaService.save(sala);
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("======================== Erro ao salvar Sala! ===================");
		}
	}

	public List<Sala> getListarSalas() {
//		if(listarSalas == null){
			this.listarSalas = salaService.findAll(Sala.class);
//		}
		return listarSalas;
	}

	public void setListarSalas(List<Sala> listarSalas) {
		this.listarSalas = listarSalas;
	}
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
