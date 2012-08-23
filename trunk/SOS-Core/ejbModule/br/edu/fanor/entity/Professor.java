package br.edu.fanor.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name="professores")
public class Professor extends Pessoa{
	
	@OneToMany(mappedBy="professor")
	@JoinTable ( name ="professor_solicitacao",
	joinColumns = @JoinColumn ( name ="id_professor"),
	inverseJoinColumns = @JoinColumn ( name ="id_solicitacao"))
	private List<Solicitacao> solicitacoes; 

}
