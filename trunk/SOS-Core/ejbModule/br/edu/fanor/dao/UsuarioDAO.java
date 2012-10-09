package br.edu.fanor.dao;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.fanor.entity.Usuario;


@SuppressWarnings("unused")
@Stateless
public class UsuarioDAO extends GenericDAO<Usuario>{

	private static final long serialVersionUID = 7632027564571686039L;
	
//	private Usuario usuario;
//	private EntityManager em;
//	private SessionUtil sessionUtil;
	
	
/*	public UsuarioDAO() {
		this.em = sessionUtil.getEntityManager();
	}
*/
	
	public Usuario findByEmail(String email){
		Criteria criteria = getCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		return (Usuario) criteria.uniqueResult(); 
	}
	

	
}
