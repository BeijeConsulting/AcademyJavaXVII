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
	 public  User registerUser(User userReq) { 
  		   User  user = new User();
  		   user.setEmail(userReq.getEmail());
  		   user.setName(userReq.getName());
  		   user.setSurname(userReq.getSurname());
  		   user.setCreationDate(LocalDateTime.now());
  		   user.setPassword(userReq.getPassword());
  		   return userRepository.save(user);
  	   
     }
     public  User loginUser(User userReq) {
    	User user = userRepository.findUserByEmailAndPassword(userReq.getEmail(),userReq.getPassword());
  	    return user;
     }
     public  User loginUser(String email) {
     	User user = userRepository.findUserByEmail(email);
   	    return user;
      }

}
