package br.edu.fanor.manager;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.PerfilAdmin;
import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Solicitacao;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.AdministradorService;
import br.edu.fanor.service.PerfilAdminService;
import br.edu.fanor.service.UsuarioService;

@SessionScoped
@ManagedBean(name="usuarioManager")
public class UsuarioManager extends AbstractMB implements Serializable{

	private static final long serialVersionUID = -1432844396467219691L;

	@EJB
	private UsuarioService usuarioService;
	
	@EJB
	private AdministradorService administradorService; 
	
	@EJB
	private PerfilAdminService perfilAdminService;
	
	private String msgEmailException;
	private Solicitacao solicitacao = new Solicitacao();
	private Usuario usuario = new Usuario();
	private String nomeUsuario;
	
	private List<Usuario> listAdmin;
	private Long tipoUsuario;
	
	private Map<String, Long> perfis;
	
	public void salvar() throws ValidacaoException, IOException{
		if (tipoUsuario == -1L) {
			usuario = new Professor(usuario);
		}else {
			usuario = new Administrador(usuario);
			((Administrador) usuario).setPerfil(perfilAdminService.findById(PerfilAdmin.class, tipoUsuario));
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
		try {
			
			usuarioService.saveOrUpdate(usuario);	

			displayInfoMessageToUser("Usuário "+usuario.getNome()+" salvo com sucesso.");
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/listaUsuarios.jsf");
			
		}catch (Exception e) {
			displayErrorMessageToUser("Não foi possivel salvar o usuário, verifique os dados informados ou tente mais tarde.");
		}
		listaUsuariosPorNome();
		usuario = null;
		tipoUsuario = null;
		
	}
	
//	public void editar() throws ValidacaoException, IOException{
//		
//		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//		
//		try {
//			usuarioService.saveOrUpdate(usuario);
//			displayInfoMessageToUser("Usuário "+usuario.getNome()+" atualizado com sucesso.");
//			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/listaUsuarios.jsf");
//			usuario = null;
//			tipoUsuario= null;
//		}catch (Exception e) {
//			displayErrorMessageToUser("Não foi possivel salvar o usuário, verifique os dados informados ou tente mais tarde.");
//		}
//		listaUsuariosPorNome();
//	}
	
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
		setUsuario(user);
		return "cadastroFuncionario";
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
		listAdmin = usuarioService.pesquisaUsuario(getNomeUsuario());
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
		this.usuario = usuario;		
		if (usuario != null) {
			if (usuario instanceof Professor) {
				tipoUsuario = -1L;
			}else if (usuario instanceof Administrador) {
				tipoUsuario = ((Administrador) usuario).getPerfil().getId();
			}
		}
	}
	
	public List<Usuario> getUsuariosList() {
		return usuarioService.getUsuariosList();
	}

	public Long getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Long tipoUsuario) {
		if (usuario instanceof Administrador) {
			((Administrador) usuario).setPerfil(perfilAdminService.findById(PerfilAdmin.class, tipoUsuario));
		}
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
	
	public String getNomeUsuario() {
		if (nomeUsuario == null){
			return "";
		} 
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Map<String, Long> getPerfis() {
		perfis = new HashMap<String	, Long>();
		
		if (!(usuario != null && usuario instanceof Administrador)) {
			perfis.put("Professor", -1L);
		}
		
		if (!(usuario != null && usuario instanceof Professor)) {
			List<PerfilAdmin> list = perfilAdminService.findAll(PerfilAdmin.class);
			for (PerfilAdmin perfilAdmin : list) {
				perfis.put(perfilAdmin.getNome(), perfilAdmin.getId());
			}
		}
		
		return perfis;
	}

	public String limparUsuario(){
		usuario = null;
		tipoUsuario = null;
		return "";
	}
	
	public void setPerfis(Map<String, Long> perfis) {
		this.perfis = perfis;
	}

}
