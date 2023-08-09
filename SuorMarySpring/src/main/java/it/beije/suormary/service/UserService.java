package it.beije.suormary.service;

import java.time.LocalDateTime;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.controller.JPAmanagerFactory;
import it.beije.suormary.dto.UserDTO;
import it.beije.suormary.model.User;
import it.beije.suormary.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	 public  User registerUser(UserDTO userDTO) { 
  		   User  user = new User();
  		   user.setEmail(userDTO.getEmail());
  		   user.setName(userDTO.getName());
  		   user.setSurname(userDTO.getSurname());
  		   user.setCreationDate(LocalDateTime.now());
  		   user.setPassword(userDTO.getPassword());
  		   return userRepository.save(user);
  	   
     }
     public  User loginUser(UserDTO userDTO) {
    	User user = userRepository.findUserByEmailAndPassword(userDTO.getEmail(),userDTO.getPassword());
  	    return user;
     
	 }

     public  User loginUser(String email) {
     	User user = userRepository.findUserByEmail(email);
   	    return user;
      }

}
