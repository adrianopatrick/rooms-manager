package br.edu.fanor.manager;

//import java.util.List;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.edu.fanor.dao.UsuarioDAO;
import br.edu.fanor.entity.Usuario;

@SessionScoped
@ManagedBean(name="usuarioManager")
public class UsuarioManager implements Serializable{

	private static final long serialVersionUID = 8300564796942826471L;
	
	public static final String INJECTION_NAME = "#{usuarioManager}";
	
	private Usuario usuario;
	
//	private Class<Usuario> usuarios;
	
		
	//TODO realmente não é necessáiro um construtor?
	
	public Usuario getUsuario() {
		//TODO verificar necessidade de checagem
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
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public String salvar(Usuario usuario){
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.insert(usuario);
		
		//TODO para qual página redirecionar?
		return "";
	}
	
	public String deletar(Usuario usuario){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.delete(usuario);
		
		//TODO para qual página redirecionar?
		return "";
	}
	
	public String atualizar(Usuario usuario){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuarioDAO.insertOrUpdate(usuario);
		
		//TODO para qual página redirecionar?
		return "";
	}
	
	 public Usuario findByEmail(){
		 UsuarioDAO usuarioDAO = new UsuarioDAO();
		 usuarioDAO.findByEmail(usuario.getEmail());
		 
		 return usuario;
	 }
	
	 //TODO criar uma classe para paginação
//	 public List<Usuario> findAll(){
//		 UsuarioDAO usuarioDAO = new UsuarioDAO();
//		 return usuarioDAO.findAll(usuarios, true);
//	 }
	
	
}
