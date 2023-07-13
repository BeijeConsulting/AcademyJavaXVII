package it.beije.suormary.web.rubrica.trapani;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

//		COMPLETA E FUNZIONANTE

public class DBthroughHBM {
	
	static Configuration configuration;
	
	static Scanner in = new Scanner(System.in);
	
	public static boolean connection() {

		try {
			configuration = new Configuration().configure()
			.addAnnotatedClass(Contact.class);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static List<Contact> listContacts() {
		List<Contact> contacts = null;
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();

			
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
			contacts = query.getResultList();
			session.close();
		} else {
			System.out.println("errore connesione");
		}
		
		return contacts;
		
	}
	
	public static List<Contact> findContacts() {
		List<Contact> contacts = null;
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();

			
			Query query = session.createQuery("SELECT c from Contact as c WHERE c.name LIKE :nome OR c.surname LIKE :cognome "
					+ "OR c.phoneNumber LIKE :telefono OR c.email LIKE :email OR c.note LIKE :note");
			
			System.out.println("Inserisci valore da cercare: ");
			String search = "%" + in.nextLine() + "%";
			
			query.setParameter("nome", search);
			query.setParameter("cognome", search);
			query.setParameter("telefono", search);
			query.setParameter("email", search);
			query.setParameter("note", search);

			contacts = query.getResultList();
			
			for (Contact c : contacts) 
				System.out.println(c);
			session.close();
		} else {
			System.out.println("errore connesione");
		}
		return contacts;
		
	}
	
	public static void insertContact() {
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		Contact contact = new Contact();

		System.out.println("Nome: ");
		contact.setName(in.nextLine());
		System.out.println("Cognome: ");
		contact.setSurname(in.nextLine());
		System.out.println("Telefono: ");
		contact.setPhoneNumber(in.nextLine());
		System.out.println("Email: ");
		contact.setEmail(in.nextLine());
		System.out.println("Note: ");
		contact.setNote(in.nextLine());
		
		session.save(contact);
		
		transaction.commit();
		session.close();
		} else {
			System.out.println("errore connesione");
		}
		
	}
	
	public static void updateContact() {
		List<Contact> contatti = null;
		Contact contact = null;
		
		if(connection()) {
			
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
			
			System.out.println("Visualizzare elenco contatti salvati prima? (y/n)");
			if(in.nextLine().equals("y")) {
				
				Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
				contatti = query.getResultList();
				
				for (Contact s : contatti) {
					System.out.println(s);
				}
			}
			
			System.out.println("Inserire Id contatto da aggiornare:");
			int idagg=in.nextInt(); 
		
			Transaction transaction = session.beginTransaction();
			for (Contact s : contatti) {
				if(s.getId()== idagg) {
					contact = s;
				}
			}
			System.out.println("Nome: ");
			contact.setName(in.next());
			System.out.println("Cognome: ");
			contact.setSurname(in.next());
			System.out.println("Telefono: ");
			contact.setPhoneNumber(in.next());
			System.out.println("Email: ");
			contact.setEmail(in.next());
			System.out.println("Note: ");
			contact.setNote(in.next());
			
			session.save(contact);
			
			transaction.commit();
			session.close();
			} else {
				System.out.println("errore connesione");
			}
		}
	
	public static void deleteContact() {
				
		List<Contact> contatti = null;
		Contact contact = null;
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
			
			System.out.println("Visualizzare elenco contatti salvati prima? (y/n)");
			if(in.nextLine().equals("y")) {
				
				Query<Contact> query = session.createQuery("SELECT c FROM Contact as c"); //SELECT * FROM rubrica
				contatti = query.getResultList();
				
				for (Contact s : contatti) {
					System.out.println(s);
				}
			}
			
			System.out.println("Inserire Id contatto da aggiornare:");
			int idagg=in.nextInt(); 
		
			Transaction transaction = session.beginTransaction();
			for (Contact s : contatti) {
				if(s.getId()== idagg) {
					contact = s;
				}
			}
			
			session.delete(contact);
			
			transaction.commit();
			session.close();
			
		} else {
			System.out.println("errore connesione");
		}
		
	}
		
	public static List<Contact> findDuplicate() {
		List<Contact> contacts = null;
		//Contact contact = null;
		List<Contact> duplicates = null;
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
				
				Query<Contact> query = session.createQuery("SELECT name,surname,phoneNumber,email, COUNT(*) FROM Contact "
						+ "GROUP BY name,surname,phoneNumber,email HAVING COUNT(*) > 1"); 
				
				duplicates = query.getResultList();
				
				
				if(duplicates.size()==0) {
					System.out.println("Nessun contatto duplicato");
					return null;
				}
			
			
			session.close();
			
		} else {
			System.out.println("errore connesione");
		}
		return duplicates;
	}
	
	public static void mergeDuplicate() {
		List<Contact> contacts = null;
		Contact contact =  new Contact();
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
				
			contacts = findDuplicate();

			for(int i=0; i<contacts.size(); i++) {
				for(int j=i+1; j<contacts.size(); j++) {
					if(contacts.get(i).toString().equals(contacts.get(j).toString())) {
						contact = contacts.get(j);
						session.delete(contact);
						transaction.commit();
					}
							
			if(contacts.size()==0) {
				System.out.println("Nessun contatto duplicato da eliminare");
				return;
					}		
				}
			}
		session.close();
			
		} else 
			System.out.println("errore connesione");
	}	
	
	public static List<Contact> findContacts(String search) {
		List<Contact> contacts = null;
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();

			
			Query query = session.createQuery("SELECT c from Contact as c WHERE c.name LIKE :nome OR c.surname LIKE :cognome "
					+ "OR c.phoneNumber LIKE :telefono OR c.email LIKE :email OR c.note LIKE :note");
						
			query.setParameter("nome", search);
			query.setParameter("cognome", search);
			query.setParameter("telefono", search);
			query.setParameter("email", search);
			query.setParameter("note", search);

			contacts = query.getResultList();
			session.close();
			
		} else {
			System.out.println("errore connesione");
		}
		return contacts;
		
	}
	
	public static void insertContact(Contact contact) {
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(contact);
		
		transaction.commit();
		session.close();
		} else {
			System.out.println("errore connesione");
		}
		
	}

	public static boolean deleteContact(int id) {
		
		List<Contact> contatti = null;
		Contact contact = null;
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			Query<Contact> query = session.createQuery("SELECT c FROM Contact as c WHERE c.id LIKE :id"); //SELECT * FROM rubrica
			query.setParameter("id", id);
			contact = query.getSingleResult();
		
		
		session.delete(contact);
		
		
		transaction.commit();
		session.close();
			
		return true;
		} else {
			System.out.println("errore connesione");
			return false;
		}
		
	}

	public static Contact getContactFromId(int id) {
		
		
		return contact
	}
}
