package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.model.Book;
import it.beije.suormary.service.BookService;
import it.beije.suormary.service.TestService;


@RestController
@RequestMapping(value = "/api")
public class BookController {
	
//	@Autowired
//	private TestService testService;
//	
//	@RequestMapping(value = "/mybooks", method = RequestMethod.GET)
//	public String mybooks(Model model) {
//		System.out.println("GET /mybooks");
//
////		TestService testService = new TestService();
//		System.out.println("testService : " + testService.hashCode());
//		List<String> books = testService.getBooks();
//		
//		model.addAttribute("libri", books);
//		
//		return "mybooks";
//	}
	
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
}
