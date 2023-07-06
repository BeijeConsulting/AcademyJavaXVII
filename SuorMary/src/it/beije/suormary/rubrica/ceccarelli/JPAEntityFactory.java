package it.beije.suormary.rubrica.ceccarelli;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityFactory {
	
	private JPAEntityFactory() {}
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager  openEntity() {
		if(entityManagerFactory ==null || (!entityManagerFactory.isOpen())) {
			System.out.println("creo entityManager");
			
			entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			//entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManagerFactory.createEntityManager();
	}

}
