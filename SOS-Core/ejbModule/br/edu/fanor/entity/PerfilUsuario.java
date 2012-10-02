package br.edu.fanor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="perfis_usuario")
public class PerfilUsuario {

	@Id
	@SequenceGenerator(sequenceName="public.seq_perfis_usuarios",name="seq_perfis_usuarios",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_perfis_usuarios")
	private Long id;
	
	private String nome;
	

	@OneToOne(mappedBy="perfilUsuario")
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
