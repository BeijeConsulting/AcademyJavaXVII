package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityFactory {
	
	private JPAEntityFactory() {}
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static EntityManager  openEntity() {
		if(entityManagerFactory ==null || (!entityManagerFactory.isOpen())) {
			System.out.println("creo entityManager");
			
			entityManagerFactory = Persistence.createEntityManagerFactory("SuorMaryWeb");
			//entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManagerFactory.createEntityManager();
	}

}
