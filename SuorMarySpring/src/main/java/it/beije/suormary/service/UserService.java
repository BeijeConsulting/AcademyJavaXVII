package it.beije.suormary.service;

import java.time.LocalDateTime;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.controller.JPAmanagerFactory;
import it.beije.suormary.model.User;
import it.beije.suormary.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	 public  void registerUser(String name, String surname, String email, String password, LocalDateTime date) { 
  		   User  user = new User();
  		   user.setEmail(email);
  		   user.setName(name);
  		   user.setSurname(surname);
  		   user.setCreationDate(date);
  		   user.setPassword(password);
  		   userRepository.save(user);
  	   
     }
     public  User loginUser(String email) {
    	User user = userRepository.findUserByEmail(email);
  	    return user;
     }

}
