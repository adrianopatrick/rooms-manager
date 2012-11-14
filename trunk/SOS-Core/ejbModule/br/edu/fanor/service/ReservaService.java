package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

import br.edu.fanor.dao.ReservaDAO;
import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Solicitacao;

@Stateless
public class ReservaService extends GenericService<Reserva>{

	private static final long serialVersionUID = 6458622927634444979L;

	@EJB
	ReservaDAO reservaDAO;
	
	@EJB
	SolicitacaoService solicitacaoService;
	
	
	public void salvaReserva(Reserva reserva, Solicitacao solicitacao) {
		Professor professor = new Professor();
		professor = (Professor) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
//			solicitacao.setProfessor(professor);
			solicitacao.setId_professor(professor.getId());
			solicitacaoService.salvaSolicitacao(solicitacao);
			reserva.setSolicitacao(solicitacao);
			reservaDAO.insert(reserva);

	}

}