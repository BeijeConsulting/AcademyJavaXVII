package it.beije.suormary.controller.bookstore1;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bin.bookstore1.Book;
import it.beije.suormary.bin.bookstore1.Cart;
import it.beije.suormary.dumpster.bookstore1.BookUtils;

@Controller
public class ShopController {
	
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shopGet(HttpSession session) {
		
		List<Book> books = BookUtils.getAllBooks();
		session.setAttribute("books", books);
		return "shop";
		
	}
	
	@RequestMapping(value = "/shop", method = RequestMethod.POST)
	public String shopPost(HttpSession session, @RequestParam String bookId, @RequestParam String quantity) {
		int bookIdInt = Integer.valueOf(bookId);
		int quantityInt = Integer.valueOf(quantity);
		Map<Integer,Integer> cart = Cart.getCart(session);
		
		if(cart.containsKey(bookId)) {
			int newQuantity = cart.get(bookIdInt) + quantityInt;
			cart.replace(bookIdInt, newQuantity);
		}else {
			cart.put(bookIdInt, quantityInt);
		}
		
		session.setAttribute("cart", cart);
		
		//System.out.println(session.getAttribute("cart"));
		return "shop";
	}
}
