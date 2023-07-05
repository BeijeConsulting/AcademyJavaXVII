package it.beije.suormary.rubrica.Char;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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
		public static Contact findContactByNameSurname(EntityManager entityManager) throws Exception {
		     Scanner scanner = new Scanner(System.in);
	    	 List<Contact> listContacts = null;
	    	 try {
	    		 System.out.print("Inserisci il nome : ");
	    		 String name = scanner.nextLine();
	    		 System.out.print("Inserisci il cognome : ");
	    		 String surname = scanner.nextLine();
	    		 Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c.name = :name AND c.surname = :surname");
	    		 query.setParameter("name", name);
	    		 query.setParameter("surname", surname);
	    		 listContacts = query.getResultList();
	    		 
	    	 } catch(Exception e) {
	    		 System.out.println("Si è verificato un errore  : " + e.getMessage());
	    	 } 
			 for(int i = 0; i < listContacts.size(); i++) {
				 System.out.println(i + ". " + listContacts.get(i));
			 }
	    	 if(listContacts.size() == 1) return listContacts.get(0);
			 else if (listContacts.size() == 0) throw new Exception("Non sono stati trovati contatti con quel nome e cognome");
			 System.out.print("Sono stati trovati più contatti con lo stesso nome e cognome, inserisci il numero del contatto che ti interessa : ");
				 int num = scanner.nextInt();
				 scanner.nextLine();
				 return listContacts.get(num);
			
			
		}
		public static void findContact(EntityManager entityManager) {
			try {
				Contact c = findContactByNameSurname(entityManager);
				System.out.println("Informazioni riguardo al contatto selezionato...");
				System.out.println(c);
			} catch (Exception e) {
				 System.out.println("Si è verificato un errore  : " + e.getMessage());
			}
		}
		public static void createContact(EntityManager entityManager) {
			 Scanner scanner = new Scanner(System.in);
	    	 List<Contact> listContacts = null;
	    	 try {
	    		 System.out.print("Inserisci il nome : ");
	    		 String name = scanner.nextLine();
	    		 System.out.print("Inserisci il cognome : ");
	    		 String surname = scanner.nextLine();
	    		 System.out.print("Inserisci l'email : ");
	    		 String email = scanner.nextLine();
	    		 System.out.print("Inserisci il numero di telefono : ");
	    		 String phone = scanner.nextLine();
	    		 System.out.print("Inserisci delle note : ");
	    		 String note = scanner.nextLine();
	    		 EntityTransaction transaction = entityManager.getTransaction();
	    		 transaction.begin();
	    		 Contact c = new Contact();
	    		 c.setName(name);
	    		 c.setSurname(surname);
	    		 c.setEmail(email);
	    		 c.setPhoneNumber(phone);
	    		 c.setNote(note);
	    		 System.out.println("Informazioni riguardo al nuovo contatto");
	    		 System.out.println(c);
	    		 System.out.print("Vuoi salvare il contatto? (si/no) : ");
	    		 String salva = scanner.nextLine();
	    		 if(salva.equals("si")) {
	    			 entityManager.persist(c);
	    			 transaction.commit();
	    			 System.out.println("Contatto salvato correttamente");
	    		 }
	    		 else System.out.println("Contatto non salvato");
	    		 
	    	 } catch(Exception e) {
	    		 System.out.println("Si è verificato un errore  : " + e.getMessage());
	    	 }
		}
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityManager entityManager = JPAmanagerFactory.createEntityManager();
		
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
