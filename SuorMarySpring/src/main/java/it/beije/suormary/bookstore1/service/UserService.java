package it.beije.suormary.bookstore1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore1.model.User;
import it.beije.suormary.bookstore1.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User checkUser(String email, String password) {
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		if(users.isEmpty()) {
			return null;
		}else {
			return users.get(0);
		}
	}
	
	public boolean userExists(String email) {
		List<User> users = userRepository.findByEmail(email);
		if(users.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	public Integer getUserId(String email) {
		try {
			List<User> users = userRepository.findByEmail(email);
			if(users.isEmpty()) {
				throw new Exception("Non esiste un utente associato a questa email");
			}else {
				return users.get(0).getId();
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void createUser(User user) {
		user.setCreationDate(LocalDateTime.now());
		userRepository.save(user);
		
	}
	
}
