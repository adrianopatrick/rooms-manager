package br.edu.fanor.manager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.PerfilAdmin;
import br.edu.fanor.enums.TipoPermissoes;
import br.edu.fanor.service.AdministradorService;

@ManagedBean
@SessionScoped
public class PermissaoManager {

	private PerfilAdmin perfil;

	@EJB
	private AdministradorService service;

	public Boolean canEdit() {
		return getPerfil().checkPermission(TipoPermissoes.EDITAR);
	}

	public Boolean canDelete() {
		return getPerfil().checkPermission(TipoPermissoes.EXCLUIR);
	}

	public PerfilAdmin getPerfil() {
		if (perfil == null) {
			Administrador adm = (Administrador) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("usuario");
			perfil = service.findById(Administrador.class, adm.getId(), true).getPerfil();
		}
		return perfil;
	}

	public void setPerfil(PerfilAdmin perfil) {
		this.perfil = perfil;
	}

}
