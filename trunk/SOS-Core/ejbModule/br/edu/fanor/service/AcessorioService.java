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
	
	private Map<String, Long> mapListAcessorios = new HashMap<String, Long>();
	List<Acessorio> acessorios = new ArrayList<Acessorio>();
	
	public List<Acessorio> listaTodos(){
		acessorios = acessorioDAO.lista();
		return acessorios;
	}
	
	public Map<String, Long> getMapListAcessorios() {
		for (int i = 0; i < listaTodos().size(); i++) {
			mapListAcessorios.put(acessorios.get(i).getNome(), acessorios.get(i).getId());
		}
		return mapListAcessorios;
	}

	public void setMapListAcessorios(Map<String, Long> mapListAcessorios) {
		this.mapListAcessorios = mapListAcessorios;
	}

}
