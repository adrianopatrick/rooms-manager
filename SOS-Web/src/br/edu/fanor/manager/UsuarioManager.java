package br.edu.fanor.manager;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

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
	
	private Usuario usuario = new Usuario();
	//usuario = getUsuario();
	
	@EJB
	private UsuarioService usuarioService;
	
	private List usuariosList;
	
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
	
	//TODO Implementar TryCatch
	public void salvar() throws ValidacaoException, IOException{
		String url = null;
		
		usuarioService.save(usuario);	
		
		if (url == null || url.contains("SOS-Web/paginas/login/cadastroFuncionario.jsf")) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SOS-Web/paginas/admin/homeAdmin.jsf");
			}
		}
	
	//TODO Implementar TryCatch
	public void deletar() throws ValidacaoException{
		usuarioService.delete(usuario);		
	}
	
	//TODO Implementar TryCatch
	public void atualizar() throws ValidacaoException{
		usuarioService.update(usuario);
	}
	
	 public Usuario findByEmail(){
		return usuarioService.findByEmail(usuario.getEmail());
	 }
	 
	public List getUsuariosList() {
		return usuarioService.getUsuariosList();
	}

//***********************************Só para o eclipse não reclamar *******************************//
	public void setUsuariosList(List usuariosList) {
		this.usuariosList = usuariosList;
	}
// **********************************************************************************************//
	public void changeTipoUsuario(ValueChangeEvent e){
		Integer tipo = Integer.parseInt((String) e.getNewValue());
		if (tipo == 1) {
			usuario = new Professor();
		}else {
			usuario = new Administrador();
		}
	}
	

}
