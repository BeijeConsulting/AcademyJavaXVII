package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.suormary.model.Book;

public interface BookRepository extends JpaRepository<Book,Integer>  {

	public Book findBookByTitle(String title);

	
}
