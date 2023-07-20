package it.beije.suormary.bookstore1.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dumpster.bookstore1.BookUtils;
import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.model.Cart;
import it.beije.suormary.bookstore1.service.BookService;

@Controller
public class ShopController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String shopGet(Model model) {
		
		List<Book> books = bookService.getAllBooks();
		System.out.println(books);
		model.addAttribute("books", books);
		return "shop";
		
	}
	
	@RequestMapping(value = "/shop", method = RequestMethod.POST)
	public String shopPost(HttpSession session, @RequestParam String bookId, @RequestParam String quantity, Model model) {
		int bookIdInt = Integer.valueOf(bookId);
		int quantityInt = Integer.valueOf(quantity);
		Map<Integer,Integer> cart = Cart.getCart(session);
		
		if(cart.containsKey(bookIdInt)) {
			int newQuantity = cart.get(bookIdInt) + quantityInt;
			cart.replace(bookIdInt, newQuantity);
		}else {
			cart.put(bookIdInt, quantityInt);
		}
		
		session.setAttribute("cart", cart);
		
		List<Book> books = bookService.getAllBooks();
		System.out.println(books);
		model.addAttribute("books", books);
		return "shop";
	}
	
	
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String cartGet(Model model, HttpSession session) {
		
		Map<Integer,Integer> cart = Cart.getCart(session);
		
		Map<Book,Integer> books = bookService.getBooks(cart);
		model.addAttribute("books", books);
		return "cart";
		
	}
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public String cartPost(Model model, HttpSession session, @RequestParam String bookId, @RequestParam String quantity) {
		
		Map<Integer,Integer> cart = Cart.getCart(session);
		
		int bookIdInt = Integer.valueOf(bookId);
		int quantityInt = Integer.valueOf(quantity);
		
		int newQuantity = cart.get(bookIdInt) - quantityInt;
		
		if(newQuantity > 0) {	
			cart.replace(bookIdInt, newQuantity);
		}else {	
			cart.remove(bookIdInt);
		}
		
		model.addAttribute("cart", cart);

		Map<Book,Integer> books = bookService.getBooks(cart);
		model.addAttribute("books", books);
		return "cart";
		
	}
	
}
