package br.edu.fanor.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


//Classe para fabricar sessões de conexão
public class SessionUtil {
	//Ideia parecida com o SessionFactory, mas quero evitar o warning "Deprecated"
	private EntityManagerFactory entityManagerFactory;
	
	public EntityManager getEntityManager(){
		//Quem vai mandar na persistencia
		return entityManagerFactory.createEntityManager();
	}
	
	public SessionUtil(){
		//Assim que instanciado, vai abrir uma conexão com o banco senão o createEntityManager não funfa.
		//Como parâmetro, o nome do persistence unit
		entityManagerFactory = Persistence.createEntityManagerFactory("jdbc/sos");
	}
	
}
