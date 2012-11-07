package br.edu.fanor.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.fanor.enums.EstadoReserva;

@Entity(name = "reservas")
public class Reserva {

	@Id
	@SequenceGenerator(sequenceName = "public.seq_reservas", name = "seq_reservas", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reservas")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EstadoReserva estadoReserva = EstadoReserva.PENDENDE;

	private String observacao;

	@Column(name = "data_inicial")
	//@Temporal(TemporalType.TIMESTAMP)
	private Date dataIncial;

	@Column(name = "data_final")
	//@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;
	
	@ManyToOne(optional=false)
	private Sala sala;

	@OneToOne
	private Solicitacao solicitacao;

	@ManyToOne
	@JoinTable(name = "administrador_reserva", 
		inverseJoinColumns = @JoinColumn(name = "id_administrador"), 
		joinColumns = @JoinColumn(name = "id_reserva"))
	private Administrador administrador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Date getDataIncial() {
		return dataIncial;
	}

	public void setDataIncial(Date dataIncial) {
		this.dataIncial = dataIncial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

}
