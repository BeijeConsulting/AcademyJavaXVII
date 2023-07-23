package it.beije.suormary.bookstore4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore4.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

//	public User findById(Integer id);
//	
//	public List<User> findByName(String name);
//	
//	public List<User> findBySurname(String surname);
	
	public List<User> findByEmailAndPassword(String email, String password);

}
