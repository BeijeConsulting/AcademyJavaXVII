package it.beije.suormary.bookstore1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore1.model.Author;
import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.model.User;
import it.beije.suormary.bookstore1.service.AuthorService;
import it.beije.suormary.bookstore1.service.BookServiceRest;

@RestController
@RequestMapping(value = "/personal")
public class BookRestController {
	@Autowired
	private BookServiceRest bookServiceRest;
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping(value="/author/{name}/{surname}")
	public List<Author> authorGet(@PathVariable String name, @PathVariable String surname){
		List<Author> author = authorService.searchAuthorByNameAndSurname(name, surname);
		return author;
	}
	
	@GetMapping(value="/author")
	public List<Author> authorGet(){
		List<Author> authors = authorService.searchAllAuthors();
		return authors;
	}
	
	@GetMapping(value="/author/{id}")
	public Author authorGet(@PathVariable Integer id) {
		return authorService.findById(id);
	}
	
	@PostMapping(value="/author")
	public Author authorPost(@RequestBody Author author) {
		if(author!=null) {
			return authorService.insertAuthor(author);
			
		}
		
		return null;
	}
	
	@PutMapping(value="/author/{id}")
	public Author authorPut(@PathVariable Integer id, @RequestBody Author author) {
		Author output=null;
		
		if(author!=null && id>0) {
			if(id.compareTo(author.getId()) == 0){
				output=authorService.updateAuthor(author);
				
			}
		} else throw new RuntimeException("id non valido");
		
	return output;
	}
	
	@DeleteMapping(value="/author/{id}")
	public Map<String, String> authorDelete(@PathVariable Integer id){
		Map<String, String> message = new HashMap<>();
		try {
			authorService.deleteAuthor(id);
			message.put("message", "cancellato con successo");
		}catch(Exception e) {
			message.put("message", "operazione non riuscita");
			e.printStackTrace();
		}
		return message;
	}

	
	//_________________________________________________________________________
	
	@GetMapping(value="/book")
	public List<Book> bookGet(){
		return bookServiceRest.findallBooks();
	}
	
	@GetMapping(value="/book/{id}")
	public List<Book> bookGetByAuthor(@PathVariable Integer id){
		return bookServiceRest.findallBooksByAuthor(id);
	}
	
	@PostMapping(value="/book")
	public Book bookPost(@RequestBody Book book) {
		if(book!=null) {
			return bookServiceRest.insertBook(book);
		}
		return null;
	}
	
	@PutMapping(value="/book/{id}")
	public Book bookPut(@PathVariable Integer id, @RequestBody Book book) {
		if(book!=null && id>0) {
			if(id.compareTo(book.getId()) == 0) {
				return bookServiceRest.upDateBook(book);
			}
		}
		return null;
	}
	
	@DeleteMapping(value="/book/{id}")
	public Map<String, String> bookDelete(@PathVariable Integer id){
		Map<String, String> message = new HashMap<>();
		try {
			bookServiceRest.deleteBook(id);
			message.put("message", "cancellato con successo");
		}catch(Exception e) {
			message.put("message", "operazione non riuscita");
			e.printStackTrace();
		}
		return message;
	}
}
