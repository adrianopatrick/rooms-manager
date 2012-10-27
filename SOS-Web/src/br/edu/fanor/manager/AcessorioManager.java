package br.edu.fanor.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.edu.fanor.entity.Acessorio;
import br.edu.fanor.service.AcessorioService;

@ManagedBean
@RequestScoped
public class AcessorioManager {

	@EJB
	AcessorioService acessorioService;
	
	private Acessorio acessorio = new Acessorio();
	private List<Acessorio> listarAcessorios = new ArrayList<Acessorio>();
	private Map<String, Long> mapListAcessorios = new HashMap<String, Long>();
	
	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}

	public List<Acessorio> getListarAcessorios() {
		this.listarAcessorios = acessorioService.listaTodos();
		return listarAcessorios;
	}

	public void setListarAcessorios(List<Acessorio> listarAcessorios) {
		this.listarAcessorios = listarAcessorios;
	}

	public Map<String, Long> getMapListAcessorios() {
		this.mapListAcessorios = acessorioService.getMapListAcessorios();
		return mapListAcessorios;
	}

	public void setMapListAcessorios(Map<String, Long> mapListAcessorios) {
		this.mapListAcessorios = mapListAcessorios;
	}
}
