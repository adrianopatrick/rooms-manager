package br.edu.fanor.service;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.SalaDAO;
import br.edu.fanor.entity.Acessorio;
import br.edu.fanor.entity.Sala;

@Stateless
public class SalaService extends GenericService<Sala>{

	private static final long serialVersionUID = -3156429340276177333L;

	@EJB
	private SalaDAO salaDAO;
	
	public List<Sala> findByNome(String nome){
		return salaDAO.findByNome(nome);
	}
	
	public List<Sala> findByQtd(int qtd){
		return salaDAO.findByCapacidade(qtd, qtd+10);
	}
	
	public List<Sala> listarTodas(){
		return salaDAO.listarTodas();
	}

	public Long pagaUltimoRegistroSala() {
		return salaDAO.pagaUltimoRegistroSala();
	}

	public void salvaAcessoriosDaSala(Long id, List<Acessorio> acessoriosList) {
		for (int i = 0; i < acessoriosList.size(); i++) {
			salaDAO.salvaAcessoriosDaSala(id, acessoriosList.get(i));
		}
	}

}
