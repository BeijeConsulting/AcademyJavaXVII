package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>{

}
