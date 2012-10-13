package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

import br.edu.fanor.dao.ReservaDAO;
import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.entity.Usuario;

@Stateless
public class ReservaService extends GenericService<Usuario>{

	private static final long serialVersionUID = 6458622927634444979L;

	@EJB
	ReservaDAO reservaDAO;
	
	@EJB
	SolicitacaoService solicitacaoService;
	
	Administrador administrador = new Administrador();
	
	public void salvaReserva(Reserva reserva, Solicitacao solicitacao) {
		int a = reserva.getDataFinal().compareTo(reserva.getDataIncial());
//		administrador = (Administrador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		if (a == 1){
			solicitacaoService.salvaSolicitacao(solicitacao);
			reserva.setSolicitacao(solicitacao);
//			reserva.setAdministrador(administrador);
			reservaDAO.insert(reserva);
		} else {
			System.out.println("COLOCAR MENSAGEM");
		}

	}

}