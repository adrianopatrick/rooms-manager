package br.edu.fanor.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;


import javax.ejb.Stateful;


import br.edu.fanor.entity.Sala;

@Stateful
public class SalaDAO extends GenericDAO<Sala> {

	private static final long serialVersionUID = -3949689364295277956L;


	@SuppressWarnings("unchecked")
	public List<Sala> findByNome(String nome){
		Criteria consulta = getCriteria(Sala.class);
		consulta.add(Restrictions.ilike("nome", "%"+nome+"%") );
		return consulta.list();
	}

	@SuppressWarnings("unchecked")
	public List<Sala> findByCapacidade(int qtdMin, int qtdMax){
		Criteria consulta = getCriteria(Sala.class);
		consulta.add(Restrictions.between("capacidade", qtdMin, qtdMax));
		
		return consulta.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Sala> listarTodas(){
		Criteria query = getCriteria(Sala.class);		
		return query.list();
	}
	
	
	

}
