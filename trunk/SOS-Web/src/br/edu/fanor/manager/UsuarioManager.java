package br.edu.fanor.manager;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.UsuarioService;

@SessionScoped
@ManagedBean(name="usuarioManager")
public class UsuarioManager extends AbstractMB implements Serializable{

	private static final long serialVersionUID = -1432844396467219691L;

	@EJB
	private UsuarioService usuarioService;
	
	private String msgEmailException;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> listAdmin = new ArrayList<Usuario>(); 
	private Integer tipoUsuario = 0;
	
	public void salvar() throws ValidacaoException, IOException{
		if (tipoUsuario == 1) {
			usuario = new Professor(usuario);
		}else {
			usuario = new Administrador(usuario);
			//TODO implementar atendente
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			usuarioService.saveOrUpdate(usuario);
			displayInfoMessageToUser("Usuário "+usuario.getNome()+" salvo com sucesso.");
		} catch (Exception e) {
			displayErrorMessageToUser("Não foi possivel salvar o usuário");
		}
		
		if (isNew()) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/homeAdmin.jsf");
		}else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/listaUsuarios.jsf");
		}
		
		usuario = null;
		
	}
	
	public void deletar(Usuario usuario){
		System.out.println("deletando usuario: "+usuario.getNome());
		try {
			usuarioService.delete(usuario);
			displayInfoMessageToUser("Usuário "+usuario.getNome()+" excluido com sucesso.");
		} catch (Exception e) {
			displayErrorMessageToUser("Não foi possivel excluir o usuário");
		}
		listUsuarios();
	}
	
	public String editar(Usuario user){
		System.out.println("deu certo "+user.getNome());
		setUsuario(user);
		return "cadastroFuncionario";
	}
	
	public Boolean isNew(){
		if (usuario.getId() == null || usuario.getId() == 0) {
			return true;
		}else {
			return false;
		}
		 
	}
	
	public Boolean isEdit(){
		return !isNew();
	}
	
	public void listUsuarios(){
		listAdmin = usuarioService.pesquisaUsuario(usuario.getNome());
	}
	
	public void findAllUsuario(){
		listAdmin = usuarioService.findAllUsuario();
	}
		
	public void deletar() throws ValidacaoException{
		usuarioService.delete(usuario);		
	}
	
	public void atualizar() throws ValidacaoException{
		usuarioService.update(usuario);
	}
	
	public Usuario findByEmail() {
		return usuarioService.findByEmail(usuario.getEmail());
	}
	
	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		if (usuario instanceof Professor	) {
			tipoUsuario = 1;
		}
		if (usuario instanceof Administrador) {
			tipoUsuario = 3;
			//implementar atendente
		}
		this.usuario = usuario;
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

	public List<Usuario> getListAdmin() {
		return listAdmin;
	}

	public void setListAdmin(List<Usuario> listAdmin) {
		this.listAdmin = listAdmin;
	}

	public String getMsgEmailException() {
		return msgEmailException;
	}

	public void setMsgEmailException(String msgEmailException) {
		this.msgEmailException = msgEmailException;
	}

}
