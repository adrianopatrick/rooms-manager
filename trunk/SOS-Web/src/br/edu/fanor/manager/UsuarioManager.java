package br.edu.fanor.manager;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Solicitacao;
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
	private Solicitacao solicitacao = new Solicitacao();
	private Usuario usuario = new Usuario();
	private List<Usuario> listAdmin;
	private Long tipoUsuario = 0l;
	
	public void salvar() throws ValidacaoException, IOException{
		if (tipoUsuario == 1 || tipoUsuario == 2) {
			usuario = new Administrador(usuario);
		}else {
			usuario = new Professor(usuario);
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			usuario.setPerfil(tipoUsuario);
			usuarioService.save(usuario);

			displayInfoMessageToUser("Usuário "+usuario.getNome()+" salvo com sucesso.");
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/homeAdmin.jsf");
			
		}catch (Exception e) {
			displayErrorMessageToUser("Não foi possivel salvar o usuário, verifique os dados informados ou tente mais tarde.");
		}
		usuario = null;
		
	}
	
	public void editar() throws ValidacaoException, IOException{
		deletarEdit(usuario);
		if (tipoUsuario == 1 || tipoUsuario == 2) {
			usuario = new Administrador(usuario);
		}else {
			usuario = new Professor(usuario);
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			usuario.setPerfil(tipoUsuario);
			usuarioService.saveOrUpdate(usuario);
			displayInfoMessageToUser("Usuário "+usuario.getNome()+" atualizado com sucesso.");
		}catch (Exception e) {
			displayErrorMessageToUser("Não foi possivel salvar o usuário, verifique os dados informados ou tente mais tarde.");
		}finally{
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/listaUsuarios.jsf");
		}
		
		usuario = null;
	}
	
	public void deletar(Usuario usuario){
		try {
			usuarioService.delete(usuario);
			displayInfoMessageToUser("Usuário "+usuario.getNome()+" excluido com sucesso.");
		} catch (Exception e) {
			displayErrorMessageToUser("Não foi possivel excluir o usuário");
		}
		listaUsuariosPorNome();
	}
	
	public void deletarEdit(Usuario usuario){
		try {
			usuarioService.delete(usuario);
		} catch (Exception e) {	}
	}
	
	public String pegar(Usuario user){
		tipoUsuario = user.getPerfil();
		setUsuario(user);
		return "editarFuncionario";
	}
	
	public String pegaSolicitacao(Solicitacao lista){
		setSolicitacao(lista);
		return "reservaSala";
	}
	
	public String voltar(){
		setUsuario(null);
		return "listaUsuarios";
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
	
	public void listaUsuariosPorNome(){
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
			tipoUsuario = 3l;
		}
		if (usuario instanceof Administrador && usuario.getPerfil() == 1) {
			tipoUsuario = 1l;
		} else {
			tipoUsuario = 2l;
		}
		this.usuario = usuario;
	}
	
	public List<Usuario> getUsuariosList() {
		return usuarioService.getUsuariosList();
	}

	public Long getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Long tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<Usuario> getListAdmin() {
		if (listAdmin == null) {
			return listAdmin = usuarioService.findAllUsuario();
		} else {
			return listAdmin;
		}
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

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

}
