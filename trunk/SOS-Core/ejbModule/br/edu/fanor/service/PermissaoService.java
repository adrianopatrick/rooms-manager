package br.edu.fanor.service;

import br.edu.fanor.entity.PerfilAdmin;
import br.edu.fanor.enums.TipoPermissoes;

public class PermissaoService extends GenericService<PerfilAdmin>{

	private static final long serialVersionUID = -4319420765045695765L;
	
	public void criarPermissoes(){
		for (TipoPermissoes tipoPermissoes : TipoPermissoes.values()) {
			
		}
	}

}
