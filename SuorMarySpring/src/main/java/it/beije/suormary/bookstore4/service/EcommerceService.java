package it.beije.suormary.bookstore4.service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore4.model.Author;
import it.beije.suormary.bookstore4.model.BasketItem;
import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.model.Order;
import it.beije.suormary.bookstore4.model.OrderItem;
import it.beije.suormary.bookstore4.model.User;
import it.beije.suormary.bookstore4.repository.AuthorRepository;
import it.beije.suormary.bookstore4.repository.BasketItemRepository;
import it.beije.suormary.bookstore4.repository.BookRepository;
import it.beije.suormary.bookstore4.repository.OrderItemRepository;
import it.beije.suormary.bookstore4.repository.OrderRepository;
import it.beije.suormary.bookstore4.repository.UserRepository;


@Service
public class EcommerceService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BasketItemRepository basketItemRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
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
	
	//prende tutti gli autori
	@Transactional
	public List<Author> authorsList(){
		List<Author> authors = authorRepository.findAll();
		if (authors.size() == 0) return null;
    	return authors;
	}
	
	//prende tutti libri nel carrello di un utente
	public HashMap<Book, Integer> basket(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		
		//HashMap<Book, Integer> basket = (user.get().getBasket() == null) ? (new HashMap<Book, Integer>()) : (user.get().getBasket()) ;
		/*if (basket == null)*/ HashMap<Book, Integer> basket = new HashMap<Book, Integer>();
		
    	List<BasketItem> items = basketItemRepository.findByUserId(userId);
    	if (items.size() != 0) {
    		for(BasketItem bi : items) {
    			Optional<Book> book = bookRepository.findById(bi.getBookId());
    			book.ifPresent(b -> basket.put(b, bi.getQuantity()));
    		} 
    	}
    	
    	//System.out.println(basket);
    	user.get().setBasket(basket);
    	return basket;
	}
	
	
	//Cerca utente con email e password
	public User findUser(String email, String password) {
		System.out.println("Sono in find user");
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		if (users.size() == 0) return null;
		System.out.println("User id : " + users.get(0).getId());
		return users.get(0);
	}
	
	//aggiungi utente
	public boolean addUser(String name, String surname, String email, String password) {
		List<User> users = userRepository.findByEmail(email);
		
		if (users.size() == 0) {
			User user = new User();
			user.setName(name);
			user.setSurname(surname);
			user.setEmail(email);
			user.setPassword(password);
			userRepository.save(user);
			return true;
		}
		else return false;
	}
	
	//Aggiunge un libro al carrello
	public void addToBasket(Integer bookId, Integer userId) {
		HashMap<Book, Integer> basket = basket(userId);
		//System.out.println(basket);
		
		//Controllo se il libro è già nel carrello
		
		List<BasketItem> items = basketItemRepository.findByBookIdAndUserId(bookId, userId);
		System.out.println(items);
		
		BasketItem bi;
		Book book = bookRepository.findById(bookId).get();
		int quantity;
		int newQuantity;
		//Se c'è allora aggiorno la quantità nel carrello e nel db
		if (items.size() != 0) {			
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
	
	
	//Rimuovi un libro dal carrello
	public void removeFromBasket(Integer bookId, Integer userId) {
		HashMap<Book, Integer> basket = basket(userId);
		
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
	public double sumBasket(Integer userId) {
		Double sum = basketItemRepository.sumBasket(userId);
		return (sum == null) ? 0.0 : sum;
	}
	
	@Transactional
	public void buyBasket(Integer userId, String address, String typePayment) {
		
		Order order = new Order();
		order.setUserId(userId);
		order.setShippingAddress(address);
		order.setStatus(typePayment.equalsIgnoreCase("cash") ? "P" : "I");
		order.setDate(LocalDateTime.now());
		order.setAmount(sumBasket(userId));
		orderRepository.save(order);
		
		Integer orderId = order.getId();
		HashMap<Book, Integer> basket = basket(userId);
		
		for(HashMap.Entry<Book, Integer> set : basket.entrySet()) {
			OrderItem oi = new OrderItem();
            oi.setOrderId(orderId);
            
            int bookId = set.getKey().getId();
            oi.setBookId(bookId);
            
            int bookQuantity = set.getValue();
            oi.setQuantity(bookQuantity);
            
            double price = set.getKey().getPrice();
            oi.setPrice(price);
            
            orderItemRepository.save(oi);
            
          //aggiornare la quantità nel magazzino
            Book book = bookRepository.findBybookId(set.getKey().getId());
            int newQ = book.getQuantity() - bookQuantity;
            book.setQuantity(newQ);
            bookRepository.save(book);
		}
		
		//Svuotare il carrello sia nel db che come variabile
        emptyBasket(userId);
		List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
		order.setItems(items);
	}
	
	public void emptyBasket(Integer userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.get().getBasket() == null) {
			System.out.println("é vuoto");
		}else {
		System.out.println("non é vuoto");
		user.get().getBasket().clear();
		}
		basketItemRepository.deleteByUserId(userId);
	}
	
	@Transactional
	public List<Order> getOrders(Integer userId){
		List<Order> orders = orderRepository.findByUserId(userId);
		for (Order order : orders) {
			Integer orderId = order.getId();
			List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
			order.setItems(items);
		}
		return orders;
	}
}
