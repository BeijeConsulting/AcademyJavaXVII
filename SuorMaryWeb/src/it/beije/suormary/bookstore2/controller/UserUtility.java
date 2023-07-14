package it.beije.suormary.bookstore2.controller;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import it.beije.suormary.bookstore2.model.PersistenceManagerJPA;
import it.beije.suormary.bookstore2.model.User;

public class UserUtility {
	
	public static void insertUser(User user) {
		EntityManager entityManager = null;
		
		try {
			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			User newUser = new User();
			
			if(user!=null) {
				try {
					newUser.setEmail(user.getEmail());
					newUser.setPassword(user.getPassword());
					newUser.setSurname(user.getSurname());
					newUser.setName(user.getName());
					newUser.setCreationDate(LocalDateTime.now());
					entityManager.persist(newUser); // salva l'user nel database
					transaction.commit();
				} catch (Exception e) {
					System.out.println("Insert non valido: " + user.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 
					
			} else {
				System.out.println("User mancante");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
