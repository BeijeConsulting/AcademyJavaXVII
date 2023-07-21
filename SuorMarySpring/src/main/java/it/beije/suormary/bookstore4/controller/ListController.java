package it.beije.suormary.bookstore4.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String bookList(HttpSession session, Model model) {
		List<Book> books = ecommerceService.bookList();
		model.addAttribute("booklist", books);
		
		user = ecommerceService.findUser("alice.ceccarelli@gmail.com", "00000");
//		user.setName("Alice");
		 //se user loggato fai vedere anche carrello
		if(user.getName() != null) {
			model.addAttribute("user", user);
			session.setAttribute("user", user);
//			Integer userId = user.getId();
//			List<BasketItem> basket = ecommerceService.basket(userId);
			model.addAttribute("basket", user.getBasket()); 
		} 
		
		return "booklist"; //  /WEB-INF/views/booklist.jsp
	}
	
	@RequestMapping(value = "/addtobasket", method = RequestMethod.POST)
	public String addToBasket(Model model, 
			@RequestParam(name = "bookId", required = true) Integer bookIdString) {
		//Integer bookid = Integer.parseInt(bookIdString);
		User sessionUser = ecommerceService.findUser("alice.ceccarelli@gmail.com", "00000");
		ecommerceService.addToBasket(bookIdString, sessionUser.getId());
		return "booklist";
	}
			
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String 
	
	
	
}
