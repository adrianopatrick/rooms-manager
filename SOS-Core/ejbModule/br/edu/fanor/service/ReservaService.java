package br.edu.fanor.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.edu.fanor.dao.ReservaDAO;
import br.edu.fanor.entity.Reserva;

@Stateless
public class ReservaService extends GenericService<Reserva>{

	private static final long serialVersionUID = 6458622927634444979L;

	@EJB
	ReservaDAO reservaDAO;
	
}