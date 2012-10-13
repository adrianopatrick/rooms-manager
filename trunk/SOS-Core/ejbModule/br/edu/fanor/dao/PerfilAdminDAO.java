package br.edu.fanor.dao;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.fanor.entity.PerfilAdmin;

@Stateless
public class PerfilAdminDAO extends GenericDAO<PerfilAdmin>{

	private static final long serialVersionUID = -4914783351314703069L;
	
	public PerfilAdmin CarregaPerfil(Long id){
		Criteria criteria = getCriteria(PerfilAdmin.class);
		criteria.add(Restrictions.eq("id", id));
		return (PerfilAdmin)criteria.uniqueResult(); 
	}
	
	public PerfilAdmin findByName(String name){
		Criteria criteria = getCriteria(PerfilAdmin.class);
		criteria.add(Restrictions.eq("nome", name));
		return (PerfilAdmin)criteria.uniqueResult(); 
	}
	
}
