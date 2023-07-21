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
	private EcommerceService ecommerceService;
	
	User user = new User();
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		List<Book> books = ecommerceService.bookList();
		model.addAttribute("booklist", books);
		
		user.setName("Alice");
		 //se user loggato fai vedere anche carrello
		if(user.getName() != null) {
			model.addAttribute("user", user);
//			Integer userId = user.getId();
//			List<BasketItem> basket = ecommerceService.basket(userId);
			model.addAttribute("basket", user.getBasket()); 
		} 
		
		return "booklist"; //  /WEB-INF/views/booklist.jsp
		
	}
	
}
