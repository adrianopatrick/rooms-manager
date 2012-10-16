package br.edu.fanor.service;

import javax.ejb.Stateless;

import br.edu.fanor.entity.PerfilAdmin;
import br.edu.fanor.entity.Permissao;
import br.edu.fanor.enums.TipoPermissoes;
import br.edu.fanor.exceptions.ValidacaoException;

@Stateless
public class PermissaoService extends GenericService<Permissao>{

	private static final long serialVersionUID = -4319420765045695765L;
	
	public void criarPermissoes(){
		//TODO
//		for (TipoPermissoes tipoPermissoes : TipoPermissoes.values()) {
//			
//		}
	}

	public void addPermission(TipoPermissoes tipoPermissoes, Boolean value, PerfilAdmin perfilAdmin){
		Permissao permissao = new Permissao(tipoPermissoes, value, perfilAdmin);
		try {
			save(permissao);
		} catch (ValidacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
