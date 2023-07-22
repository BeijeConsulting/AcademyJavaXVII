package it.beije.suormary.bookstore4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore4.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	//trovo caratteristiche del libro in baskets nella tabella "books"
		@Query(value = "SELECT * FROM books as book JOIN baskets as b ON b.book_id = book.id WHERE b.book_id = :basketId", nativeQuery = true)
		public Book findBybookId(@Param("basketId") Integer basketId);
		
}
