package it.beije.suormary.rubrica.Char;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;

public class RubricaHBM {
	public static void loadRubricaHBM() {
	 	 Configuration configuration = new Configuration().configure().addAnnotatedClass(Contact.class);
    	 SessionFactory sessionFactory = configuration.buildSessionFactory();
    	 Session session = null;
    	 try {
    		 session = sessionFactory.openSession();
    		 Query<Contact> query = session.createQuery("SELECT c FROM Contact as c");
    		 List<Contact> listContacts = query.getResultList();
    		 for(Contact c : listContacts) {
    			 System.out.println(c);
    		 }
    		 
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 } finally {
    		 session.close();
    		 sessionFactory.close();
    	 }
	}
	public static Contact findContactByNameSurname() throws Exception {
		 Scanner scanner = new Scanner(System.in);
		 Configuration configuration = new Configuration().configure().addAnnotatedClass(Contact.class);
    	 SessionFactory sessionFactory = configuration.buildSessionFactory();
    	 Session session = null;
    	 List<Contact> listContacts = null;
    	 try {
    		 System.out.print("Inserisci il nome : ");
    		 String name = scanner.nextLine();
    		 System.out.print("Inserisci il cognome : ");
    		 String surname = scanner.nextLine();
    		 session = sessionFactory.openSession();
    		 Query<Contact> query = session.createQuery("SELECT c FROM Contact as c WHERE c.name = :name AND c.surname = :surname");
    		 query.setParameter("name", name);
    		 query.setParameter("surname", surname);
    		 listContacts = query.getResultList();
    		 
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 } finally {
    		 session.close();
    		 sessionFactory.close();
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
	public static void findContact() {
		try {
			Contact c = findContactByNameSurname();
			System.out.println("Informazioni riguardo al contatto selezionato...");
			System.out.println(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void createContact() {
		 Scanner scanner = new Scanner(System.in);
		 Configuration configuration = new Configuration().configure().addAnnotatedClass(Contact.class);
    	 SessionFactory sessionFactory = configuration.buildSessionFactory();
    	 Session session = null;
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
    		 session = sessionFactory.openSession();
    		 Transaction transaction = session.beginTransaction();
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
    			 session.save(c);
    			 transaction.commit();
    			 System.out.println("Contatto salvato correttamente");
    		 }
    		 else System.out.println("Contatto non salvato");
    		 
    	 } catch(Exception e) {
    		 e.printStackTrace();
    	 } finally {
    		 session.close();
    		 sessionFactory.close();
    	 }
	}

     
}
