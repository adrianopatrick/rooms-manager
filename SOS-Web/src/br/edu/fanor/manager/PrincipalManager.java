package br.edu.fanor.manager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Professor;
import br.edu.fanor.entity.Usuario;
import br.edu.fanor.exceptions.ValidacaoException;
import br.edu.fanor.service.UsuarioService;

@Singleton
@Startup
public class PrincipalManager {

	@EJB
	private UsuarioService usuarioService;

	@PostConstruct
	public void init() {
		createDefaultProf();
		createDefaultAdmin();
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
				System.out.println("admin padrao não inserido");
				e.printStackTrace();
			}
		}

		
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
				System.out.println("professor padrao não inserido");
				e.printStackTrace();
			}
		}

	}

}
