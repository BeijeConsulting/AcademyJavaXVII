package it.beije.suormary.rubrica.ceccarelli;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAEntityFactory {
	
	private JPAEntityFactory() {}
	
	private static EntityManager entityManager;
	
	public static EntityManager  openEntity() {
		if(entityManager ==null) {
			System.out.println("creo entityManager");
			
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}

}
