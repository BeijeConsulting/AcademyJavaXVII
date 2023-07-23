package it.beije.suormary.bookstore4.service;
import java.util.HashMap;
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
	public HashMap<Book, Integer> basket(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		
		HashMap<Book, Integer> basket = user.get().getBasket();
    	List<BasketItem> items = basketItemRepository.findByUserId(userId);
    	if (items.size() == 0) return null;
    	else {
    		for(BasketItem bi : items) {
    			Optional<Book> book = bookRepository.findById(bi.getBookId());
    			book.ifPresent(b -> basket.put(b, bi.getQuantity()));
    		} 
    	}
    	return basket;
	}
	
	
	//Cerca utente con email e password
	public User findUser(String email, String password) {
		System.out.println("Sono in find user");
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		System.out.println("User id : " + users.get(0).getId());
		return users.get(0);
	}
	
	//Aggiunge un libro al carrello
	public void addToBasket(Integer bookId, Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		HashMap<Book, Integer> basket = user.get().getBasket();
		
		//Controllo se il libro è già nel carrello
		List<BasketItem> items = basketItemRepository.findByBookIdAndUserId(bookId, userId);
		
		BasketItem bi;
		Book book = bookRepository.findById(bookId).get();
		int quantity;
		int newQuantity;
		//Se c'è allora aggiorno la quantità nel carrello e nel db
		if (items.size() == 0) {			
			bi = items.get(0);
			quantity = bi.getQuantity();
			//controllo che la quantità nel carrello non sia maggiore di quella nel magazzino
			newQuantity = (quantity < book.getQuantity()) ? (quantity + 1) : quantity; 
		}
		//altrimenti creo un nuovo oggetto da mettere nel carrello e nel db
		else {
			bi = new BasketItem();
			basket.put(book, 1);
			bi.setBookId(bookId);
			bi.setUserId(userId);
			newQuantity = (book.getQuantity() > 0) ? 1 : 0;
		}
		
		basket.put(book, newQuantity);
		bi.setQuantity(newQuantity);
		basketItemRepository.save(bi);

	}
	
	
	
	//join tra basket e book
	public Book getBasketItemInBook(Integer basketId){
		Book b =  bookRepository.findBybookId(basketId);
		System.out.println("VALORE DI B: " + b) ;
		return b;
	}
	
	//Rimuovi un libro dal carrello
	public void removeFromBasket(Integer bookId, Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		HashMap<Book, Integer> basket = user.get().getBasket();
		
		//Carico il carrello
		List<BasketItem> items = basketItemRepository.findByBookIdAndUserId(bookId, userId);
		
		BasketItem bi = items.get(0);
		Book book = bookRepository.findById(bi.getBookId()).get();
		int actualQuantity = bi.getQuantity();
		
		//aggiorna se la quantità è maggiore di 1
		if(actualQuantity > 1) {
			int newQuantity = actualQuantity - 1;
			basket.put(book, newQuantity);
			bi.setQuantity(newQuantity);
			basketItemRepository.save(bi);
		}
		//altrimenti elimina l'oggetto
		else {
			basket.remove(book);
			basketItemRepository.delete(bi);
		}
	}
	
	
	
	//somma del carrello
	public Double sumBasket(Integer userId) {

		return basketItemRepository.sumBasket(userId);
	}
	
}
