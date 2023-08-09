package it.beije.suormary.bookstore2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.User;
import it.beije.suormary.bookstore2.service.BookService;

@Controller
public class CartController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/bookstore_cart", method = RequestMethod.GET)
	public String getCart(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		
		if (user == null) {
			return "redirect:bookstore_home";
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
                    author = bookService.findAuthorById(book.getAuthorId());
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
	
	@RequestMapping(value = "/bookstore_cart", method = RequestMethod.POST)
	public String postCart(HttpSession session, Model model,
			@RequestParam(name = "id", required = true) String bookIdString,
			@RequestParam(name = "action", required = true) String action) {
		Integer bookId = Integer.valueOf(bookIdString);
		
		Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
		if (cart == null) {
	        cart = new HashMap<>();
	        session.setAttribute("cart", cart);
	    }
		
		int quantity = cart.getOrDefault(bookId, 0);
        Book book = bookService.findBook(bookId);
        int maxQuantity = book.getQuantity();
        
        if (action.equals("add") || action.equals("addFromBookDet")) {
        	if (quantity < maxQuantity) {
        		cart.put(bookId, quantity + 1);
                System.out.println("Added to cart: bookId=" + bookId + ", quantity=" + (quantity + 1));
        	} else {
        		System.out.println("Maximum quantity reached for bookId=" + bookId);
        	}

            if (action.equals("addFromBookDet")) {
                // Redirect back to the previous page or any other desired page
                return "redirect:bookstore_book_details?id=" + bookId;
            } else {
                return "redirect:bookstore_cart";
            }
        } else if (action.equals("remove")) {
            if (quantity > 0) {
                cart.put(bookId, quantity - 1);
                System.out.println("Removed from cart: bookId=" + bookId + ", quantity=" + (quantity - 1));
                if (quantity - 1 == 0) {
                	cart.remove(bookId);
                }
            }
            return "redirect:bookstore_cart";
        } else {
            // Gestire l'azione sconosciuta o altri casi
        	return "bookstore_cart";
        }
	}

}
