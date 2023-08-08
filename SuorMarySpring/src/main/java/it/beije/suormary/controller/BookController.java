package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.model.Book;
import it.beije.suormary.service.BookService;



@RestController
@RequestMapping(value = "/api")
public class BookController {

	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value="/books")
	public List<Book> listBook() {
		return bookService.getAllBooks();
	}
	
	
	@GetMapping(value="/books/{id}")
	public Book book(@PathVariable Integer id) {
		return bookService.getBook(id);
	}
	
	@PostMapping(value ="/books")
	public Book insertBook(@RequestBody Book book) {
		return bookService.insertBook(book);
	}
	
	@DeleteMapping(value="/books/{id}") 
	public Map<String, String> deleteBook(@PathVariable Integer id) {
		Map<String, String> message = new HashMap<String, String>();

		try {
			bookService.deleteBook(id);
			message.put("message", "libro rimosso correttamente");
		} catch (Exception e) {
			message.put("message", e.getMessage());
		}		
		
		return message;
	}
	
	@PutMapping(value = "/books/{id}")
	public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
		if (id.compareTo(book.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");
		return bookService.updateBook(book);
	}
}
