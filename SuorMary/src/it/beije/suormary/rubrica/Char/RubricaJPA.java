package it.beije.suormary.rubrica.Char;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;


public class RubricaJPA {

	
		
		public static List<Contact> loadRubricaJPA(EntityManager entityManager) {
			Scanner scanner = new Scanner(System.in);
            List<Contact> listContacts = null;

	    	 try {
	    		 System.out.print("Vuoi ordinare i contatti per nome e cognome? (si/no) : ");
	    		 String ord = scanner.nextLine();
	    		 Query query = null;
	    		 if(ord.equals("si")) query = entityManager.createQuery("SELECT c FROM Contact as c ORDER BY c.name,c.surname");
	    		 
	    		 else query = entityManager.createQuery("SELECT c FROM Contact as c");
	    		 
	    		  listContacts = query.getResultList();
	    		 
	    	 } catch(Exception e) {
	    		 System.out.println("Si è verificato un errore  : " + e.getMessage());
	    	 } 
	    	 return listContacts;
		}
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityManager entityManager = JPAmanagerFactory.createEntityManager();
		
//		EntityTransaction transaction = entityManager.getTransaction();
//		transaction.begin();

//		Contact contact = null;
		
		//SELECT di contatto specifico
//		contact = entityManager.find(Contact.class, 21);
//		System.out.println(contact);
		
		
		//INSERT
//		contact = new Contact();
//		//contact.setId(21);
//		contact.setName("Alessandro");
//		contact.setSurname("Ceccarelli");
//		contact.setPhoneNumber("09876543");
//		contact.setEmail("ale@beije.it");
//		contact.setNote("contatto inserito con JPA");

		//UPDATE
//		System.out.println("contact PRE UPDATE: " + contact);
//		//contact.setId(30);
//		contact.setName("Alessandro");
//		contact.setSurname("Sala");
//		contact.setPhoneNumber("09876543");
//		contact.setEmail("Alessandro@beije.it");
//		contact.setNote("contatto modificato con JPA");
//		
//		System.out.println("contact PRE : " + contact);
//		entityManager.persist(contact);
//		System.out.println("contact POST : " + contact);
		
		//DELETE
//		entityManager.remove(contact);
//		
//		transaction.commit();

		//SELECT JPQL

}
