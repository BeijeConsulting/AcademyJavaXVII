package it.beije.suormary.web.rubrica;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAentityManagerFactory {
	private JPAentityManagerFactory () {}
	private static EntityManagerFactory instance;
	
	public static EntityManager getEntityManager() {
		if (instance == null) {
			System.out.println("creo EntityManager...");
			instance = Persistence.createEntityManagerFactory("SuorMaryWeb");
		}
		return instance.createEntityManager();
	}
}
