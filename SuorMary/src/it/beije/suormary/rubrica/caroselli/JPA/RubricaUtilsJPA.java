package it.beije.suormary.rubrica.caroselli.JPA;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;

import it.beije.suormary.rubrica.caroselli.Contact;
import it.beije.suormary.rubrica.caroselli.ScannerUtil;



public class RubricaUtilsJPA {

	public static List<Contact> readContactsFromDb() {

		List<Contact> newList = new ArrayList<>();
		EntityTransaction transaction = null;
		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();

		String choice = ScannerUtil
				.readStringValue("Vuoi cecare i contatti per nome o per cognome? Scegli 'si' o 'no'");
		if (choice.equalsIgnoreCase("si")) {

			String value = ScannerUtil.readStringValue(
					"Scrivi 'nome' per cercare i contatti per nome, altrimenti 'cognome' per cercare i contatti per cognome");
			orderContactsByNameOrSurname(value);

		} else {

			try {

				transaction = entityManager.getTransaction();
				transaction.begin();
				Query query = entityManager.createQuery("SELECT c FROM Contact as c");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts)
					System.out.println(c);

				transaction.commit(); // Commit the transaction if successful
			} catch (Exception e) {
				transaction.rollback(); // Rollback the transaction in case of an exception
				e.printStackTrace();
			} finally {
				entityManager.close(); // Close the entityManager
				persistenceManager.close(); // Close the PersistenceManager
			}

		}

		return newList;
	}

	public static List<Contact> orderContactsByNameOrSurname(String nameOrSurname) {

		List<Contact> result = new ArrayList<>();
		EntityTransaction transaction = null;

		PersistenceManagerJPA persistenceManager = PersistenceManagerJPA.getInstance();
		EntityManager entityManager = persistenceManager.getEntityManager();
		
		if (nameOrSurname.equals("nome")) {

			try {

				transaction = entityManager.getTransaction();
				transaction.begin();

				Query query = entityManager.createQuery("SELECT c FROM Contact as c ORDER BY name ASC");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts) {
					System.out.println(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
				persistenceManager.close();
			}

		} else if (nameOrSurname.equals("cognome")) {

			try {
				
				transaction = entityManager.getTransaction();
				transaction.begin();

				Query query = entityManager.createQuery("SELECT c FROM Contact as c ORDER BY surname ASC");
				List<Contact> contacts = query.getResultList();
				for (Contact c : contacts)
					System.out.println(c);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
				persistenceManager.close();
			}
		}

		return result;
	}

}
