package it.beije.suormary.rubrica.trapani;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAentity {
	
	private JPAentity() {};
	
	private static EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		if(entityManager == null) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}

}