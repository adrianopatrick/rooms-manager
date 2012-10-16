package br.edu.fanor.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name="professores")
public class Professor extends Usuario{
	
	public Professor() {
		super();
	}
	
	public Professor(Usuario usuario) {
		super(usuario);
	}
	
	@OneToMany(mappedBy="professor")
	private List<Solicitacao> solicitacoes; 

}
