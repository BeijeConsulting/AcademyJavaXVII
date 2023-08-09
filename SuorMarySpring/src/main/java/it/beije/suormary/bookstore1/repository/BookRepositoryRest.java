package it.beije.suormary.bookstore1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore1.model.Author;
import it.beije.suormary.bookstore1.model.Book;

	@Repository
	public interface BookRepositoryRest extends JpaRepository<Book, Integer>{
		
		public List<Book> findAllBooksByAuthor(Author a);
		
	}

