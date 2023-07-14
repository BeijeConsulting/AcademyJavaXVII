package it.beije.suormary.bookstore2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.Query;

import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.PersistenceManagerJPA;

public class BookstoreUtility {
	
	
	public static List<Book> readBooksFromDb() {

		List<Book> newList = new ArrayList<>();
		EntityTransaction transaction = null;
		EntityManager entityManager = null;

			try {

				entityManager = PersistenceManagerJPA.getEntityManager();
				transaction = entityManager.getTransaction();
				transaction.begin();
				Query query = entityManager.createQuery("SELECT b FROM Book as b");
				newList = query.getResultList();
				for (Book b : newList) {
					System.out.println(b.toString());
				
				}

				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} finally {
				entityManager.close();
			}


		return newList;
	}
	
	

}
