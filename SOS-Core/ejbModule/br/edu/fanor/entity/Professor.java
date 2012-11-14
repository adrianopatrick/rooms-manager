package br.edu.fanor.entity;

import javax.persistence.Entity;

@Entity(name="professores")
public class Professor extends Usuario{
	
	public Professor() {
		super();
	}
	
	public Professor(Usuario usuario) {
		super(usuario);
	}
	
//	@OneToMany(mappedBy="professor")
//	private List<Solicitacao> solicitacoes; 

}
