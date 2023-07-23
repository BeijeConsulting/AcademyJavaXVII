package it.beije.suormary.bookstore4.controller;

import java.util.ArrayList;
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
	BasketItem bi = new BasketItem();
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(HttpSession session, Model model) {
		List<Book> books = ecommerceService.bookList();
		model.addAttribute("booklist", books);
		
		user = ecommerceService.findUser("alice.ceccarelli@gmail.com", "00000");
		
		
		//se user loggato fai vedere anche carrello
		if(user != null) {
			model.addAttribute("user", user);
			session.setAttribute("user", user);
			model.addAttribute("basket", user.getBasket());
			
			//prendo libri nel basket e trovo le caratteristiche corrispondenti di ognuno
			List<BasketItem>bi =( (List<BasketItem>) (model.getAttribute("basket")));
			List<Book> add = new ArrayList<>();
			for(BasketItem b : bi) {
				Book bb = ecommerceService.getBasketItemInBook(b.getBookId());
				System.out.println(bb);
				add.add(bb);
			}
			
			// aggiungo il risultato al model che lo mostra nella booklist.jsp
			model.addAttribute("basketJoinBook", add);
			model.addAttribute("sum", ecommerceService.sumBasket(user.getId()));
			
			
		} 
		
		return "booklist"; //  /WEB-INF/views/booklist.jsp
	}
	
	
	@RequestMapping(value = "/addtobasket", method = RequestMethod.POST)
	public String addToBasket(HttpSession session, Model model, @RequestParam("bookId") Integer bookId) {
		System.out.println("SONO NEL METODO");
		User sessionUser = ecommerceService.findUser("alice.ceccarelli@gmail.com", "00000");
		//System.out.println("BOOK ID: " + bookId);
		ecommerceService.addToBasket(bookId, sessionUser.getId());
		
		// trova il libro per modificare la quantità
		//Book book = ecommerceService.findById(bookId);
		
		return bookList(session, model);
	}
	
	
	@RequestMapping(value = "/removefrombasket", method = RequestMethod.POST)
	public String removefrombasket(HttpSession session, Model model, @RequestParam("bookId") Integer bookId) {
		System.out.println("STO RIMUOVENDO");
		User sessionUser = ecommerceService.findUser("alice.ceccarelli@gmail.com", "00000");
		ecommerceService.removeFromBasket(bookId, sessionUser.getId());
		
		// trova il libro per modificare la quantità
		//Book book = ecommerceService.findById(bookId);
		
		return bookList(session, model);
	}
}
