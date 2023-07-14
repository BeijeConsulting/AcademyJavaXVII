package it.beije.suormary.bookstore1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserUtils {
	
	public static User checkUser(String email, String password) {
		EntityManager entityManager = null;
		User user=null;
		
		try {
			entityManager = JPAManagerFactory.getEntityManager();
			Query query = entityManager.createQuery("SELECT u FROM Users as u WHERE u.email = :email AND u.password"
					+ "= :password ");
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
	
}

