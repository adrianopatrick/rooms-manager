package br.edu.fanor.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.sun.xml.xsom.impl.scd.Iterators.Map;

@SuppressWarnings("unused")
@Entity(name="perfis_admins")
public class PerfilAdmin {

	@Id
	@SequenceGenerator(sequenceName="public.seq_perfis_admins",name="seq_perfis_admins",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="seq_perfis_admins")
	private Long id;
	
	private String nome;

	@OneToMany(mappedBy="perfil")
	private List<Administrador> administradores;
	
	@OneToMany
	private List<Permissoes> permissoes;
	
	public Boolean checkPermission(String nome){
		for (Permissoes permissao : permissoes) {
			if (permissao.getName().equals(nome)) {
				return permissao.getValue();
			}
		}
		return false;
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
