package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

		author = authorService.createAuthor(author);

		return author;
	}
	@RequestMapping(value = "/listAuthors", method = RequestMethod.GET)
	public String listAuthors(Model model) {
		List<Author> authors = authorService.getAuthors();
		model.addAttribute("authors", authors);
		return "listAuthors";
	}
	@RequestMapping(value = "/updateAuthor", method = RequestMethod.GET)
	public String updateAuthorGet(@RequestParam String id, Model model) {
		Author author = authorService.getAuthorById(id);
		model.addAttribute("author", author);
		return "updateAuthor";
	}
	@RequestMapping(value = "/updateAuthor", method = RequestMethod.POST)
	public String updateAuthorPost(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String description = request.getParameter("description");
		authorService.updateAuthor(id,name, surname, description);
		List<Author> authors = authorService.getAuthors();
		model.addAttribute("authors", authors);
		return "listAuthors";
	}
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET)
	public String deleteAuthor(@RequestParam String id, Model model) {
		authorService.deleteAuthor(id);
		List<Author> authors = authorService.getAuthors();
		model.addAttribute("authors", authors);
		return "listAuthors";
	}

}
