package it.beije.suormary.web.mancuso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
//import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

public class JPAUtils {
	
	public static List<Contact> multiColumn(List<String> columns, List<String> values){
		EntityManager entityManager = null;
		List<Contact> contacts = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			
			//Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c." + column + " LIKE CONCAT('%', :value,'%')");
			//query.setParameter("value", value);
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
			Root<Contact> rootContact = cq.from(Contact.class);
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			Predicate condition = null;
			
			for(int i=0; i<columns.size();i++) {
				
				condition = cb.like(rootContact.get(
						columns.get(i)), 
						cb.parameter(String.class, "likeCondition"+i)
						);
				
				predicates.add(condition);
			}
			cq.select(rootContact).where( predicates.toArray( new Predicate[ predicates.size() ] ));
			
			TypedQuery<Contact> typedQuery = entityManager.createQuery(cq);
			
			for(int i=0; i<columns.size();i++) {
				typedQuery.setParameter("likeCondition"+i, "%"+ values.get(i) +"%");
			}
			
			contacts = typedQuery.getResultList();
			
			//contacts = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return contacts;
	}
	
	public static List<Contact> selectColumn(String column, String value) {
		EntityManager entityManager = null;
		List<Contact> contacts = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			
			//Query query = entityManager.createQuery("SELECT c FROM Contact as c WHERE c." + column + " LIKE CONCAT('%', :value,'%')");
			//query.setParameter("value", value);
			
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			
			CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);
			Root<Contact> rootContact = cq.from(Contact.class);
			
			cq.select(rootContact).where(cb.equal(rootContact.get(column), value));
			
			TypedQuery<Contact> typedQuery = entityManager.createQuery(cq);
			contacts = typedQuery.getResultList();
			
			//contacts = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
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
			
			for(int i=0; i<contacts.size(); i++) {
				List<ContactDetail> cds = new ArrayList<ContactDetail>();
				query = entityManager.createQuery("SELECT cd FROM ContactDetail AS cd WHERE id_contact = :contact");
				query.setParameter("contact", contacts.get(i).getId());
				
				cds = query.getResultList();
				contacts.get(i).setDetail(cds);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return contacts;
	}
	
	// c must have been previously read by the db
	public static void editContact(int id, String name, String surname, String notes) {
		EntityManager entityManager = null;
		Contact c = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			c = entityManager.find(Contact.class, id);		
			
			EntityTransaction transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			c.setFirstName(name);
			c.setLastName(surname);
			//c.setEmail(email);
			//c.setPhoneNumber(phone);
			c.setNotes(notes);

			entityManager.persist(c);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
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
			entityManager.close();
		}
	}
	
	public static void deleteContact(int id) {
		EntityManager entityManager = null;
		Contact contact = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			contact = entityManager.find(Contact.class, id);		

			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.remove(contact);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
	public static Contact getContact(int id) {
		EntityManager entityManager = null;
		Contact contact = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
	
			contact = entityManager.find(Contact.class, id);		

			List<ContactDetail> cds = new ArrayList<ContactDetail>();
			Query query = entityManager.createQuery("SELECT cd FROM ContactDetail AS cd WHERE id_contact = :contact");
			query.setParameter("contact", contact.getId());
			
			cds = query.getResultList();
			contact.setDetail(cds);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return contact;
	}
	
	public static ContactDetail getContactDetail(int id) {
		EntityManager entityManager = null;
		ContactDetail contactDetail = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
	
			contactDetail = entityManager.find(ContactDetail.class, id);		

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		return contactDetail;
	}
	
	public static void editContactDetail(int idCd, String label, String detail, String type) {
		EntityManager entityManager = null;
		ContactDetail contactDetail = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			
			contactDetail = entityManager.find(ContactDetail.class, idCd);	
			
			EntityTransaction transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			contactDetail.setLabel(label);
			contactDetail.setDetail(detail);
			contactDetail.setType(type.charAt(0));

			entityManager.persist(contactDetail);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
	}
	
}
