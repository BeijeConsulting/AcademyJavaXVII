package it.beije.suormary.service;

import java.time.LocalDateTime;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import it.beije.suormary.controller.JPAmanagerFactory;
import it.beije.suormary.model.User;
@Service
public class UserService {
	 public  void registerUser(String name, String surname, String email, String password, LocalDateTime date) { 
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   try {
  		   EntityTransaction transaction = entityManager.getTransaction();
  		   transaction.begin();
  		   User user = new User();
  		   user.setEmail(email);
  		   user.setName(name);
  		   user.setSurname(surname);
  		   user.setCreationDate(date);
  		   user.setPassword(password);
  		   entityManager.persist(user);
  		   transaction.commit();
  		   	   
  	   } catch(Exception e) {
  		   e.printStackTrace();
  	   } finally {
  		   entityManager.close();
  	   }
  	   
     }
     public  User loginUser(String email) {
  	   EntityManager entityManager = JPAmanagerFactory.createEntityManager();
  	   User user = null;
  	   try {
  		   Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.email = :email");
  		   query.setParameter("email", email);
  		  // query.setParameter("password", password);
  		    user = (User) query.getSingleResult();
  		   
  	   }catch(Exception e) {
  		   e.printStackTrace();
  		   
  	   } finally {
  		   entityManager.close();
  	   }
  	   return user;
     }

}
