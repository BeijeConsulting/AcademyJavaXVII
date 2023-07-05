package it.beije.suormary.rubrica.trapani;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.suormary.rubrica.Contact;

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
		List<Contact> contatti = listContacts();
		Contact contact = null;
		
		System.out.println("Visualizzare elenco contatti salvati prima? (y/n)");
		if(in.nextLine().equals("y")) {
			
			for (Contact s : contatti) {
				System.out.println(s);
			}
		}
		
		System.out.println("Inserire Id contatto da aggiornare:");
		int idagg=in.nextInt(); 
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
		
			Transaction transaction = session.beginTransaction();
			for (Contact s : contatti) {
				if(s.getId()== idagg) {
					contact = s;
				}
			}
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
	
	public static void deleteContact() {
		
		System.out.println("Visualizzare elenco contatti salvati prima? (y/n)");
		if(in.nextLine().equals("y")) {
			List<Contact> contatti =listContacts();
			for (Contact s : contatti) {
				System.out.println(s);
			}
		}
		
		System.out.println("Inserire Id contatto da eliminare:");
		int idcanc=in.nextInt(); 
		
		
		if(connection()) {
			SessionFactory factory = configuration.buildSessionFactory();
			
			Session session = factory.openSession();
			Transaction transaction = session.beginTransaction();
			
			
			Contact contact = listContacts().get(idcanc);
			
			session.delete(contact);
			
			transaction.commit();
			session.close();
			
		} else {
			System.out.println("errore connesione");
		}
		
	}
		
	
	
}
