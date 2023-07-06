package it.beije.suormary.rubrica;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class RubricaJPA {

	public static void main(String[] args) {
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		
//		EntityTransaction transaction = entityManager.getTransaction();
		EntityManager eM = JPAentity.getEntityManager();
				
			EntityTransaction transaction= eM.getTransaction();
			transaction.begin();

		Contact contact = null;
		
		//SELECT di contatto specifico
//		contact = eM.find(Contact.class, 21);
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
//		eM.remove(contact);
		
//		transaction.commit();

		//SELECT JPQL
		Query query = eM.createQuery("SELECT c from Contact as c"); //SELECT * FROM rubrica
//		Query query = entityManager.createQuery("SELECT c from Contact as c WHERE c.surname = :cognome");
//		query.setParameter("cognome", "Rossi");
		List<Contact> contacts = query.getResultList();
		for (Contact c : contacts) System.out.println(c);
		
		eM.close();
	}
	
}
