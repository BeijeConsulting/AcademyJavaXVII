package it.beije.suormary.bookstore1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAManagerFactory {
	
	private JPAManagerFactory() {}
	
	private static EntityManagerFactory instance;
	
	public static EntityManager getEntityManager() {
		if(instance == null) {
			instance = Persistence.createEntityManagerFactory("SuorMaryWeb");
			
		}
		//System.out.println(instance);
		return instance.createEntityManager();
	}
}