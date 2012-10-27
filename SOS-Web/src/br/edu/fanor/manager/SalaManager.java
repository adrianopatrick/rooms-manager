package br.edu.fanor.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.fanor.entity.Sala;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.SalaService;

@RequestScoped
@ManagedBean(name="salaManager")
public class SalaManager extends AbstractMB implements Serializable{

	private static final long serialVersionUID = -84971612047807691L;

	@EJB
	SalaService salaService;
	
	private List<Sala> listarSalas = new ArrayList<Sala>(); 
	
	private Sala sala = new Sala();

	public void saveSala(){
		try {
			salaService.save(sala);
			displayInfoMessageToUser("Sala " + sala.getNome() + " salvo com sucesso.");
		} catch (ValidacaoException e) {
			displayErrorMessageToUser("Erro ao tentar salvar "+sala.getNome());
			e.printStackTrace();
		}
	}
	
	public List<Sala> listarTodasSalas() {
		return salaService.listarTodas();
	}
	
	public List<Sala> findByQtd(){
		return salaService.findByQtd(sala.getCapacidade());
	}
	
	public List<Sala> findByNome(){
		return salaService.findByNome(sala.getNome());
	}

	public void setListarSalas(List<Sala> listarSalas) {
		this.listarSalas = listarSalas;
	}
	
	public List<Sala> getListarSalas(){
		return listarSalas;
	}
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

}
