package br.edu.fanor.manager;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.fanor.entity.Acessorio;
import br.edu.fanor.service.AcessorioService;


@ManagedBean
@SessionScoped
public class AcessorioManager {

	@EJB
	AcessorioService acessorioService;
	
	private Acessorio acessorio = new Acessorio();
	private Map<String, Acessorio> mapListAcessorios = new HashMap<String, Acessorio>();
	
	public Acessorio getAcessorio() {
		return acessorio;
	}

	public void setAcessorio(Acessorio acessorio) {
		this.acessorio = acessorio;
	}

	public Map<String, Acessorio> getMapListAcessorios() {
		this.mapListAcessorios = acessorioService.getMapListAcessorios();
		return mapListAcessorios;
	}

	public void setMapListAcessorios(Map<String, Acessorio> mapListAcessorios) {
		this.mapListAcessorios = mapListAcessorios;
	}
}
