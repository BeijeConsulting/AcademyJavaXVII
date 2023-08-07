package it.beije.suormary.bookstore4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore4.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
