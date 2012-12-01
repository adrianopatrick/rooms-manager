package br.edu.fanor.manager;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Reserva;
import br.edu.fanor.entity.Sala;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.enums.EstadoReserva;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.ReservaService;
import br.edu.fanor.service.UsuarioService;
import br.edu.fanor.util.MailUtils;
import br.edu.fanor.util.SalaListener;

@SessionScoped
@ManagedBean
public class ReservaManager extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 2410613834286415194L;

	private Usuario usuario;

	private Solicitacao solicitacao;
	
	private Date dia;
	
//	private Sala sala = new Sala();
	
	private Reserva reserva = new Reserva();
	
	private List<Sala> salas;
	
	@ManagedProperty(value = "#{salaManager}")
	private SalaManager salaManager;
	
	@EJB
	UsuarioService usuarioService;

	@EJB
	ReservaService reservaService;

	public String reservarSala(){
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			reserva.setAdministrador((Administrador)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario"));
			
			if (reserva.getSolicitacao() == null) {
				reserva.setEstadoReserva(EstadoReserva.RESERVADO);
				reservaService.save(reserva);
				displayInfoMessageToUser("Reservado com sucesso");
			}else {
				reserva.setEstadoReserva(EstadoReserva.PENDENTEDEAVISO);
				reservaService.save(reserva);
				displayInfoMessageToUser("Reservado com sucesso, aguandando confirmação");
				//TODO: implementar envio de email
				enviarEmailDeComfirmacao(reserva);
				
			}
			reserva = new Reserva();
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			displayErrorMessageToUser("ERRO impossivel realizar reserva, tente mais tarde");
		}
		return "homeAdmin";
		
	}
	
	private void enviarEmailDeComfirmacao(final Reserva reserva){
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int count = 0;
				boolean enviado = false;
				
				while (count < 3 && !enviado) {
					try {
						System.out.println((count+1)+"� tentativa de envio do email.");
						
						MailUtils.sendMail(reserva.getSolicitacao().getProfessor().getEmail(), 
								"Comfirmacao de Sala", 
								"Este e apenas um email de confirmacao.\n\n" +
								"Reserva\n\n" +
								"Sala: "+ reserva.getSala().getNome() + "\n" +
								"Dia: " + formatarData("dd/MM/yyyy", reserva.getDataInicial())+ "\n" +
								"Periodo: "+ formatarData("HH:mm", reserva.getDataInicial()) + " - " + formatarData("HH:mm", reserva.getDataFinal()));
						
						enviado = true;
						
						System.out.println("Email enviado com sucesso!");
						
					} catch (Exception e) {
						count++;
					}
				}
				
				if (enviado) {
					reservaService.comfirmarReserva(reserva);
				}
			}
		});
		thread.start();
	}
	
	public String formatarData(String pattern, Date date){
		Format formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
	public String pegaSolicitacao(Solicitacao lista){
		this.reserva.setSolicitacao(lista);
		this.solicitacao = lista;
		this.usuario = solicitacao.getProfessor();
		this.dia = null;
		return "reservaSala";
	}
	
	public String limparReserva(){
		usuario = null;
		solicitacao = null;
		reserva = new Reserva();
		salaManager.configurarBusca(null, null);
		dia = null;
		return "reservaSala";
	} 
	
//	private void synchronizeDates() {
//		Calendar d = Calendar.getInstance();
//		d.setTime(dia);
//		
//		Calendar inicio = Calendar.getInstance();
//		inicio.setTime(reserva.getDataIncial());
//		inicio.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DATE));
//		reserva.setDataIncial(inicio.getTime());
//		
//		Calendar fim = Calendar.getInstance();
//		fim.setTime(reserva.getDataFinal());
//		fim.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DATE));
//		reserva.setDataFinal(fim.getTime());
//	}
	
	public void carregaPesquisa(ActionEvent event){
		if (solicitacao != null) {
			salaManager.configurarBusca(solicitacao.getDataInicial(), solicitacao.getDataFinal());
		}
		salaManager.setSalaListener(new SalaListener() {
			
			@Override
			public void selecionaSalaDisponivel(Sala sala) {
				dia = salaManager.getDia();
				reserva.setSala(sala);
				reserva.setDataInicial(salaManager.getDataInicio());
				reserva.setDataFinal(salaManager.getDataFim());
			}
		});
	}
	
	public List<Usuario> buscaPorNome(String query) {
		return usuarioService.buscaAutoComplete(query);

	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
		reserva.setDataInicial(solicitacao.getData());
		reserva.setDataFinal(solicitacao.getData());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDia() {
		return dia;
	}

	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public void setDia(Date dia) {
		this.dia = dia;
		
	}
	
//	public Sala getSala() {
//		return sala;
//	}
//
//	public void setSala(Sala sala) {
//		this.sala = sala;
//	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	
	public SalaManager getSalaManager() {
		return salaManager;
	}

	public void setSalaManager(SalaManager salaManager) {
		this.salaManager = salaManager;
	}


}
