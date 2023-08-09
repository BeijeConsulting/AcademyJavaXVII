package it.beije.suormary.bookstore2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.service.AuthorService;

@RestController
@RequestMapping(value = "/api")
public class BookstoreRestController {
	
	@Autowired
	private AuthorService authorService;
	
	@GetMapping(value = "/author/{id}")
	public Author getAuthor(@PathVariable Integer id) {
		return authorService.getAuthorById(id);
	}
	
	@GetMapping(value = "/authors")
	public List<Author> authors() {
		return authorService.getAllAuthors();
	}
	
	@PostMapping(value = "/author")
	public Author insertAuthor(@RequestBody Author author) {
		authorService.save(author);
		
		return author;
	}

}
