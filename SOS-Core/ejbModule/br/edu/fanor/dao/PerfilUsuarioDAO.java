package br.edu.fanor.dao;

import javax.ejb.Stateless;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.fanor.entity.PerfilUsuario;

@Stateless
public class PerfilUsuarioDAO extends GenericDAO<PerfilUsuario>{

	private static final long serialVersionUID = -4914783351314703069L;
	
	public PerfilUsuario CarregaPerfil(Long id){
		Criteria criteria = getCriteria(PerfilUsuario.class);
		criteria.add(Restrictions.eq("id", id));
		return (PerfilUsuario)criteria.uniqueResult(); 
	}
	
}
