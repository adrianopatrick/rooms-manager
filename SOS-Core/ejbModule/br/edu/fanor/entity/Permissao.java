package br.edu.fanor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import br.edu.fanor.enums.TipoPermissoes;

@Entity(name="permissoes")
public class Permissao {

	@Id
	@SequenceGenerator(sequenceName="public.seq_permissoes",name="seq_permissoes",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_permissoes")
	private Long id;
	
	private Integer tipo;
	
	private Boolean value;
	
	@ManyToOne(optional=false)
	private PerfilAdmin perfilAdmin;

	public Permissao() {
		super();
	}
	
	public Permissao(String nome, Boolean value) {
		this(TipoPermissoes.getTipoPermissoes(nome), value);
	}
	
	public Permissao(TipoPermissoes tipoPermissoes, Boolean value) {
		tipo = tipoPermissoes.getValue();
		this.value = value;
	}
	
	public Permissao(TipoPermissoes tipoPermissoes, Boolean value, PerfilAdmin perfilAdmin) {
		tipo = tipoPermissoes.getValue();
		this.value = value;
		this.perfilAdmin = perfilAdmin;
	}
	
	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public TipoPermissoes getTipo() {
		return TipoPermissoes.getTipoPermissoes(tipo);
	}

	public void setTipo(TipoPermissoes tipo) {
		this.tipo = tipo.getValue();
	}

	public PerfilAdmin getPerfilAdmin() {
		return perfilAdmin;
	}

	public void setPerfilAdmin(PerfilAdmin perfilAdmin) {
		this.perfilAdmin = perfilAdmin;
	}
	
}
