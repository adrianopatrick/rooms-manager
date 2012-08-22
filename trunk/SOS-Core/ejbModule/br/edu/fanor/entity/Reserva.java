package br.edu.fanor.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import br.edu.fanor.enums.EstadoReserva;

@Entity(name="reservas")
public class Reserva {

	@Id
	@SequenceGenerator(sequenceName="public.seq_reservas",name="seq_reservas",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_reservas")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EstadoReserva estadoReserva;
	
	private String observacao;
	
	@Column(name="data_reserva")
	private Calendar data;
	
	@OneToOne
	private Solicitacao solicitacao;
	
	@ManyToOne
	private Funcionario funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}
	
}
