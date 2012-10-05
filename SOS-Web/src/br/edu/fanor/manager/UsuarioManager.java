package br.edu.fanor.manager;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.edu.fanor.entity.Usuario;

@SessionScoped
@ManagedBean(name="usuarioManager")
public class UsuarioManager implements Serializable{

	private static final long serialVersionUID = 8300564796942826471L;
	
	public static final String INJECTION_NAME = "#{usuarioManager}";
	
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logOut() {
		getRequest().getSession().invalidate();
		return "/paginas/login/login.jsf";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
}
