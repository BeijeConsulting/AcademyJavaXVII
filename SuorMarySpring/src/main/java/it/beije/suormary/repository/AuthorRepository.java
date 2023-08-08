package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.suormary.model.Author;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

	public Author findAuthorBySurname(String surname);

}
