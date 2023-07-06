package it.beije.suormary.rubrica.mancuso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class JPAUtils {
	
	public static List<Contact> selectColumn(String column, String value) {
		EntityManager entityManager = null;
		List<Contact> contacts = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			
			Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c." + column + " LIKE CONCAT('%', :value,'%')");
			query.setParameter("value", value);
			
			contacts = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
		return contacts;
	}
	
	public static List<Contact> getAllContacts() {
		EntityManager entityManager = null;
		List<Contact> contacts = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			
			Query query = entityManager.createQuery("SELECT c FROM Contact as c ");
			
			contacts = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
		return contacts;
	}
	
	// c must have been previously read by the db
	public static void editContact(Contact c, String name, String surname, String phone, String email, String notes) {
		EntityManager entityManager = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			c.setFirstName(name);
			c.setLastName(surname);
			c.setEmail(email);
			c.setPhoneNumber(phone);
			c.setNotes(notes);
			
			entityManager.persist(c);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
	}
	
	public static void addContact(Contact c) {
		EntityManager entityManager = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();	
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(c);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
	}
	
	public static void deleteContact(Contact c) {
		EntityManager entityManager = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();	
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(c);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
	}
	
	public static Contact getContact(int id) {
		EntityManager entityManager = null;
		Contact contact = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			
			contact = entityManager.find(Contact.class, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//entityManager.close();
		}
		return contact;
	}
	
}
