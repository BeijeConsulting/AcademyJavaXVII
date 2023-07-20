package it.beije.suormary.bookstore4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.service.EcommerceService;
 

@Controller
public class ListController {

	@Autowired
	private EcommerceService ecs;
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		List<Book> books = ecs.bookList();
		model.addAttribute("booklist", books);
		return "booklist"; //  /WEB-INF/views/booklist.jsp
	}
}
