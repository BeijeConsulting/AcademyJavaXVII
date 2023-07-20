package dumpster.bookstore1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.suormary.bookstore1.model.User;
import it.beije.suormary.bookstore1.service.JPAManagerFactory;

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
	
	public static void createUser(String email, String password, String name, String surname) {
		EntityManager entityManager = null;
		User user = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			user = new User(email,password,name,surname);
			
			entityManager.persist(user);
			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
	}
	
}

