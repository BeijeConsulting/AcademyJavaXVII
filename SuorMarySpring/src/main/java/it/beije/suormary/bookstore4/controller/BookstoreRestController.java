package it.beije.suormary.bookstore4.controller;

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

import it.beije.suormary.bookstore4.model.Author;
import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.model.Order;
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
	
	@GetMapping(value = "/all_authors")
	public List<Author> allauthors() {
		List<Author> authors = ecommerceService.authorsList();
		return authors;  
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
}