package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.model.Author;
import it.beije.suormary.service.AuthorService;

@Controller
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value = "/createAuthor", method = RequestMethod.GET)
	public String createAuthorGet() {
		return "createAuthor";
	}
	@RequestMapping(value = "/createAuthor", method = RequestMethod.POST)
	public String createAuthorPost(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String description = request.getParameter("description");
		authorService.createAuthor(name, surname, description);
		List<Author> authors = authorService.getAuthors();
		model.addAttribute("authors", authors);
		return "listAuthors";
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
