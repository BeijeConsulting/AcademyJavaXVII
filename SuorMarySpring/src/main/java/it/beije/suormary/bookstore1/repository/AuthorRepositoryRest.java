package it.beije.suormary.bookstore1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore1.model.Author;

@Repository
public interface AuthorRepositoryRest extends JpaRepository<Author, Integer>{
	
}


