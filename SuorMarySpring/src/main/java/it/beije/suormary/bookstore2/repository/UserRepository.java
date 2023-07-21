package it.beije.suormary.bookstore2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore2.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmailAndPassword(String email, String password);
	
	//save permette l'inserimento nel DB, metodo ereditato da JpaRepository
	public User save(User user);
	
}
