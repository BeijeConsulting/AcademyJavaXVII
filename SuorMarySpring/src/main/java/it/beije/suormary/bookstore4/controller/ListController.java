package it.beije.suormary.bookstore4.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore4.model.Author;
import it.beije.suormary.bookstore4.model.BasketItem; 
import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.model.Order;
import it.beije.suormary.bookstore4.model.User;
import it.beije.suormary.bookstore4.service.EcommerceService;
 

@Controller
public class ListController { 
	
	@Autowired
	private EcommerceService ecommerceService;
	
	User user;	
	BasketItem bi = new BasketItem();
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		
		return stock(model);
	}
	
	@RequestMapping(value = "/loginbutton", method = RequestMethod.GET)
		public String loginButton() {
			return "loginpage";
	}
	
	//riceve parametri dal form di login
	@RequestMapping(value = "/loginpage", method = RequestMethod.POST)
	public String login(HttpSession session, 
			Model model,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		user = ecommerceService.findUser(email, password);
		if (user == null) {
			model.addAttribute("loginerror", "Email or password wrong...\nTry again or sign up");	
			return "loginpage";
		}
		session.setAttribute("user", user);
		model.addAttribute("booklist", ecommerceService.bookList());
		model.addAttribute("authorlist", ecommerceService.authorsList());
		return "stockpage";
	}
	
	//riceve parametri dalla pagina di sign up
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup() {
		return "signup";
	}
	
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String insertNewUser(HttpSession session, 
			Model model,
			@RequestParam("name") String name,
			@RequestParam("surname") String surname,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		
		boolean userAdded = ecommerceService.addUser(name, surname, email, password);
	
		//se l'utente è stato aggiunto passa per il metodo login e poi mostra i libri(non fa reinserire i dati)
		if(userAdded) return login(session, model, email, password);
		//altrimenti torna alla pagina di login con un messaggio di erroe e fa reinserire i dati
		else {
			model.addAttribute("loginerror", "Email already exists");
			return "loginpage";
		}
	}
	
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String bookList(HttpSession session, Model model) {
		List<Book> books = ecommerceService.bookList();
		model.addAttribute("booklist", books);
		
		 //se user loggato fai vedere anche carrello
		if(user != null) {
			model.addAttribute("user", user);
			HashMap<Book, Integer> basket = ecommerceService.basket(user.getId());
			model.addAttribute("basket", basket);
			model.addAttribute("sum", ecommerceService.sumBasket(user.getId()));	
		} 
		return "booklist"; //  /WEB-INF/views/booklist.jsp
	}
	
	
	@RequestMapping(value = "/addtobasket", method = RequestMethod.POST)
	public String addToBasket(HttpSession session, Model model, @RequestParam("bookId") Integer bookId) {
		System.out.println("SONO NEL METODO");
		//System.out.println("BOOK ID: " + bookId);
		ecommerceService.addToBasket(bookId, user.getId());
		
		// trova il libro per modificare la quantità
		//Book book = ecommerceService.findById(bookId);
		
		return bookList(session, model);
	}
	
	
	@RequestMapping(value = "/removefrombasket", method = RequestMethod.POST)
	public String removefrombasket(HttpSession session, Model model, @RequestParam("bookId") Integer bookId) {
		System.out.println("STO RIMUOVENDO");
		ecommerceService.removeFromBasket(bookId, user.getId());
		
		// trova il libro per modificare la quantità
		//Book book = ecommerceService.findById(bookId);
		
		return bookList(session, model);
	}

	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public String stock (Model model) {
		List<Book> books = ecommerceService.bookList();
		model.addAttribute("booklist", books);
		
		List<Author> authors = ecommerceService.authorsList();
		model.addAttribute("authorlist", authors);
		return "stockpage";
	}
	
	@RequestMapping(value = "/infouser", method = RequestMethod.GET)
	public String infoUser(HttpSession session, Model model) {
		// lista ordini
		Integer userId = user.getId();
		List<Order> orders = ecommerceService.getOrders(userId);
		System.out.println("lista ordini: " + orders);
		model.addAttribute("orders", orders);
		return "infouserpage";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		user = null;
		session.removeAttribute("user");
		return stock(model);
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment(Model model) {
		model.addAttribute("sum", ecommerceService.sumBasket(user.getId()));
		return "payment";
	}
	
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buy(HttpSession session, 
			Model model,
			@RequestParam("address") String address, 
			@RequestParam("typePayment") String typePayment) {
	Integer userId = user.getId();
	ecommerceService.buyBasket(userId, address, typePayment);
	return infoUser(session, model);
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addBook(Model model) {
		model.addAttribute("authorlist", ecommerceService.authorsList());
		return "insertBook";
	}
	
	@RequestMapping(value = "/insertbook", method = RequestMethod.POST)
	public String insertBook(
			Model model,
			@RequestParam("title") String title, 
			@RequestParam("authorId") Integer authorId,
			@RequestParam("description") String description,
			@RequestParam("editor") String editor,
			@RequestParam("price") Double price, 
			@RequestParam("quantity") Integer quantity) {
		ecommerceService.insertBook(title, description, authorId, editor, price, quantity);
		return stock(model);
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public String editBook(
			Model model,
			@RequestParam("bookId") Integer bookId) {
		Book book = ecommerceService.findBook(bookId);
		model.addAttribute("book", book);
		return "editBook";
	}
	
	@RequestMapping(value = "/updatebook", method = RequestMethod.POST)
	public String updateBook(Model model,
			@RequestParam("bookId") Integer bookId,
			@RequestParam("price") Double price,
			@RequestParam("price") Integer quantity) {
		ecommerceService.updateBook(bookId, price, quantity);
		return stock(model);
	}
	
	@RequestMapping(value = "/addauthor", method = RequestMethod.GET)
	public String addAuhor() {
		return "insertAuthor";
	}
	
	@RequestMapping(value = "/insertauthor", method = RequestMethod.POST)
	public String insertAuhor(
			Model model,
			@RequestParam("name") String name, 
			@RequestParam("surname") String surname,
			@RequestParam("description") String description) {
		ecommerceService.insertAuthor(name, surname, description);
		return stock(model);
	}
	
	@RequestMapping(value = "/editauthor", method = RequestMethod.POST)
	public String editAuthor(
			Model model,
			@RequestParam("authorId") Integer authorId) {
		Author author = ecommerceService.findAuthor(authorId);
		model.addAttribute("author", author);
		return "editAuthor";
	}
	
	@RequestMapping(value = "/updatebook", method = RequestMethod.POST)
	public String updateBook(Model model,
			@RequestParam("authorId") Integer authorId,
			@RequestParam("description") String description) {
		ecommerceService.updateAuthor(authorId, description);
		return stock(model);
	}

}
