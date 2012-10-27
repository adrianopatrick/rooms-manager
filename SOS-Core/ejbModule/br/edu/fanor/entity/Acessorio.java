package br.edu.fanor.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="acessorios")
public class Acessorio {

	@Id
	@SequenceGenerator(sequenceName="public.seq_acessorios",name="seq_acessorios",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_acessorios")
	private Long id;
	
	private String nome;
	
	private String descricao; 
	
	@ManyToMany
	@JoinTable ( name ="acessorios_salas",
	joinColumns = @JoinColumn ( name ="id_acessorio"),
	inverseJoinColumns = @JoinColumn ( name ="id_sala"))
	private List<Sala> salas;
	

	@ManyToMany
	@JoinTable ( name ="acessorios_solicitacoes",
	joinColumns = @JoinColumn ( name ="id_acessorio"),
	inverseJoinColumns = @JoinColumn ( name ="id_solicitacao"))
	private List<Solicitacao> solicitacoes;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Sala> getSalas() {
		return salas;
	}

	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	public List<Solicitacao> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<Solicitacao> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}
	
}
