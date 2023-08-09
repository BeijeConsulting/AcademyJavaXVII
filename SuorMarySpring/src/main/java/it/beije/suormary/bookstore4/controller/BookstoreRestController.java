package it.beije.suormary.bookstore4.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore4.dto.AuthCredentials;
import it.beije.suormary.bookstore4.dto.PaymentDetails;
import it.beije.suormary.bookstore4.model.Author;
import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.model.Order;
import it.beije.suormary.bookstore4.model.OrderItem;
import it.beije.suormary.bookstore4.model.User;
import it.beije.suormary.bookstore4.service.EcommerceService;
import it.beije.suormary.model.Contact;

//@Controller
@RestController
@RequestMapping(value = "/api")
public class BookstoreRestController {

	@Autowired
	private EcommerceService ecommerceService;
	
	//lista libri nel magazzino
	@GetMapping(value = "/all_books")
	public List<Book> allBooks() {
		List<Book> books = ecommerceService.bookList();
		return books;
	}
	
	@PutMapping(value = "/book/{id}")
	public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
		if (id.compareTo(book.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");
		ecommerceService.updateBook(id, book.getPrice(), book.getQuantity());
		return book;
	}
	
	@PostMapping(value = "/book")
	public Book insertBook(@RequestBody Book book) {
		ecommerceService.insertBook(book.getTitle(), book.getDescription(), book.getAuthorId(), book.getEditor(), book.getPrice(), book.getQuantity());
		return book;
	}
	
	@GetMapping(value = "/all_authors")
	public List<Author> allauthors() {
		List<Author> authors = ecommerceService.authorsList();
		return authors;  
	}
	
	@PutMapping(value = "/author/{id}")
	public Author updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
		if (id.compareTo(author.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");
		ecommerceService.updateAuthor(id, author.getDescription());
		return author;
	}
	
	@PostMapping(value = "/author")
	public Author insertAuthor(@RequestBody Author author) {
		ecommerceService.insertAuthor(author.getName(), author.getSurname(), author.getDescription());
		return author;
	}
	
	@DeleteMapping(value="/empty_basket/{userId}")
	public String emptyBasket(@PathVariable Integer userId) {
		ecommerceService.emptyBasket(userId);
		return "Basket is empty now";
	}
	
	@GetMapping(value = "/my_orders/{userId}")
	public List<Order> myOrders(@PathVariable Integer userId) {
		List<Order> orders = ecommerceService.getOrders(userId);
		return orders;
	}
	
	@PutMapping(value = "/order/{id}")
	public Order updateOrder(@PathVariable Integer id) {
		ecommerceService.deleteOrder(id);
		return ecommerceService.getOrder(id);
	}
	
	@PostMapping(value = "/buy/{userId}")
	public Order buyBasket(@PathVariable Integer userId, @RequestBody PaymentDetails paymentDetails) {
		ecommerceService.buyBasket(userId, paymentDetails.getShippingAddress(), paymentDetails.getPayment());
		List<Order> orders = ecommerceService.getOrders(userId);
		return orders.get(orders.size() - 1);
	}
	
	@GetMapping(value = "/basket/{userId}")
	public HashMap<Book, Integer> addToBasket(@PathVariable Integer userId) {
		return ecommerceService.basket(userId);
	}
	
	@PostMapping(value = "/add_to_basket/{userId}")
	public HashMap<Book, Integer> addToBasket(@PathVariable Integer userId, @RequestBody Integer bookId) {
		ecommerceService.addToBasket(bookId, userId);
		return ecommerceService.basket(userId);
	}
	
	@DeleteMapping(value = "/remove_from_basket/{userId}")
	public HashMap<Book, Integer> removeFromBasket(@PathVariable Integer userId, @RequestBody Integer bookId) {
		ecommerceService.removeFromBasket(bookId, userId);
		return ecommerceService.basket(userId);
	}
	
	@PostMapping(value = "/login")
	public String login(@RequestBody AuthCredentials auth) {
		System.out.println(auth.toString());
		boolean logged = ecommerceService.findUser(auth.getEmail(), auth.getPassword()) != null;
		return logged ? "Logged in" : "Wrong username or password";
	}
	
	@GetMapping(value = "/user/{id}")
	public User user(@PathVariable Integer id) {
		User user = ecommerceService.findUserById(id);
		return user;  
	}
	
	@PostMapping(value = "/user")
	public String newUser(@RequestBody User user) {
		System.out.println(user);
		boolean newUser = ecommerceService.addUser(user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
		return newUser ? "Welcome" : "Something went wrong...try again";
	}
	
	
}