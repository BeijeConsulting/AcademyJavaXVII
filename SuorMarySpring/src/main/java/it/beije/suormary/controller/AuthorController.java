package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookService bookService;
	@GetMapping(value = "/createAuthor")
	public String createAuthorGet() {
		return "createAuthor";
	}
	@PostMapping(value = "/createAuthor")
	public Author createAuthorPost(@RequestBody Author author) {

		return authorService.createAuthor(author);

	}
	@GetMapping(value = "/listAuthors")
	public List<Author> listAuthors() {
		return authorService.getAuthors();
	}
	@GetMapping(value = "/updateAuthor/{id}")
	public Author updateAuthorGet(@PathVariable Integer id) {
		return authorService.getAuthorById(id);
	}
	@PutMapping(value = "/updateAuthor/{id}")
	public Author updateAuthorPost(@PathVariable Integer id, @RequestBody Author author) {
		return authorService.updateAuthor(id,author);
	}
	@DeleteMapping(value = "/deleteAuthor/{id}")
	public void deleteAuthor(@PathVariable Integer id) {
		 authorService.deleteAuthor(id);
	}

}
