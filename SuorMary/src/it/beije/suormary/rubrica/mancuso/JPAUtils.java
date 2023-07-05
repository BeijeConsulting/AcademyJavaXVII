package it.beije.suormary.rubrica.mancuso;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAUtils {
	
	public static EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SuorMary");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
	
	public static List<Object> selectColumn(String column, String value) {
		EntityManager entityManager = null;
		List<Object> contacts = null;
		try {
			entityManager = getEntityManager();
			
			Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c." + column + " = :value ");
			query.setParameter("value", value);
			
			contacts = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return contacts;
	}
	
	public static List<Object> getAllContacts() {
		EntityManager entityManager = null;
		List<Object> contacts = null;
		try {
			entityManager = getEntityManager();
			
			Query query = entityManager.createQuery("SELECT c FROM Contact as c ");
			
			contacts = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return contacts;
	}
	
	// c must have been previously read by the db
	public static List<Object> editContact(Contact c, String name, String surname, String phone, String email, String notes) {
		EntityManager entityManager = null;
		List<Object> contacts = null;
		try {
			entityManager = getEntityManager();
			
			c.setFirstName(name);
			c.setLastName(surname);
			c.setEmail(email);
			c.setPhoneNumber(phone);
			c.setNotes(notes);
			
			entityManager.persist(c);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return contacts;
	}
	
}
