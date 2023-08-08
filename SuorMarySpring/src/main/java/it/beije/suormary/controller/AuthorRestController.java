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

import it.beije.suormary.model.Author;
import it.beije.suormary.service.AuthorService;


@RestController
@RequestMapping(value = "/api")
public class AuthorRestController {
		
	@Autowired
	private AuthorService authorService;
	
	@GetMapping(value = "/authors")
	public List<Author> getAuthors(){
		
		List<Author> authors = authorService.getAuthors();
		
		return authors;
	}
	
	@GetMapping(value = "/author/{id}")
	public Author getAuthor(@PathVariable Integer id) {
		System.out.println("GET author/" + id);
		return authorService.getAuthorById(id);
	}
	
	@GetMapping(value = "/authorname/{surname}")
	public Author getAuthor(@PathVariable String surname) {
		System.out.println("GET author/" + surname);
		return authorService.getAuthorBySurname(surname);
	}
	
	@PostMapping(value = "/author")
	public Author insertAuthor(@RequestBody Author author) {
		System.out.println("POST INSERT author/");
		authorService.addAuthor(author);
		
		return author;
	}
	
	@PutMapping(value = "/author/{id}")
	public Author updateAuthor(@PathVariable Integer id, @RequestBody Author author) {
		
		authorService.updateAuthor(author);
		
		return author;
	}
	
	@DeleteMapping(value = "/author/{id}")
	public Map<String,String> deleteAuthor(@PathVariable Integer id){
		
		Map<String,String> message = new HashMap<String,String>();
		
		try {
			authorService.deleteAuthor(id);
			message.put("message:", "Author deleted"); 
		} catch (Exception e) {
			message.put("message:", e.getMessage());
		}
		
		return message;
	}
	
}
