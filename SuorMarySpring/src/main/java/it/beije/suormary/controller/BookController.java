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
import it.beije.suormary.service.BookService;
import it.beije.suormary.service.OrderService;
import it.beije.suormary.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController { 
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderService orderService;
	 

	   @RequestMapping(value = "/createBook", method = RequestMethod.GET)
       public String createBookGet(HttpSession session, Model model) {
		    if(session.getAttribute("email")!= null) {
		    	List<Author> authors = bookService.getAuthors();
		    	model.addAttribute("authors", authors);
		    	return "createBook";
		    }
		    else return "login";
       }
	   @RequestMapping(value = "/createBook", method = RequestMethod.POST)
       public String createBookPost(HttpSession session, HttpServletRequest request, Model model) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String editor = request.getParameter("editor");
			String price = request.getParameter("price");
			String quantity = request.getParameter("quantity");
			String authorId = request.getParameter("authorId");
			bookService.addBook(title, description, editor, price, quantity, authorId);
			List<Book> books = bookService.loadBooks();
			 model.addAttribute("books", books);	
			return "welcome";
       }
	   @RequestMapping(value = "/updateBook", method = RequestMethod.GET)
       public String updateBookGet(HttpSession session, Model model,@RequestParam(name="id") String id) {
		    if(session.getAttribute("email")!= null) {
				Book book = bookService.getBookById(id);
				System.out.println(book.getAuthorId());
				model.addAttribute("book",book);
				List<Author> authors = BookStoreUtility.getAuthors();
				model.addAttribute("authors", authors);
		    	return "updateBook";
		    }
		    else return "login";
	
       }
	   @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
       public String updateBookPost(HttpSession session, HttpServletRequest request) {
			String bookId = request.getParameter("id");
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String editor = request.getParameter("editor");
			String price = request.getParameter("price");
			String quantity = request.getParameter("quantity");
			String authorId = request.getParameter("authorId");
			bookService.updateBook(title, description, editor, price, quantity, authorId,bookId);
			return "welcome";
       }
	   @RequestMapping(value = "/deleteBook", method=RequestMethod.GET)
	   public String deleteBook(HttpSession session,@RequestParam String id, Model model) {
		   if(session.getAttribute("email")!= null) {
			   List<Book> books = bookService.loadBooks();
			   model.addAttribute("books", books);	
			   bookService.deleteBook(id);
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
	   

