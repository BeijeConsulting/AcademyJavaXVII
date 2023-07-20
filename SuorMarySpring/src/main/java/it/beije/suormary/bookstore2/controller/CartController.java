package it.beije.suormary.bookstore2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.User;
import it.beije.suormary.bookstore2.service.BookService;

public class CartController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public String getCart(HttpSession session, Model model) {
		System.out.println("cart doGet");
		
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			return "bookstore_home";
		} else {
			Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
			List<Book> books =  new ArrayList<>();
        	List<Author> authors =  new ArrayList<>();
        	List<Integer> quantities =  new ArrayList<>();
        	Book book = null;
        	int quantity;
        	Author author = null;
        	if (cart != null && !cart.isEmpty()) {
        		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
        			book = bookService.findBook(entry.getKey());
                    quantity = entry.getValue();
                    author = bookService.findAuthorFromId(book.getAuthorId());
                    books.add(book);
                    authors.add(author);
                    quantities.add(quantity);
        		}
        	}
        	model.addAttribute("books", books);
        	model.addAttribute("authors", authors);
        	model.addAttribute("quantities", quantities);
        	
        	return "bookstore_cart";
		}
	}

}
