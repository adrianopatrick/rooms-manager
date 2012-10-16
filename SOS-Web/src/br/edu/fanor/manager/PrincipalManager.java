package br.edu.fanor.manager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.PerfilAdmin;
import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.enums.TipoPermissoes;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.PerfilAdminService;
import br.edu.fanor.service.PermissaoService;
import br.edu.fanor.service.UsuarioService;

/**
 * Classe usada para facilitar os testes
 * 
 * @author Joe
 *
 */

@Singleton
@Startup
public class PrincipalManager {

	@EJB
	private UsuarioService usuarioService;
	
	@EJB
	private PerfilAdminService perfilAdminService;

	@EJB
	private PermissaoService permissaoService;
	
	@PostConstruct
	public void init() {
		createDefaultProf();
		createDefaultAdmin();
		createAdminMaster();
//		createAdminBasic();
	}

	private void createAdminBasic() {
		Usuario usuario = usuarioService.findByEmail("basic");
		
		if (usuario == null) {
			Administrador admin = new Administrador();
			admin.setNome("Administrador Basic");
			admin.setEmail("basic");
			admin.setSenha("basic");
			admin.setPerfil(createPerfilBasic());

			try {
				usuarioService.saveOrUpdate(admin);
			} catch (ValidacaoException e) {
				System.out.println("admin basic n�o inserido");
				e.printStackTrace();
			}
		}
		
	}

	private void createAdminMaster() {
		Usuario usuario = usuarioService.findByEmail("master");
		
		if (usuario == null) {
			Administrador admin = new Administrador();
			admin.setNome("Administrador Master");
			admin.setEmail("master");
			admin.setSenha("master");
			
			admin.setPerfil(createPerfilMaster());
			
			try {
				usuarioService.saveOrUpdate(admin);
			} catch (ValidacaoException e) {
				System.out.println("admin master n�o inserido");
				e.printStackTrace();
			}
		}
		
	}

	private void createDefaultAdmin() {
		
		Usuario usuario = usuarioService.findByEmail("admin");
		
		if (usuario == null) {
			Administrador admin = new Administrador();
			admin.setNome("Administrador");
			admin.setEmail("admin");
			admin.setSenha("admin");

			try {
				usuarioService.save(admin);
			} catch (ValidacaoException e) {
				System.out.println("admin padrao n�o inserido");
				e.printStackTrace();
			}
		}

		
	}

	private PerfilAdmin createPerfilMaster() {
		PerfilAdmin perfilAdmin = perfilAdminService.findByName("master");
		if (perfilAdmin != null) {
			return perfilAdmin;
		}
		perfilAdmin = new PerfilAdmin();
		
		perfilAdmin.setNome("master");
		perfilAdmin.setId(1L);
		try {
			perfilAdminService.saveOrUpdate(perfilAdmin);
			permissaoService.addPermission(TipoPermissoes.EDITAR, true, perfilAdmin);
			permissaoService.addPermission(TipoPermissoes.EXCLUIR, true, perfilAdmin);
			permissaoService.addPermission(TipoPermissoes.INSERIR, true, perfilAdmin);
		} catch (ValidacaoException e) {
			System.out.println("perfil master nao inserido");
			e.printStackTrace();
		}
		return perfilAdmin;
		
	}
	
	private PerfilAdmin createPerfilBasic() {
		PerfilAdmin perfilAdmin = perfilAdminService.findByName("basic");
		if (perfilAdmin != null) {
			return perfilAdmin;
		}
		
		perfilAdmin = new PerfilAdmin();
		perfilAdmin.setNome("basic");
		perfilAdmin.addPermission(TipoPermissoes.EDITAR, false);
		perfilAdmin.addPermission(TipoPermissoes.EXCLUIR, false);
		perfilAdmin.addPermission(TipoPermissoes.INSERIR, false);
		try {
			perfilAdminService.saveOrUpdate(perfilAdmin);
		} catch (ValidacaoException e) {
			System.out.println("perfil basic nao inserido");
			e.printStackTrace();
		}
		return perfilAdmin;
		
	}

	private void createDefaultProf() {
		Usuario usuario = usuarioService.findByEmail("prof");
		
		if (usuario == null) {
			Professor prof = new Professor();
			prof.setNome("Professor");
			prof.setEmail("prof");
			prof.setSenha("prof");

			try {
				usuarioService.save(prof);
			} catch (ValidacaoException e) {
				System.out.println("professor padrao n�o inserido");
				e.printStackTrace();
			}
		}

	}

}
