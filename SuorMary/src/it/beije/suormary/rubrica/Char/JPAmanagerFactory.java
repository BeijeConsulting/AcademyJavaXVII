package it.beije.suormary.rubrica.Char;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAmanagerFactory {
     private JPAmanagerFactory() {}
     private static EntityManager entityManager;
     public static EntityManager createEntityManager() {
    	 if(entityManager == null) {
    		 EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
    		 entityManager = entityManagerFactory.createEntityManager();
    	 }
    	 return entityManager;
     }
}
     
