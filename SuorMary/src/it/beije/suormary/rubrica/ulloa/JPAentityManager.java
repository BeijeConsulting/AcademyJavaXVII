package it.beije.suormary.rubrica.ulloa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAentityManager {
	private JPAentityManager () {}
	private static EntityManager entityManager;
	
	public static EntityManager getEntityManager() {
		if (entityManager == null) {
			System.out.println("creo EntityManager...");
			
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
}
