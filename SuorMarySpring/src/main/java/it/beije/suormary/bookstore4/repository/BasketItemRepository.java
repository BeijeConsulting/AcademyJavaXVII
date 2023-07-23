package it.beije.suormary.bookstore4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.suormary.bookstore4.model.BasketItem;
import it.beije.suormary.bookstore4.model.Book;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Integer>{

	public List<BasketItem> findByUserId(Integer userId);
	
	public List<BasketItem> findByBookId(Integer bookId);
	
	// metodo per la somma nel carrello
	@Query(value = "SELECT SUM(book.price*bi.quantity) FROM books as book JOIN baskets as bi ON book.id = bi.book_id WHERE bi.user_id = :userId", nativeQuery = true)
	public Double sumBasket(@Param("userId") Integer userId);

	public List<BasketItem> findByBookIdAndUserId(Integer bookId, Integer userId);
}