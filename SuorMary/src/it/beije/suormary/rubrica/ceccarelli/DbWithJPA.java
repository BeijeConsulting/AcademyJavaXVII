package it.beije.suormary.rubrica.ceccarelli;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import it.beije.suormary.rubrica.Contact;

public class DbWithJPA {
	
	public EntityManager entityManager;
	
	public List<Contact> listContactJPA() {
		List<Contact> contacts = null;
		try {
//			EntityTransaction transaction = JPAEntityFactory.openEntity().getTransaction();
//			transaction.begin();
			entityManager = JPAEntityFactory.openEntity();
//			Query query = entityManager.createQuery("SELECT c from Contact as c"); //SELECT * FROM rubrica
//			contacts = query.getResultList();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Contact> query = cb.createQuery(Contact.class);
			Root<Contact> root = query.from(Contact.class);
			contacts = entityManager.createQuery(query.select(root)).getResultList();
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
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Contact> query = cb.createQuery(Contact.class);
			Root<Contact> root = query.from(Contact.class);
			selected = entityManager.createQuery(query.select(root).where(cb.equal(root.get("name"),name))).getResultList();
			//Query query = entityManager.createQuery("SELECT c from Contact as c WHERE c.name = :nome"); //SELECT * FROM rubrica
			//query.setParameter("nome", name);
			//selected = query.getResultList();
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
			entityManager = JPAEntityFactory.openEntity();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Contact> query = cb.createQuery(Contact.class);
			Root<Contact> root = query.from(Contact.class);
			selected = entityManager.createQuery(query.select(root).where(cb.equal(root.get("surname"),surname))).getResultList();
//			Query query = entityManager.createQuery("SELECT c from Contact as c WHERE c.name = :cognome"); //SELECT * FROM rubrica
//			query.setParameter("cognome", surname);
//			selected = query.getResultList();
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
				entityManager = JPAEntityFactory.openEntity();
				CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<Contact> query = cb.createQuery(Contact.class);
				Root<Contact> root = query.from(Contact.class);
				Predicate condition1 = cb.equal(root.get("name"), name);
				Predicate condition2 = cb.equal(root.get("surname"), surname);
				selected = entityManager.createQuery(query.select(root).where(cb.and(condition1,condition2))).getResultList();
//				Query query = entityManager.createQuery("SELECT c from Contact as c WHERE c.name = :nome and c.surname = :cognome");
//				query.setParameter("nome", name);
//				query.setParameter("cognome", surname);
				
				//selected = query.getResultList();
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
	public List<Contact> findMultipleContacts() {
		List<Contact> occ = null;
		try {
			entityManager = JPAEntityFactory.openEntity();
//			EntityTransaction transaction = entityManager.getTransaction();
//			transaction.begin();
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Contact> query = cb.createQuery(Contact.class);
			Root<Contact> root = query.from(Contact.class);
			query.select(root);
			
			//definizione raggruppamento
			query.groupBy(root.get("name"), root.get("surname"), root.get("phoneNumber"), root.get("email"), root.get("note"));
			
			//condizione per contatti duplicati
			query.having(cb.gt(cb.count(root),1));
			
			//disabilita indice
//			String disableIndexQuery = "ALTER TABLE rubrica DISABLE KEYS";
//			entityManager.createNativeQuery(disableIndexQuery).executeUpdate();
			
			//query duplicati;
			occ = entityManager.createQuery(query).getResultList();
			
			//abilita indice

//			String enableIndexQuery = "ALTER TABLE rubrica ENABLE KEYS";
//			entityManager.createNativeQuery(enableIndexQuery).executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			entityManager.close();
		}
		return occ;
	}
}
