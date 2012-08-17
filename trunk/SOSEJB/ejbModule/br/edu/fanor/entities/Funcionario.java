package br.edu.fanor.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.edu.fanor.enums.TipoFuncionario;

@Entity(name="funcionarios")
public class Funcionario extends Pessoa{
	
	@OneToMany(mappedBy="funcionario")
	@JoinTable ( name ="funcionario_reserva",
	joinColumns = @JoinColumn ( name ="id_funcionario"),
	inverseJoinColumns = @JoinColumn ( name ="id_reserva"))
	private List<Reserva> reservas;
	
	@NotNull
	@Column(name="tipo_funcionario")	
	@Enumerated(EnumType.STRING)
	private TipoFuncionario tipoFuncionario;

}
