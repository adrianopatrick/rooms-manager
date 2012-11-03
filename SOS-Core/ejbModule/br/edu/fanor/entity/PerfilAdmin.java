package br.edu.fanor.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.edu.fanor.enums.TipoPermissoes;

@Entity(name="perfis_admins")
public class PerfilAdmin {

	@Id
	@SequenceGenerator(sequenceName="public.seq_perfis_admins",name="seq_perfis_admins",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_perfis_admins")
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String nome;

	@OneToMany(mappedBy="perfilId")
	private List<Administrador> administradores;
	
	@OneToMany(mappedBy="perfilAdmin")
	private Set<Permissao> permissoes =  new HashSet<Permissao>();

	public Boolean checkPermission(TipoPermissoes tipoPermissao){
		for (Permissao permissao : permissoes) {
			if (permissao.getTipo().equals(tipoPermissao)) {
				return permissao.getValue();
			}
		}
		return null;
	}
	
	public void addPermission(Permissao permissao){
		permissoes.add(permissao);
	}
	
//	public void addPermission(String nome, Boolean value){
//		
//	}
	
	public void addPermission(TipoPermissoes tipoPermissoes, Boolean value){
		addPermission(new Permissao(tipoPermissoes, value, this));
	}
	
	public void setPermission(String nome, Boolean value){
		setPermission(TipoPermissoes.getTipoPermissoes(nome), value);
	}
	
	public void setPermission(TipoPermissoes tipoPermissoes, Boolean value){
		for (Permissao permissao : permissoes) {
			if (permissao.getTipo().equals(tipoPermissoes)) {
				permissao.setValue(value);
			}
		}
	}
	
	public Boolean checkPermission(String name){
		return checkPermission(TipoPermissoes.getTipoPermissoes(name));
	}
	
	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministrador(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
