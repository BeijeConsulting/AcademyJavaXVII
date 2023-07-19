package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.service.BookService;


@Controller
public class WelcomeController {
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(HttpSession session, Model model) {
		if(session.getAttribute("email") == null) {
			return "login";
		}
		else{
			List<Book> books = bookService.loadBooks();
				  model.addAttribute("books", books);		
				  return "welcome";
		  
		}
	}

}
