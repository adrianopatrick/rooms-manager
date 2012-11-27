package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.ReservaDAO;
import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.enums.EstadoReserva;
import br.edu.fanor.enums.EstadoSolicitacao;

@Stateless
public class ReservaService extends GenericService<Reserva> {

	private static final long serialVersionUID = 6458622927634444979L;

	@EJB
	ReservaDAO reservaDAO;
	
	@EJB
	SolicitacaoService solicitacaoService;

	public void comfirmarReserva(Reserva reserva) {
		try {
			if (reserva != null) {
				reserva.setEstadoReserva(EstadoReserva.RESERVADO);
				saveOrUpdate(reserva);
				
				Solicitacao solicitacao = reserva.getSolicitacao(); 
				if (solicitacao != null) {			
					solicitacao.setEstado(EstadoSolicitacao.ATENDIDA);
					solicitacaoService.saveOrUpdate(solicitacao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}