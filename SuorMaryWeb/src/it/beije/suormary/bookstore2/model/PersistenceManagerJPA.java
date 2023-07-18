package it.beije.suormary.bookstore2.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManagerJPA {


	private static EntityManagerFactory entityManagerFactory;


	public static EntityManager getEntityManager() {
		if(entityManagerFactory == null) {
		entityManagerFactory = Persistence.createEntityManagerFactory("SuorMaryWeb");

		}
		return entityManagerFactory.createEntityManager();
	}
}
