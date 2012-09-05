package br.edu.fanor.entity;

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
	private List<Reserva> reservas;
	
	@NotNull
	@Column(name="tipo_funcionario")	
	@Enumerated(EnumType.STRING)
	private TipoFuncionario tipoFuncionario;

}
