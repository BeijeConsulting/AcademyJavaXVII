package it.beije.suormary.rubrica.caroselli.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManagerJPA {

	private static PersistenceManagerJPA instance;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private PersistenceManagerJPA() {
		entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public static PersistenceManagerJPA getInstance() {
		if (instance == null) {
			instance = new PersistenceManagerJPA();
		}

		return instance;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
