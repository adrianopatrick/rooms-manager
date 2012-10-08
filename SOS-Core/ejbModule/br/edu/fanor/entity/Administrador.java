package br.edu.fanor.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name="administradores")
public class Administrador extends Usuario{
	
	@ManyToOne
	private PerfilAdmin perfil;

	public PerfilAdmin getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilAdmin perfil) {
		this.perfil = perfil;
	}
	
}
