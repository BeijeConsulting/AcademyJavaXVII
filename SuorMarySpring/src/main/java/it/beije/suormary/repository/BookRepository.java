package it.beije.suormary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	public List<Book> findByAuthorId(Integer authorId);
}
