package br.edu.fanor.dao;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.fanor.entity.Usuario;

@Stateless
public class LoginDAO extends GenericDAO<Usuario>{

	private static final long serialVersionUID = 4607471052913499938L;
	
	public Usuario buscaUsuario(String email){
		Criteria criteria = getCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		return (Usuario) criteria.uniqueResult(); 
	}

}
