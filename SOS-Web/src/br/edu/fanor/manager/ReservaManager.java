package br.edu.fanor.manager;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Sala;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.enums.EstadoReserva;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.ReservaService;
import br.edu.fanor.service.UsuarioService;

@SessionScoped
@ManagedBean
public class ReservaManager extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 2410613834286415194L;

	private Usuario usuario;

	private Solicitacao solicitacao;
	
	private Calendar dia;

	private Sala sala = new Sala();
	
	private Reserva reserva = new Reserva();

	@EJB
	UsuarioService usuarioService;

	@EJB
	ReservaService reservaService;
	
	public void reservarSala(){
		
		try {
			reserva.setAdministrador((Administrador)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
			
			if (reserva.getSolicitacao() == null) {
				reserva.setEstadoReserva(EstadoReserva.RESERVADO);
				reservaService.save(reserva);
			}else {
				reserva.setEstadoReserva(EstadoReserva.PENDENTEDEAVISO);
				reservaService.save(reserva);
				//TODO: implementar envio de email
			}
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Usuario> buscaPorNome(String query) {
		return usuarioService.buscaAutoComplete(query);

	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
		reserva.setDataIncial(solicitacao.getData());
		//TODO: colocar data final em solicitacao ou horas de reserva para calcular a data final.
		reserva.setDataFinal(solicitacao.getData());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDia() {
		return dia;
	}

	public void setDia(Calendar dia) {
		this.dia = dia;
		
		Calendar inicio = Calendar.getInstance();
		inicio.setTime(reserva.getDataIncial());
		inicio.set(dia.get(Calendar.YEAR), dia.get(Calendar.MONTH), dia.get(Calendar.DATE));
		reserva.setDataIncial(inicio.getTime());
		
		Calendar fim = Calendar.getInstance();
		fim.setTime(reserva.getDataFinal());
		fim.set(dia.get(Calendar.YEAR), dia.get(Calendar.MONTH), dia.get(Calendar.DATE));
		reserva.setDataFinal(fim.getTime());
		
	}

}
