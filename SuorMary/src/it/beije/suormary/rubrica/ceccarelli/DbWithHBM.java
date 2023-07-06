package it.beije.suormary.rubrica.ceccarelli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.suormary.rubrica.Contact;
import it.beije.suormary.rubrica.HBMsessionFactory;

public class DbWithHBM {
	
	public Configuration configuration;
	public SessionFactory factory;
	public Session session;
	public Transaction transaction;
	
//	// check connection
//	public Session connectionCheck() {
//		try {
////		//creazione configurazione
////			
////		configuration = new Configuration().configure(new File("./src/hibernate.cfg.xml"))
////				.addAnnotatedClass(Contact.class);
////		
////		//creazione sessione
////		factory = configuration.buildSessionFactory();
//		//inizializzazione sessione
//			
//			session = HBMsessionFactory.openSession().openSession();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return session;
//	}
	
	//list contact from DB
	public List<Contact> listContactHBM() {
		List<Contact> contacts = null;
			try {
				session = HBMsessionFactory.openSession();
				//transaction = session.beginTransaction();
				
				//SELECT HQL
				Query<Contact> query = session.createQuery("SELECT c from Contact as c");
				contacts = query.getResultList();
				
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		return contacts;
	}
	
	// search contact with name
	public List<Contact> searchContactsName(String name){
		List<Contact> selected= new ArrayList<Contact>();
			try {
				session = HBMsessionFactory.openSession();
				Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.name = :nome");
				query.setParameter("nome", name);
				selected = query.getResultList();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
		return selected;
	}
	
	// search contact with surnname
		public List<Contact> searchContactsSurname(String surname){
			List<Contact> selected= new ArrayList<Contact>();
			
				try {
					session = HBMsessionFactory.openSession();
					Query<Contact> query =session.createQuery("SELECT c from Contact as c WHERE c.surname = :cognome");
					query.setParameter("cognome", surname);
					selected = query.getResultList();
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					session.close();
				}
			return selected;
		}
		
	// search contact with name and surname
	public List<Contact> searchContactsNameSurname(String name, String surname){
				List<Contact> selected= new ArrayList<Contact>();
					try {
						session = HBMsessionFactory.openSession();
						Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.name = :nome and c.surname = :cognome");
						query.setParameter("nome", name);
						query.setParameter("cognome", surname);
						
						selected = query.getResultList();
					}catch (Exception e) {
						e.printStackTrace();
					} finally {
						session.close();
					}
				return selected;
			}
	
	//inserti contact
	public void insertContacts(Contact contact) {
			try {
				session = HBMsessionFactory.openSession();
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
//	
	//update contact
	public void updateContact(Contact contact) {
		System.out.println(contact.getId());
			try {
				session = HBMsessionFactory.openSession();
				transaction = session.beginTransaction();
				Query<Contact> query = session.createQuery("SELECT c from Contact as c WHERE c.id = :id");
				query.setParameter("id", contact.getId());
				List<Contact> selected= new ArrayList<Contact>();
				selected = query.getResultList();
				Contact c = selected.get(0);
				System.out.println("contact PRE UPDATE: " + c);
				c.setName(contact.getName());
				c.setPhoneNumber(contact.getPhoneNumber());;
				c.setSurname(contact.getSurname());
				c.setEmail(contact.getEmail());
				c.setNote(contact.getNote());
				System.out.println("contact POST UPDATE: " + c);
				
				session.save(c);
				transaction.commit();
				System.out.println("Contatto modificato");
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}
	}
	
	//delete contact
	public void deleteContact(Contact contact) {
			try {
				session = HBMsessionFactory.openSession();
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
	
	//find multiple contacts
	public List<Contact> findMultipleContact() {
		List<Contact> occ= new ArrayList<Contact>();
		try {
			session = HBMsessionFactory.openSession();
			Query<Contact> query = session.createQuery("SELECT COUNT(*) from Contact as c GROUP BY c.name, c.surname HAVING COUNT>1");
			occ = query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return occ;
	}
	
}
