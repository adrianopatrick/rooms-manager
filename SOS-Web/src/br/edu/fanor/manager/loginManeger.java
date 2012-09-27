package br.edu.fanor.manager;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.DefaultException;
import br.edu.fanor.service.LoginService;

@ManagedBean
public class loginManeger {
 
	private String email;

	private String senha;
	
	@EJB
	private LoginService loginService;
	
	private Usuario usuario;

	public void login(ActionEvent actionEvent) throws IOException {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage msg = null;
		boolean loggedIn = false;
		
		try {
			usuario = loginService.validaLogin(email, senha);
			loggedIn = true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
			FacesContext.getCurrentInstance().getExternalContext().redirect("homeProfessor.jsf");
		} catch (DefaultException e) {
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMsg(), "");
//			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login ou senha Incorretos", "");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		context.addCallbackParam("loggedIn", loggedIn);
//		if (email != null && email.equals(usuario.getEmail()) && senha != null && senha.equals(usuario.getSenha())) {
//			loggedIn = true;
////			msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem Vindo!!", email);
//			FacesContext.getCurrentInstance().getExternalContext().redirect("homeProfessor.jsf");  
//		} else {
//			loggedIn = false;
//			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login ou senha Incorretos", "");
//		}
//		
//		FacesContext.getCurrentInstance().addMessage(null, msg);
		
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
