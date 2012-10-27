package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.SalaDAO;
import br.edu.fanor.entity.Sala;

@Stateless
public class SalaService extends GenericService<Sala>{

	private static final long serialVersionUID = -3156429340276177333L;
	
	@EJB
	SalaDAO salaDAO;
	
	public void salvaSala(Sala sala){
		salaDAO.insert(sala);
	}
	
}
