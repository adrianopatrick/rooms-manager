package br.edu.fanor.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import br.edu.fanor.enums.EstadoSolicitacao;

@Entity(name="solicitacoes")
public class Solicitacao {
	
	@Id
	@SequenceGenerator(sequenceName="public.seq_solicitacaoes",name="seq_solicitacaoes",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_solicitacaoes")
	private Long id;
			
	@NotNull
	@Column(name="nr_alunos")
	private Integer nrAlunos;
	
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private EstadoSolicitacao estado;
	
	@OneToOne(mappedBy="solicitacao")
	private Reserva reserva;
	
	@NotNull
	@Column(name="data_reserva")
	private Calendar data;
	
	@ManyToOne(optional=false)
	@JoinTable ( name ="professor_solicitacao",
	inverseJoinColumns = @JoinColumn ( name ="id_professor"),
	joinColumns = @JoinColumn ( name ="id_solicitacao"))
	private Professor professor;
	
	@ManyToMany(mappedBy="solicitacoes")
	private List<Acessorio> acessorios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNrAlunos() {
		return nrAlunos;
	}

	public void setNrAlunos(Integer nrAlunos) {
		this.nrAlunos = nrAlunos;
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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
