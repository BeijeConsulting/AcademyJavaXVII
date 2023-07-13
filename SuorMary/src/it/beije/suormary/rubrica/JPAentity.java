package it.beije.suormary.rubrica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAentity {
	
	private JPAentity() {};
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");

		}
		return entityManagerFactory.createEntityManager();
	}

}
