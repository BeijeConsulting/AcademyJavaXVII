package it.beije.suormary.bookstore1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore1.model.User;

	@Repository
	public interface UserRepositoryRest extends JpaRepository<User, Integer>{
		
		public List<User> findByEmail(String email);
		
		public List<User> findByEmailAndPassword(String email, String password);
		
		
	}

