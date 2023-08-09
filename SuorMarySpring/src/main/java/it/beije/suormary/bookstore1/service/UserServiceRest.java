package it.beije.suormary.bookstore1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import it.beije.suormary.bookstore1.model.User;
import it.beije.suormary.bookstore1.repository.UserRepositoryRest;

@Service
public class UserServiceRest {
	
	@Autowired
	private UserRepositoryRest urr;
	
	public User checkUser(String email, String password) {
		List<User> users = urr.findByEmailAndPassword(email, password);
		if(users.isEmpty()) {
			return null;
		}else {
			return users.get(0);
		}
	}

	public void insertUser(User u) {
		urr.save(u);
	}
	
	public User updateUser(User u) {
		
			Optional<User> optional = urr.findById(u.getId());
			if(optional.isPresent()) {
				User copy = optional.get();
				//u è la source, copy è il target
				BeanUtils.copyProperties(u, copy);
				insertUser(copy);
				return copy;
			}
			return null;
	}
	
	public void deleteUser(Integer id) {
		urr.deleteById(id);
	}
	
}
