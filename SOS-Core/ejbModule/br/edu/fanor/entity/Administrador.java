package br.edu.fanor.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name="administradores")
public class Administrador extends Usuario{
	
	@ManyToOne
	private PerfilAdmin perfilId;

	public Administrador() {
		super();
	}
	
	public Administrador(Usuario usuario) {
		super(usuario);
	}
	
	public PerfilAdmin getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(PerfilAdmin perfil) {
		this.perfilId = perfil;
	}
	
}
