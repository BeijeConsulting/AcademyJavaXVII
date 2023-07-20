package it.beije.suormary.bookstore4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.bookstore4.model.BasketItem;
import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.model.User;
import it.beije.suormary.bookstore4.service.EcommerceService;
 

@Controller
public class ListController {

	@Autowired
	private EcommerceService ecs;
	
	User user = null;
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		List<Book> books = ecs.bookList();
		model.addAttribute("booklist", books);
		
		//User user = new User();
		 //se user loggato fai vedere anche carrello
		if(user != null) {
			Integer userId = user.getId();
			List<BasketItem> basket = ecs.basket(userId);
			model.addAttribute("basket", basket); 
		}
		
		return "booklist"; //  /WEB-INF/views/booklist.jsp
		
	}
	
}
