package br.edu.fanor.manager;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import antlr.collections.List;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.UsuarioService;

@SessionScoped
@ManagedBean(name="usuarioManager")
public class UsuarioManager implements Serializable{

	private static final long serialVersionUID = 8300564796942826471L;

	@EJB
	private Usuario usuario;
	
	@EJB
	private UsuarioService usuarioService;
	
	private Class<Usuario> usuariosList;
	
		

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void salvar(){
		usuarioService.salvar(usuario);				
	}
	
	//TODO Implementar Try Cath
	public void deletar() throws ValidacaoException{
		usuarioService.delete(usuario);		
	}
	
	public void atualizar(){
		usuarioService.atualizar(usuario);
	}
	
	 public Usuario findByEmail(){
		return usuarioService.findByEmail(usuario.getEmail());
	 }
	 
	public List getUsuariosList() {
		return usuarioService.getUsuariosList();
	}

	/*public void setUsuariosList(Class<Usuario> usuariosList) {
		this.usuariosList = usuariosList;
	}*/
	
//		TODO criar uma classe para paginação
//		 public List<Usuario> findAll(){
//		 UsuarioDAO usuarioDAO = new UsuarioDAO();
//		 return usuarioDAO.findAll(usuarios, true);
//	 }
	
	
}
