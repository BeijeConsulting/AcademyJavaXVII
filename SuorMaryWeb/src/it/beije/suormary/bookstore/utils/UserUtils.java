package it.beije.suormary.bookstore.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.bookstore.entities.JPAManagerFactory;
import it.beije.suormary.bookstore.entities.User;

public class UserUtils {
	
	public static User checkUser(String email, String password) {
		EntityManager entityManager = null;
		User user=null;
		
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.email = :email AND u.password = :password ");
			query.setParameter("email", email);
			query.setParameter("password", password);
			user = (User) query.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
		return user;
	}
	
	public static boolean userExists(String email) {
		EntityManager entityManager = null;
		User user = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.email = :email");
			query.setParameter("email", email);
			user = (User) query.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
		if(user == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public static int getUserId(String email) {
		
		EntityManager entityManager = null;
		int id = -1;
		
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT u.id FROM User as u WHERE u.email = :email ");
			query.setParameter("email", email);
			id = (Integer) query.getSingleResult();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
		return id;
		
	}
	
	public static boolean createUser(String email, String password, String name, String surname) {
		EntityManager entityManager = null;
		User user = null;
		EntityTransaction transaction = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			user = new User(email,password,name,surname);
			
			entityManager.persist(user);
			
			transaction.commit();
			
			return true;
			
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			
			e.printStackTrace();
			return false;
		}finally {
			entityManager.close();
		}
	}
	
}

