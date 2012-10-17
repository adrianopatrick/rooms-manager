package br.edu.fanor.manager;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.UsuarioService;

@RequestScoped
@ManagedBean(name="usuarioManager")
public class UsuarioManager extends AbstractMB implements Serializable{

	private static final long serialVersionUID = -1432844396467219691L;

	private Usuario usuario = new Usuario();
	
	@EJB
	private UsuarioService usuarioService;
	
	private List<Administrador> listAdmin = new ArrayList<Administrador>(); 
	
	private Integer tipoUsuario = 0;

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	//TODO Implementar TryCatch
	public void salvar() throws ValidacaoException, IOException{
		if (tipoUsuario == 1) {
			usuario = new Professor(usuario);
		}else {
			usuario = new Administrador(usuario);
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		
		//TODO:  criar um arquivo de configura��o para colocar todas as msgs do sistema
		try {
			usuarioService.save(usuario);
			displayInfoMessageToUser("Usuário "+usuario.getNome()+" salvo com sucesso.");
			
		} catch (Exception e) {
			displayErrorMessageToUser("Não foi possivel salvar o usuário");
		}
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/homeAdmin.jsf");
		
	}
		
	//TODO Implementar TryCatch
	public void deletar() throws ValidacaoException{
		usuarioService.delete(usuario);		
	}
	
	//TODO Implementar TryCatch
	public void atualizar() throws ValidacaoException{
		usuarioService.update(usuario);
	}
	
	public Usuario findByEmail() {
		return usuarioService.findByEmail(usuario.getEmail());
	}
	 
	public List<Usuario> getUsuariosList() {
		return usuarioService.getUsuariosList();
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Administrador> getListAdmin() {
		listAdmin = usuarioService.findAllFuncionario();
		return listAdmin;
	}

	public void setListAdmin(List<Administrador> listAdmin) {
		this.listAdmin = listAdmin;
	}

}
