package br.edu.fanor.manager;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.DefaultException;
import br.edu.fanor.service.LoginService;

@RequestScoped
@ManagedBean
public class LoginManager {

	private String email;

	private String senha;

	@EJB
	private LoginService loginService;
	
	private Usuario usuario;

	private boolean loggedIn = false;
	
	private String url;

	public void login(ActionEvent actionEvent) throws IOException {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		System.out.println(getRequest().getRequestURI());
		
		try {
			
			loggedIn = false;
			setUsuario(loginService.validaLogin(email, senha));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", getUsuario());
			loggedIn = true;
			
			if (url == null || url.contains("SOS-Web/paginas/login/login.jsf")) {
				if (getUsuario() instanceof Administrador) {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/homeAdmin.jsf");
				}else {
					FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/professor/homeProfessor.jsf");
				}
			}else {
				FacesContext.getCurrentInstance().getExternalContext().redirect(url);
			}	
					
			
		} catch (DefaultException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMsg(), "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}	

		
		context.addCallbackParam("loggedIn", loggedIn);

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public void logOut(ActionEvent actionEvent) {
		getRequest().getSession().invalidate();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/login/login.jsf");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}
	
	@SuppressWarnings("unused")
	private HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}