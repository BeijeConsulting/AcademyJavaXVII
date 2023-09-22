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

import it.beije.suormary.bookstore1.model.Author;
import it.beije.suormary.bookstore1.service.AuthorService;

@RestController
@RequestMapping(value = "/api")
public class AuthorRestController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping(value = "/author/{id}")
	public Author selectAuthor(@PathVariable Integer id) {
		System.out.println("Author GET");
		Author author = authorService.findById(id);
		return author;
	}
	
	@DeleteMapping(value = "/author/{id}")
	public String deleteBook(@PathVariable Integer id) {
		System.out.println("Author DELETE");
		try {
			authorService.deleteAuthor(id);
			return "author deleted";
		} catch (Exception e) {
			return "error while deleting author";
		}
	}
	
	@PostMapping(value = "/new_author")
	public Author insertAuthor(@RequestBody Author author) {
		System.out.println("Author POST");
		authorService.addAuthor(author);
		return author;
	}
	
	@PutMapping(value = "edit_author/{id}")
	public Author updateAuthor(@RequestBody Author author) {
		System.out.println("Author PUT");
		return authorService.updateAuthor(author.getId(), author);
	}
	
	
}