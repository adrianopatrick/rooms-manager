package br.edu.fanor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="permissoes")
public class Permissoes {

	@Id
	@SequenceGenerator(sequenceName="public.seq_permissoes",name="seq_permissoes",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_permissoes")
	private Long id;
	
	private String name;
	
	private Boolean value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}
}
