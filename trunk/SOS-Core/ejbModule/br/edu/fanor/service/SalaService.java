package br.edu.fanor.service;


import java.util.ArrayList;
import java.util.Date;
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

//	public void salvaAcessoriosDaSala(Long id, List<Acessorio> acessoriosList) {
//		for (int i = 0; i < acessoriosList.size(); i++) {
//			salaDAO.salvaAcessoriosDaSala(id, acessoriosList.get(i));
//		}
//	}
	
	public List<Sala> listarSalasDisponiveis(Date inicio, Date fim) {
		return listarSalasDisponiveis(inicio, fim, null, null, null);
	}
	
	public List<Sala> listarSalasDisponiveis(Date inicio, Date fim, Integer qtdAlunos, String tipoSala) {
		return listarSalasDisponiveis(inicio, fim, qtdAlunos, tipoSala, null);
	}

	public List<Sala> listarSalasDisponiveis(Date inicio, Date fim, Integer qtdAlunos, String tipoSala, List<Acessorio> acessorios) {
		List<Sala> salas = salaDAO.listarSalasDisponiveis(inicio, fim, qtdAlunos, tipoSala);
		
		if (acessorios != null) {
			salas = filtrarAcessorios(salas, acessorios);
		}
		
		return salas;
	}
	
	public List<Sala> filtrarAcessorios(List<Sala> salas, List<Acessorio> acessorios){
		List<Sala> result = new ArrayList<Sala>();
		
		for (Sala sala : salas) {
			boolean pass = true;
			for (Acessorio acessorio : acessorios) {
				if (!sala.getAcessorios().contains(acessorio)) {
					pass = false;
				}
			}
			if (pass) {
				result.add(sala);
			}
		}
		
		return result;
	}
	
}
