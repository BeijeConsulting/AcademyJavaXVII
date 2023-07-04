package it.beije.suormary.rubrica.ceccarelli;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.suormary.rubrica.Contact;

public class DbWithHBM {
	
	Configuration configuration;
	SessionFactory factory;
	Session session;
	Transaction transaction;
	// check connection
	public boolean connectionCheck() {
		boolean check = true;
		try {
		//creazione configurazione
			
		configuration = new Configuration().configure()
				.addAnnotatedClass(Contact.class);
		
		//creazione sessione
		factory = configuration.buildSessionFactory();
		//inizializzazione sessione
		session = null;
		
		}catch (Exception e) {
			check = false;
			e.printStackTrace();
		}
		return check;
	}
	
	//list contact from DB
	public List<Contact> listContactHBM() {
		List<Contact> contacts = null;
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				//transaction = session.beginTransaction();
				
				//SELECT HQL
				Query<Contact> query = session.createQuery("SELECT c from Contact as c");
				contacts = query.getResultList();
				
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return contacts;
	}
	
	// search contact with name
	public List<Contact> searchContactsName(String name){
		List<Contact> selected= new ArrayList<Contact>();
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.nome = :nome");
				query.setParameter("nome", name);
				selected = query.getResultList();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return selected;
	}
	
	// search contact with surnname
		public List<Contact> searchContactsSurname(String surname){
			List<Contact> selected= new ArrayList<Contact>();
			if(connectionCheck()) {
				try {
					session = factory.openSession();
					Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.cognome = :cognome");
					query.setParameter("cognome", surname);
					selected = query.getResultList();
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					session.close();
				}
			}
			return selected;
		}
		
	// search contact with name and surname
	public List<Contact> searchContactsNameSurname(String name, String surname){
				List<Contact> selected= new ArrayList<Contact>();
				if(connectionCheck()) {
					try {
						session = factory.openSession();
						Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.nome = :nome and c.cognome = :cognome");
						query.setParameter("nome", name);
						query.setParameter("cognome", surname);
						
						selected = query.getResultList();
					}catch (Exception e) {
						e.printStackTrace();
					} finally {
						session.close();
					}
				}
				return selected;
			}
	
	//inserti contact
	public void insertContacts(Contact contact) {
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				transaction = session.beginTransaction();
				session.save(contact);
				transaction.commit();
				System.out.println("Contatto/i inserito/i");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
//	//update contact
	public void updateContact(Contact contact) {
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				transaction = session.beginTransaction();
				session.save(contact);
				transaction.commit();
				System.out.println("Contatto modificato");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
	
	//delete contact
	public void deleteContact(Contact contact) {
		if(connectionCheck()) {
			try {
				session = factory.openSession();
				transaction = session.beginTransaction();
				session.delete(contact);
				transaction.commit();
				System.out.println("Contatto eliminato");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
}
