package it.beije.suormary.rubrica.iannetta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Singleton {
	
	private Singleton() {
		System.out.println("creo l'oggetto...");
	}
	
	private static EntityManager instance;
	
	public static EntityManager getInstance() {
		if (instance == null) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			instance = entityManagerFactory.createEntityManager();
		}
		return instance;
	}

}
