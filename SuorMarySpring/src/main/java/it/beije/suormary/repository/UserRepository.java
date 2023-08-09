package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
