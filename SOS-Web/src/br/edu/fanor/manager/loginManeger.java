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
                        
                        if (usuario.getPerfilUsuario().getNome().equals("funcionarios")) {
                        	FacesContext.getCurrentInstance().getExternalContext().redirect("homeFuncionario.jsf");
						} else if (usuario.getPerfilUsuario().getNome().equals("professores")) {
							FacesContext.getCurrentInstance().getExternalContext().redirect("homeProfessor.jsf");
						} else {
							FacesContext.getCurrentInstance().getExternalContext().redirect("homeAdmin.jsf");
						}
						
                } catch (DefaultException e) {
                        msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMsg(), "");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                
                context.addCallbackParam("loggedIn", loggedIn);
                
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