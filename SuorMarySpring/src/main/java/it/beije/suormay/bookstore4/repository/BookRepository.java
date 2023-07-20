package it.beije.suormay.bookstore4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore4.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	//public List<Book> 
}
