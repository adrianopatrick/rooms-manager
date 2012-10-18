package br.edu.fanor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.fanor.entity.Administrador;
import br.edu.fanor.entity.Usuario;

@Stateless
public class UsuarioDAO extends GenericDAO<Usuario>{

	private static final long serialVersionUID = 7632027564571686039L;
	
	public Usuario findByEmail(String email){
		Criteria criteria = getCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		return (Usuario) criteria.uniqueResult(); 
	}

	@SuppressWarnings("unchecked")
	public List<Administrador> findAllFuncionario() {
		List<Administrador> administradors = new ArrayList<Administrador>();
		Query query = getEntityManager().createQuery("from usuarios where perfil_id = 2");
		administradors = query.getResultList();
		return administradors;
	}

	public List<Administrador> listFuncionario(String nome, String email) {
		Criteria criteria = getCriteria(Usuario.class);
		criteria.add(Restrictions.eq("nomep", nome));
		criteria.add(Restrictions.eq("email", email));
		return null;
	}
	
}
