package br.edu.fanor.manager;

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
	
	private Sala sala;

	public void saveSala(){

		Sala s = new Sala();
		s.setNome("sala teste");
		s.setCapacidade(50);

		try {
			salaService.save(s);
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
