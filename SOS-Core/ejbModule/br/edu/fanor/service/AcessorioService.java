package br.edu.fanor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.AcessorioDAO;
import br.edu.fanor.entity.Acessorio;

@Stateless
public class AcessorioService extends GenericService<Acessorio> {

	private static final long serialVersionUID = -8927232173401253677L;

	@EJB
	AcessorioDAO acessorioDAO;
	
	private Map<String, Acessorio> mapListAcessorios = new HashMap<String, Acessorio>();
	List<Acessorio> acessorios = new ArrayList<Acessorio>();
	
	public List<Acessorio> listaTodos(){
		acessorios = acessorioDAO.lista();
		return acessorios;
	}
	
	public Map<String, Acessorio> getMapListAcessorios() {
		for (int i = 0; i < listaTodos().size(); i++) {
			mapListAcessorios.put(acessorios.get(i).getNome(), acessorios.get(i));
		}
		return mapListAcessorios;
	}

	public void setMapListAcessorios(Map<String, Acessorio> mapListAcessorios) {
		this.mapListAcessorios = mapListAcessorios;
	}

}
