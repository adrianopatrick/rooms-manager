//Dá uma olhada nesse exemplo e compara com o GenericDao.java
//Evitei usar Transition.commit por não saber ao certo como aplicar, por isso preferi o flush e o refresh.
package br.edu.fanor.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
//import org.hibernate.criterion.MatchMode;

public class HibernateDAO<T> {
	private Class<T> classe; //Atributo que alternar as classes que devem ser persistidas no banco
	private Session session; // Clase para o controle de sessões do banco de dados
	
	public HibernateDAO(Class<T> classe, Session session){
		this.classe = classe;
		this.session = session;
	}
	
	public void atualizar(T bean){
		session.update(bean); 
		session.flush(); //
		session.refresh(bean);
	}
	
	public void excluir( T bean) {
		session.delete(bean);
	}
	
	public T getBean(Integer id) {
		T bean = (T)session.get(classe, id);
		return bean;
	}
	
	public Set<T> getBeans(){//Lista para não permitir duplicação de elementos.
		Set<T> beans = (Set<T>)session.createCriteria(classe).list();
		session.getTransaction().commit();
		session.getTransaction().begin();
		return beans;
	}
	
	public void salvar(T bean) {
		session.save(bean);
	}
	
	public List<T> getBeansExemplo (T bean){//Lista para permitir elementos duplicados
		Example exemplo = Example.create(bean);
		//Necessário discutir a utilidade desta linha, pois ativar o MatchMode.START fará com que se ganhe tempo nas consultas. 
		exemplo.enableLike(); //Assim a consulta será feita com qualquer semelhança em qualquer parte da tabela 
		exemplo.ignoreCase(); //Metodo para desativar o Case sensitive, tanto faz ser maíscula como minúscula
		
		return session.createCriteria(classe).add(exemplo).list(); //:-D
	}
	
}
