package it.beije.suormary.rubrica.sala;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class RubricaJPA {

	public static void main(String[] args) {

		// Qui stai dichiarando una variabile di tipo EntityManagerFactory chiamata entityManagerFactory per 
		//memorizzare l'istanza creata in precedenza dall'createEntityManagerFactory()
		//Persistence.createEntityManagerFactory("SuorMary");: Questa riga di codice crea un'istanza di 
		//EntityManagerFactory utilizzando il nome del persistence unit "SuorMary". Il metodo createEntityManagerFactory 
		//è fornito da JPA e accetta come parametro il nome del persistence unit definito nel file "persistence.xml". 
		//Il persistence unit specifica la configurazione di JPA per il tuo progetto, inclusi i dettagli di connessione 
		//al database e le entità gestite.
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//EntityManager em = get
		//
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Contatto contact = null;
		
		//SELECT di contatto specifico
		contact = entityManager.find(Contatto.class, 21);
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
		
		transaction.commit();

		//SELECT JPQL
		Query query = entityManager.createQuery("SELECT c from Contatto as c"); //SELECT * FROM rubrica
//		Query query = entityManager.createQuery("SELECT c from Contact as c WHERE c.surname = :cognome");
//		query.setParameter("cognome", "Rossi");
		List<Contatto> contacts = query.getResultList();
		for (Contatto c : contacts) System.out.println(c);
		
		entityManager.close();
	}
	
}
