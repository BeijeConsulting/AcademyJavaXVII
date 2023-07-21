package it.beije.suormary.bookstore4.service;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore4.model.BasketItem;
import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.model.User;
import it.beije.suormary.bookstore4.repository.BasketItemRepository;
import it.beije.suormary.bookstore4.repository.BookRepository;
import it.beije.suormary.bookstore4.repository.UserRepository;


@Service
public class EcommerceService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BasketItemRepository basketItemRepository;
	
	//prende lista di tutti i libri
	@Transactional
	public List<Book> bookList() {
    	List<Book> books = bookRepository.findAll();
//    	for(Book b : books) {
//    		System.out.println(b);
//    	}
    	if (books.size() == 0) return null;
    	return books;
	}
	
	
	//prende tutti libri nel carrello di un utente
	public List<BasketItem> basket(Integer userId) {
    	List<BasketItem> books = basketItemRepository.findByUserId(userId);
    	if (books.size() == 0) return null;
    	return books;
	}
	
	
	//Cerca utente con email e password
	public User findUser(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password);
		return user;
	}
	
	//Aggiunge un libro al carrello
	public void addToBasket(Integer bookId, Integer userId) {
		Optional<Book> book = bookRepository.findById(bookId);
		BasketItem basketItem = new BasketItem();
		basketItem.setBookId(bookId);
		basketItem.setUserId(userId);
		basketItem.setQuantity(1);
		
		basketItemRepository.save(basketItem);
	}
	
	//join tra basket e book
	public Book getBasketItemInBook(Integer basketId){
		Book b =  bookRepository.findBybookId(basketId);
		System.out.println("VALORE DI B: " + b) ;
		return b;
	}
	
	//somma del carrello
	public Double sumBasket(Integer userId) {
		return basketItemRepository.sumBasket(userId);
	}
	
}
