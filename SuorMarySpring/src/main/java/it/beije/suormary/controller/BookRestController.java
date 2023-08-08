package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import it.beije.suormary.model.Author;
import it.beije.suormary.model.Book;
import it.beije.suormary.service.AuthorService;
import it.beije.suormary.service.BookService;

@RestController
@RequestMapping(value = "/api")
public class BookRestController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value = "/books")
	public List<Book> books() {
		System.out.println("GET /api/books " );
		
		List<Book> books = bookService.loadBooks();

		return books;
	}
	
	@GetMapping(value = "/bookid/{id}")
	public Book getBookFromId(@PathVariable Integer id) {
		System.out.println("GET /api/book/" + id);
		Book book = bookService.getBookById(id);
		return book;
	}
	
	@GetMapping(value = "/book/{title}")
	public Book getBooks(@PathVariable String title) {
		System.out.println("GET /api/book/" + title);
		Book book = bookService.getBookByTitle(title);
		return book;
	}
	
	@PostMapping(value = "/book")
	public Book insertBook(@RequestBody Book book) {
		System.out.println("POST /api/book : " + book);
		
		bookService.addBook(book);
		
		return book;
	}
	
	@PutMapping(value = "/book/{id}")
	public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
		System.out.println("PUT /api/book/ " + id + " : " + book);
		
		if (id.compareTo(book.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");
		
		return bookService.updateBook(book);
	}
	
	@DeleteMapping(value = "/book/{id}")
	public Map<String,String> deleteBook(@PathVariable Integer id){
		System.out.println("DELETE /book/" + id );
		Map<String, String> message = new HashMap<String, String>();
		
		try {
			bookService.deleteBook(id);
			message.put("message:", "book deleted");
			
		} catch (Exception e) {
			message.put("message:", e.getMessage());
		}
		
		return message;
	}
	
}
