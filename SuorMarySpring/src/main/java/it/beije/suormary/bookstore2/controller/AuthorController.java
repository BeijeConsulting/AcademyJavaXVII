package it.beije.suormary.bookstore2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.service.AuthorService;

@Controller
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value = "/bookstore_insert_author", method = RequestMethod.GET)
	public String insertAuthor() {

	    return "bookstore_insert_author";
	}
	
	@RequestMapping(value = "/bookstore_insert_author", method = RequestMethod.POST)
	public String insertAuthor(HttpSession session, Model model,
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "surname", required = true) String surname,
			@RequestParam(name = "description", required = false) String description) {
	
		Author author = new Author();	
		author.setName(name);
		author.setSurname(surname);
		author.setDescription(description);
		
		authorService.save(author);
		
		return "redirect:bookstore_welcome";
	}
}
