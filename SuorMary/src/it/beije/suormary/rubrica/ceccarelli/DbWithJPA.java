package it.beije.suormary.rubrica.ceccarelli;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.rubrica.Contact;

public class DbWithJPA {
	
	public EntityManager entityManager;
	
	public List<Contact> listContactJPA() {
		List<Contact> contacts = null;
		try {
//			EntityTransaction transaction = JPAEntityFactory.openEntity().getTransaction();
//			transaction.begin();
			entityManager = JPAEntityFactory.openEntity();
			Query query = entityManager.createQuery("SELECT c from Contact as c"); //SELECT * FROM rubrica
			contacts = query.getResultList();
		}catch(Exception e) {
			e.fillInStackTrace();
		}finally {
			entityManager.close();
		}
		return contacts;
	}
	
	public List<Contact> searchContactNameJPA(String name) {
		List<Contact> selected= new ArrayList<Contact>();
		try {
//			EntityTransaction transaction = JPAEntityFactory.openEntity().getTransaction();
//			transaction.begin();
			entityManager = JPAEntityFactory.openEntity();
			Query query = entityManager.createQuery("SELECT c from Contact as c WHERE c.name = :nome"); //SELECT * FROM rubrica
			query.setParameter("nome", name);
			selected = query.getResultList();
		}catch(Exception e) {
			e.fillInStackTrace();
		}finally {
			entityManager.close();
		}
		return selected;
	}
	
	public List<Contact> searchContactSurnameJPA(String surname) {
		List<Contact> selected= new ArrayList<Contact>();
		try {
//			EntityTransaction transaction = JPAEntityFactory.openEntity().getTransaction();
//			transaction.begin();
			entityManager = JPAEntityFactory.openEntity();
			Query query = entityManager.createQuery("SELECT c from Contact as c WHERE c.name = :cognome"); //SELECT * FROM rubrica
			query.setParameter("cognome", surname);
			selected = query.getResultList();
		}catch(Exception e) {
			e.fillInStackTrace();
		}finally {
			entityManager.close();
		}
		return selected;
	}
	
	// search contact with name and surname
	public List<Contact> searchContactsNameSurname(String name, String surname){
		List<Contact> selected= new ArrayList<Contact>();
			try {
				entityManager = JPAEntityFactory.openEntity();
				Query query = entityManager.createQuery("SELECT c from Contact as c WHERE c.name = :nome and c.surname = :cognome");
				query.setParameter("nome", name);
				query.setParameter("cognome", surname);
				
				selected = query.getResultList();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
			}
		return selected;
	}
	
	//insert contact
	public void insertContacts(Contact contact) {
		try {
			entityManager = JPAEntityFactory.openEntity();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(contact);
			transaction.commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	//update contact
	public void updateContacts(Contact contact) {
		Contact contactM = null;
		try {
			entityManager = JPAEntityFactory.openEntity();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			contactM = JPAEntityFactory.openEntity().find(Contact.class, contact.getId());
			contactM.setName(contact.getName());
			contactM.setSurname(contact.getSurname());
			contactM.setEmail(contact.getEmail());
			contactM.setPhoneNumber(contact.getPhoneNumber());
			contactM.setNote(contact.getNote());
			entityManager.persist(contactM);
			transaction.commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	//delete contact
	public void deleteContacts(Contact contact) {
		Contact c = null;
		try {
			entityManager = JPAEntityFactory.openEntity();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			c = entityManager.find(Contact.class, contact.getId());
			entityManager.remove(c);
			transaction.commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	//find multiple contacts
	public void findMultipleContacts() {
		
	}
}
