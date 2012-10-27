package br.edu.fanor.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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
	public List<Usuario> findAllUsuario() {
		List<Usuario> administradors = new ArrayList<Usuario>();
		Query query = getEntityManager().createQuery("from usuarios where perfil_id = 2");
		administradors = query.getResultList();
		return administradors;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> pesquisaFuncionario(String nome) {
		Criteria criteria = getCriteria(Usuario.class);
		criteria.add(Restrictions.ilike("nome","%"+nome+"%"));
		return criteria.list();
	}
	
}
