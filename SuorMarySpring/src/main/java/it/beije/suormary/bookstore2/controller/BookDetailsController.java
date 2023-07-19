package it.beije.suormary.bookstore2.controller;

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
import it.beije.suormary.bookstore2.service.BookService;

@Controller
public class BookDetailsController {

	
	@Autowired
	private BookService bookService;
	

	@RequestMapping(value = "/bookstore_book_details", method = RequestMethod.GET)
	public String bookDetailsGet(HttpSession session, Model model,
			@RequestParam(name = "id", required = true) String bookIdString) {
    	
    	int bookId = Integer.parseInt(bookIdString);
    	
    	System.out.println("BookstoreBookDetails get");

 
        Book book = bookService.findBook(bookId);
        Author author = bookService.findAuthorFromId(book.getAuthorId());
        System.out.println("book: " + book);
        
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        
        model.addAttribute("book", book);
        model.addAttribute("author", author);
        model.addAttribute("cart", cart);

        return "bookstore_book_details";
    
    }
	
}
