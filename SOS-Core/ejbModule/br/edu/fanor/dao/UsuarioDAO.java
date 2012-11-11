package br.edu.fanor.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.fanor.entity.Solicitacao;
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
		Query query = getEntityManager().createQuery("from usuarios");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> pesquisaFuncionario(String nome) {
		Criteria criteria = getCriteria(Usuario.class);
		criteria.addOrder(Order.asc("nome"));
		criteria.add(Restrictions.ilike("nome","%"+nome+"%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscaAutoComplete(String nome) {
		Query query = getEntityManager().createQuery("from usuarios u where upper(u.nome) like upper(:nome)");
		query.setParameter("nome", nome+"%");
		return query.getResultList();
	}

	public Long pegaUserPorSolicitacao(Solicitacao solicitacao) {
//		Criteria criteria = getCriteria(Usuario.class);
//		criteria.add(Restrictions.eq("id_solicitacao", solicitacao.getId()));
//		return criteria.uniqueResult();
		
		
		Query query = getEntityManager().createNamedQuery("select id_professor from professor_solicitacao where id_solicitacao = :id");
		query.setParameter("id", solicitacao.getId());
		return (Long) query.getResultList().get(0);
	}

}
