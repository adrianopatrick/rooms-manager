package br.edu.fanor.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity(name="salas")
public class Sala {

	@Id
	@SequenceGenerator(sequenceName="public.seq_salas",name="seq_salas",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_salas")
	private Long id;
	
	@NotNull
	@Column(unique=true)
	private String nome;
	
	@NotNull
	private Integer capacidade;
	
	@NotNull
	private Integer tipoSala;
	
	@ManyToMany(mappedBy="salas")
	private List<Acessorio> acessorios;
	
	@NotNull
	private String observacao;
	
//	private List<Reserva> reservas;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}
	
	public Integer getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(Integer tipoSala) {
		this.tipoSala = tipoSala;
	}

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public Long getId(){
		return id;
	}
}
