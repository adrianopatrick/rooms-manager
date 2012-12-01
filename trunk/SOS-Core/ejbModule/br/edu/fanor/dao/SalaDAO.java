package br.edu.fanor.dao;


import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.fanor.entity.Sala;

@Stateless
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
	public List<Sala> listarSalasDisponiveis(Date inicio, Date fim, Integer qtdAlunos, String tipoSala) {
		Query query = getEntityManager().createQuery("select r.sala.id from reservas r where not ((:inicio < r.dataInicial and :fim <= r.dataInicial) or (:inicio >= r.dataFinal and :fim > r.dataFinal))");
		query.setParameter("inicio", inicio);
		query.setParameter("fim", fim);
		Criteria qSala = getCriteria(Sala.class);
		List<?> list = query.getResultList();
		if (list != null && !list.isEmpty()) {
			qSala.add(Restrictions.not(Restrictions.in("id", list)));
		}
		if (qtdAlunos != null) {
			qSala.add(Restrictions.ge("capacidade", qtdAlunos));
		}
		if (tipoSala != null) {
			qSala.add(Restrictions.eq("tipoSala", tipoSala));
		}
		return (List<Sala>) qSala.list();
	}
	
}
