package it.beije.suormary.rubrica.caroselli.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManagerJPA {

	private static EntityManagerFactory entityManagerFactory;
//	private static EntityManager entityManager;


	public static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
		entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
//		entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManagerFactory.createEntityManager();
	}
	
//	public void close() {
////		entityManager.close();
//		entityManagerFactory.close();
//	}
}