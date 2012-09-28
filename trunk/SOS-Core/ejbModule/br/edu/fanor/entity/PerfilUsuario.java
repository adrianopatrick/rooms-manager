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
	@SequenceGenerator(sequenceName="public.perfis_usuario",name="perfis_usuario",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="perfis_usuario")
	private Long id;
	
	private String nome;
	
	@OneToOne(mappedBy="perfilUsuario")
	private Usuario usuario;
	
	
	
	
	
}
