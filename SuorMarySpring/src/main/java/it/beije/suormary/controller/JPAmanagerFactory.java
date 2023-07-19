package it.beije.suormary.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAmanagerFactory {
private JPAmanagerFactory() {}
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager createEntityManager() {
		if (entityManagerFactory == null) {	
		   entityManagerFactory = Persistence.createEntityManagerFactory("SuorMarySpring");
		}
		
		return entityManagerFactory.createEntityManager();
	}
}