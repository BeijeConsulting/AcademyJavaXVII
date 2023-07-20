package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.model.Book;
import it.beije.suormary.service.AuthorService;
import it.beije.suormary.service.BookService;

@Controller
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	@Autowired
	private BookService bookService;
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
		List<Book> books = bookService.loadBooks();
		model.addAttribute("books", books);
		return "welcome";
	}

}
