package br.edu.fanor.manager;

//import java.util.List;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.edu.fanor.dao.UsuarioDAO;
import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Usuario;

@SessionScoped
@ManagedBean(name="usuarioManager")
public class UsuarioManager implements Serializable{

	private static final long serialVersionUID = 8300564796942826471L;
	
	public static final String INJECTION_NAME = "#{usuarioManager}";
	
	@EJB
	private Usuario usuario;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
//	private Class<Usuario> usuarios;
	
		

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getInjectionName() {
		return INJECTION_NAME;
	}

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
		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public String salvar(String nome, String email, String senha){
		
		usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);

		
		usuarioDAO.insert(usuario);
		
		//TODO para qual página redirecionar?
		return "";
	}
	
	public String deletar(Usuario usuario){
		usuarioDAO = new UsuarioDAO();
		usuarioDAO.delete(usuario);
		
		//TODO para qual página redirecionar?
		return "";
	}
	
	public String atualizar(Usuario usuario, String nome, String email, String senha){
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(senha);
		
		usuarioDAO = new UsuarioDAO();
		usuarioDAO.insertOrUpdate(usuario);
		
		//TODO para qual página redirecionar?
		return "";
	}
	
	 public Usuario findByEmail(Usuario usuario){
		 usuarioDAO = new UsuarioDAO();
		 usuarioDAO.findByEmail(usuario.getEmail());
		 
		 return usuario;
	 }
	
	 //TODO criar uma classe para paginação
//	 public List<Usuario> findAll(){
//		 UsuarioDAO usuarioDAO = new UsuarioDAO();
//		 return usuarioDAO.findAll(usuarios, true);
//	 }
	
	
}
