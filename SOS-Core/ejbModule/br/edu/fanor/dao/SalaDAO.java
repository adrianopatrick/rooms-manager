package br.edu.fanor.dao;


import java.util.Date;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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

	public Long pagaUltimoRegistroSala() {
		Query query = getEntityManager().createQuery("select id from salas where id = (select max(id) from salas)");
		return (Long) query.getResultList().get(0);
	}

//	public void salvaAcessoriosDaSala(Long id, Acessorio acessorio) {
//		Query query = getEntityManager().createQuery("INSERT INTO acessorios_salas (id_acessorio, id_sala) values (:id, :idsala);");
//		query.setParameter("id", id);
//		query.setParameter("idsala", acessorio.getId());
//		query.executeUpdate();
//	}
	
	
	@SuppressWarnings("unchecked")
	public List<Sala> listarSalasDisponiveis(Date inicio, Date fim) {		
		//TODO: parametrizar datas. e filtrar acessorios
		Query query = getEntityManager().createQuery("select r.sala.id from reservas r where not (('2012-11-07 06:00:00' < r.dataInicial and '2012-11-07 07:30:00' <= r.dataInicial) or ('2012-11-07 06:00:00' >= r.dataFinal and '2012-11-07 07:30:00' > r.dataFinal))");
		Criteria qSala = getCriteria(Sala.class);
		qSala.add(Restrictions.not(Restrictions.in("id", query.getResultList())));
		return (List<Sala>) qSala.list();
	}
}
