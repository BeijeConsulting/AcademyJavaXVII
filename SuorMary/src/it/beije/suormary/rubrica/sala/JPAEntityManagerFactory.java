package it.beije.suormary.rubrica.sala;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAEntityManagerFactory {
	private JPAEntityManagerFactory() {}
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager createEM() {
		if(entityManagerFactory==null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
		
		}
		return entityManagerFactory.createEntityManager();
	}
	
	
}
//EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
	//EntityManager entityManager = entityManagerFactory.createEntityManager();