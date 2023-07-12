package it.beije.suormary.web.mancuso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAManagerFactory {
	
	private JPAManagerFactory() {}
	
	private static EntityManager instance;
	
	public static EntityManager getEntityManager() {
		if(instance == null) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMaryWeb");
			instance = entityManagerFactory.createEntityManager();
		}
		
		return instance;
	}
}
