package it.beije.suormary.bookstore2.controller;

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
import it.beije.suormary.bookstore2.service.BookService;

@Controller
public class BookControllerBS {

	
	@Autowired
	private BookService bookService;
	

	@RequestMapping(value = "/bookstore_book_details", method = RequestMethod.GET)
	public String bookDetailsGet(HttpSession session, Model model,
			@RequestParam(name = "id", required = true) String bookIdString) {
    	
    	int bookId = Integer.parseInt(bookIdString);
    	
    	System.out.println("BookstoreBookDetails get");

 
        Book book = bookService.findBook(bookId);
        Author author = bookService.findAuthorById(book.getAuthorId());
        System.out.println("book: " + book);
        
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        
        model.addAttribute("book", book);
        model.addAttribute("author", author);
        model.addAttribute("cart", cart);

        return "bookstore_book_details";
    
    }
	
	@RequestMapping(value = "/bookstore_insert_book", method = RequestMethod.GET)
	public String getInsertBook(Model model) {
		List<Author> authors = bookService.getAuthorList();
		model.addAttribute("authors", authors);
		return "bookstore_insert_book";

        }
	
	
	@RequestMapping(value = "/bookstore_insert_book", method = RequestMethod.POST)
	public String insertBook(HttpSession session, Model model,
			@RequestParam(name = "title", required = true) String title,
			@RequestParam(name = "description", required = false) String description,
			@RequestParam(name = "author", required = true) String author,
			@RequestParam(name = "editor", required = false) String editor,
			@RequestParam(name = "price", required = true) String price,
			@RequestParam(name = "quantity", required = true) String quantity) {
	
	Integer authorId = Integer.parseInt(author);

	double p = Double.parseDouble(price);

	int q = Integer.parseInt(quantity);
	
	Book book = new Book();	
	book.setTitle(title);
	book.setDescription(description);
	book.setAuthorId(authorId);
	book.setEditor(editor);
	book.setPrice(p);
	book.setQuantity(q);
	
	bookService.save(book);
	
	
	 return "redirect:bookstore_welcome";
	}
}
