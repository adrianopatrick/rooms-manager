package br.edu.fanor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;


@SuppressWarnings("unused")
@Entity(name="usuarios")
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
public class Usuario {

	@Id
	@SequenceGenerator(sequenceName="public.seq_pessoas",name="seq_pessoas",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_pessoas")
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;

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

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}