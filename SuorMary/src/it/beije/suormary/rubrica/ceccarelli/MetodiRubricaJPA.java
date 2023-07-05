package it.beije.suormary.rubrica.ceccarelli;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.rubrica.Contact;

public class MetodiRubricaJPA {
	
	public List<Contact> listContactJPA() {
		List<Contact> contacts = null;
		try {
//			EntityTransaction transaction = JPAEntityFactory.openEntity().getTransaction();
//			transaction.begin();
			Query query = JPAEntityFactory.openEntity().createQuery("SELECT c from Contact as c"); //SELECT * FROM rubrica
			contacts = query.getResultList();
		}catch(Exception e) {
			e.fillInStackTrace();
		}
		return contacts;
	}
	
	public List<Contact> searchContactNameJPA(String name) {
		List<Contact> selected= new ArrayList<Contact>();
		try {
//			EntityTransaction transaction = JPAEntityFactory.openEntity().getTransaction();
//			transaction.begin();
			Query query = JPAEntityFactory.openEntity().createQuery("SELECT c from Contact as c WHERE c.name = :nome"); //SELECT * FROM rubrica
			query.setParameter("nome", name);
			selected = query.getResultList();
		}catch(Exception e) {
			e.fillInStackTrace();
		}finally {
			JPAEntityFactory.openEntity().close();
		}
		return selected;
	}
	
	public List<Contact> searchContactSurnameJPA(String surname) {
		List<Contact> selected= new ArrayList<Contact>();
		try {
//			EntityTransaction transaction = JPAEntityFactory.openEntity().getTransaction();
//			transaction.begin();
			Query query = JPAEntityFactory.openEntity().createQuery("SELECT c from Contact as c WHERE c.name = :cognome"); //SELECT * FROM rubrica
			query.setParameter("cognome", surname);
			selected = query.getResultList();
		}catch(Exception e) {
			e.fillInStackTrace();
		}finally {
			JPAEntityFactory.openEntity().close();
		}
		return selected;
	}
	
	// search contact with name and surname
	public List<Contact> searchContactsNameSurname(String name, String surname){
		List<Contact> selected= new ArrayList<Contact>();
			try {
				Query query = JPAEntityFactory.openEntity().createQuery("SELECT c from Contact as c WHERE c.name = :nome and c.surname = :cognome");
				query.setParameter("nome", name);
				query.setParameter("cognome", surname);
				
				selected = query.getResultList();
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				JPAEntityFactory.openEntity().close();
			}
		return selected;
	}
	
	
	
}
