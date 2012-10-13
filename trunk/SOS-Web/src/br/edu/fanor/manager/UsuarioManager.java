package br.edu.fanor.manager;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import antlr.collections.List;
import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.UsuarioService;

@SessionScoped
@ManagedBean(name="usuarioManager")
public class UsuarioManager implements Serializable{

	private static final long serialVersionUID = 8300564796942826471L;

	private Integer tipo;
	
	private Usuario usuario = new Usuario();
	
	@EJB
	private UsuarioService usuarioService;
	
	private List usuariosList;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			if(tipo == 1){
			usuario = new Professor();
			}
			else
			{
				usuario = new Administrador();
			}
		}
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void salvar() throws ValidacaoException{
		
		usuarioService.save(usuario);				
	}
	
	//TODO Implementar TryCatch
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

	//Só para o eclipse não reclamar.
	public void setUsuariosList(List usuariosList) {
		this.usuariosList = usuariosList;
	}
	

}
