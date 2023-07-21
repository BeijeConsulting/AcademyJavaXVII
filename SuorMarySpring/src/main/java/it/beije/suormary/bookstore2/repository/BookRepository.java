package it.beije.suormary.bookstore2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore2.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	public Book save(Book book);
}
