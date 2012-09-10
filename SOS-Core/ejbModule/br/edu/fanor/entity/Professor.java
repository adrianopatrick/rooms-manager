package br.edu.fanor.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name="professores")
public class Professor extends Pessoa{
	
	@OneToMany(mappedBy="professor")
	private List<Solicitacao> solicitacoes; 

}