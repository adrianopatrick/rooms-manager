package br.edu.fanor.manager;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

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
			usuario = loginService.validaLogin(email, senha);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			loggedIn = true;
			
			System.out.println("batata:"+ url);
			if (url == null || url.equals("SOS-Web/paginas/login/login.jsf")) {
				//TODO: ir para pagina default do usuario 
			}else {
				//TODO: ir para a url
			}	
			
//			getRequest().getRequestDispatcher("public/redirect.jsf");
//			FacesContext.getCurrentInstance().getExternalContext().dispatch("public/redirect.jsf");
			
			
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
//		return "logout";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
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

}