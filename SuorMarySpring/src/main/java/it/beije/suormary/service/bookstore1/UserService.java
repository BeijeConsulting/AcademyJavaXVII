package it.beije.suormary.service.bookstore1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import it.beije.suormary.bin.bookstore1.User;

@Service
public class UserService {

	public User checkUser(String email, String password) {
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
	
	public boolean userExists(String email) {
		EntityManager entityManager = null;
		List<User> user = new ArrayList<>();
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.email = :email");
			query.setParameter("email", email);
			user = (List<User>) query.getResultList();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
		if(user.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public int getUserId(String email) {
		
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
	
	public void createUser(User user) {
		EntityManager entityManager = null;
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			
			transaction.begin();
			
			entityManager.persist(user);
			
			transaction.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			entityManager.close();
		}
	}
	
}
