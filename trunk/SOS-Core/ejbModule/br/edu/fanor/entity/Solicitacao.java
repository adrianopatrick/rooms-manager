package br.edu.fanor.entity;

import java.util.ArrayList;
import java.util.Date;
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
	private String tipoSala;
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	private EstadoSolicitacao estado = EstadoSolicitacao.PENDENTE;
	
	@OneToOne(mappedBy="solicitacao")
	private Reserva reserva;
	
	@NotNull
	@Column(name="data_reserva")
	private Date data;

	@NotNull
	@Column(name="data_inicial")
	private Date dataInicial;

	@NotNull
	@Column(name="data_final")
	private Date dataFinal;
	
	@ManyToOne(optional=false)
	@JoinTable ( name ="professor_solicitacao",
	inverseJoinColumns = @JoinColumn ( name ="id_professor"),
	joinColumns = @JoinColumn ( name ="id_solicitacao"))
	private Professor professor;
	
	@ManyToMany
	@JoinTable ( name ="acessorios_solicitacoes",
	inverseJoinColumns = @JoinColumn ( name ="id_acessorio"),
	joinColumns = @JoinColumn ( name ="id_solicitacao"))
	private List<Acessorio> acessorios;

	public List<Acessorio> getAcessorios() {
		if (acessorios == null) {
			acessorios = new ArrayList<Acessorio>();
		}
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public EstadoSolicitacao getEstado() {
		return estado;
	}

	public void setEstado(EstadoSolicitacao estado) {
		this.estado = estado;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(String tipoSala) {
		this.tipoSala = tipoSala;
	}

}
