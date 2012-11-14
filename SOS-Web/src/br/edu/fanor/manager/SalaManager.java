package br.edu.fanor.manager;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Acessorio;
import br.edu.fanor.entity.Sala;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.SalaService;

@RequestScoped
@ManagedBean
public class SalaManager extends AbstractMB implements Serializable{

	private static final long serialVersionUID = -84971612047807691L;

	@EJB
	SalaService salaService;
	
	private List<Sala> listarSalas;
	private List<Acessorio> acessoriosList;
	private Sala sala = new Sala();
	private Integer tipoSala = 0;

	//TODO Tratar Exception
	public void salvaSala() throws IOException{
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		try {
			salaService.save(sala);
			
			displayInfoMessageToUser("Sala " + sala.getNome() + " salvo com sucesso.");
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/homeAdmin.jsf");
			
		} catch (ValidacaoException e) {
			displayErrorMessageToUser("Erro ao tentar salvar ");
		}
	}
	
	//TODO criar tela de edição de sala
	public String pegarSala(Sala sala){
		setSala(sala);
		return "";
	}
	
	//TODO implementar métodos para checagem de nova entidade.
	public Boolean checarNovo(){
		return true;		 
	}
	
	//TODO implementar método de checagem de edição
	public Boolean checarEdit(){
		return !checarNovo();
	}
	
	public void listarTodasSalas() {
		listarSalas = salaService.listarTodas();
	}
	
	public void findByQtd(){
		listarSalas =  salaService.findByQtd(sala.getCapacidade());
	}
	
	public void findByNome(){
		listarSalas = salaService.findByNome(sala.getNome());
	}
	
	public List<Sala> getListarSalas(){
		if (listarSalas == null){
			return listarSalas = salaService.listarTodas();
		} else {
			return listarSalas;
		}
	}
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Integer getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(Integer tipoSala) {
		this.tipoSala = tipoSala;
	}

	public List<Acessorio> getAcessoriosList() {
		return acessoriosList;
	}

	public void setAcessoriosList(List<Acessorio> acessoriosList) {
		this.acessoriosList = acessoriosList;
	}

}
