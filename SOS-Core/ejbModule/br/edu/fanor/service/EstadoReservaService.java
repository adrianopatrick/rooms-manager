package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.ReservaDAO;
import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.entity.Usuario;

@Stateless
public class EstadoReservaService extends GenericService<Usuario>{

	private static final long serialVersionUID = 6458622927634444979L;

	@EJB
	ReservaDAO reservaDAO;
	
	@EJB
	EstadoSolicitacaoService solicitacaoService;
	
	public void salvaReserva(Reserva reserva, Solicitacao solicitacao) {

		int a = reserva.getDataFinal().compareTo(reserva.getDataInicial());

		if (a == 1){
			solicitacaoService.salvaSolicitacao(solicitacao);
			reserva.setSolicitacao(solicitacao);
			reservaDAO.insert(reserva);
		} else {
			System.out.println("COLOCAR MENSAGEM");
		}

	}

}