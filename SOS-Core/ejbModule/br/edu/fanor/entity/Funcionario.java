package br.edu.fanor.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name="funcionarios")
public class Funcionario extends Usuario{
	
	@OneToMany(mappedBy="funcionario")
	private List<Reserva> reservas;

}
