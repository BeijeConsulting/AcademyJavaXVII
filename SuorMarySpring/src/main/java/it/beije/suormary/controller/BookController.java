package it.beije.suormary.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.suormary.model.Author;
import it.beije.suormary.model.Book;
import it.beije.suormary.model.Order;
import it.beije.suormary.service.AuthorService;
import it.beije.suormary.service.BookService;
import it.beije.suormary.service.OrderService;
import it.beije.suormary.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController { 
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private AuthorService authorService;
	 

	   @GetMapping(value = "/createBook")
       public String createBookGet(HttpSession session, Model model) {
		    if(session.getAttribute("email")!= null) {
		    	List<Author> authors = authorService.getAuthors();
		    	model.addAttribute("authors", authors);
		    	return "createBook";
		    }
		    else return "login";
       }
	   @PostMapping(value = "/createBook")
       public Book createBookPost(@RequestBody Book book) {
		   return bookService.addBook(book);

       }
	   @GetMapping(value = "/updateBook/{id}")
       public Book updateBookGet(@PathVariable Integer id) {
		   return bookService.getBookById(id);	
       }
	   @PutMapping(value = "/updateBook/{id}")
       public Book updateBookPost(@PathVariable Integer id,@RequestBody Book book) {
		   if (id.compareTo(book.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");
			return bookService.updateBook(book);
       }
	   @RequestMapping(value = "/deleteBook", method=RequestMethod.GET)
	   public String deleteBook(HttpSession session,@RequestParam String id, Model model) {
		   if(session.getAttribute("email")!= null) {
			   bookService.deleteBook(id);
			   List<Book> books = bookService.loadBooks();
			   model.addAttribute("books", books);	
			return "welcome";
		   }
		   else {
			    return "login";  
		   }
	
	   }
	   @RequestMapping(value = "/quantityBook", method=RequestMethod.GET)
	   public String quantityBook(HttpServletRequest request, Model model, HttpSession session) {
				String quantity = request.getParameter("quantity");
				String idStr = request.getParameter("bookId");
				Book book = bookService.getBookById(idStr);
				int quantityId = Integer.parseInt(quantity);
				List<Book> booksOrder =(List) session.getAttribute("booksOrder");
				List<Book> books = BookStoreUtility.loadBooks();
				model.addAttribute("booksOrder", booksOrder);
				model.addAttribute("books", books);
				Order order = (Order) session.getAttribute("order");
				model.addAttribute("orderId", order.getId());
				if(quantityId > book.getQuantity() ) {
					model.addAttribute("ErrorQuantity", "Hai inserito una quantità maggiore rispetto a quelli disponibili");
				}
				else {
					session.setAttribute("quantity", quantityId);
					model.addAttribute("quantity", quantityId);
				}
				return "createOrder";

			
	   }
	 
}
	   

