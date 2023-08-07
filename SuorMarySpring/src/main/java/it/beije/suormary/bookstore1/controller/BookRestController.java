package it.beije.suormary.bookstore1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.service.BookService;

@RestController
@RequestMapping(value = "/api")
public class BookRestController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping(value = "/new_book")
	public Book insertBook(@RequestBody Book book) {
		System.out.println("Book POST");
		bookService.addNewBook(book);
		return book;
	}
	
	@GetMapping(value = "/book/{id}")
	public Book selectBook(@PathVariable Integer id) {
		System.out.println("Book GET");
		Book book = bookService.getBookById(id);
		return book;
	}
	
	@PutMapping(value = "/book/{id}")
	public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
		System.out.println("Book PUT");
		Book newBook = bookService.updateBook(id, book);
		return newBook;
	}
	
	@DeleteMapping(value = "/book/{id}")
	public String deleteBook(@PathVariable Integer id) {
		System.out.println("Book DELETE");
		try {
			bookService.deleteBook(id);
			return "book deleted";
		} catch (Exception e) {
			return "error while deleting book";
		}
	}
	
}
