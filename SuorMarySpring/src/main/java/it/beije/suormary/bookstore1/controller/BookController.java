package it.beije.suormary.bookstore1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore1.model.Author;
import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.service.AuthorService;
import it.beije.suormary.bookstore1.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String bookGet (Model model) {
		List<Author> listAuthor = (List<Author>)authorService.getAuthorList();
		model.addAttribute("listAuthor", listAuthor);
		return "book"; 
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public String bookPost(Model model, @RequestParam String title, @RequestParam String description,@RequestParam String editor,@RequestParam String price,@RequestParam String quantity, 
			@RequestParam String author) {
		Integer qtyInt = Integer.valueOf(quantity);
		Double priceDouble = Double.valueOf(price);
		Integer authorId = Integer.valueOf(author);
		Author authorFinal = authorService.findById(authorId);
		Book book = new Book(title, description, editor, priceDouble, qtyInt, authorFinal);
		System.out.println(book);
		bookService.addNewBook(book);
		model.addAttribute("newBookMessage", "Il libro " + book.getTitle() + " è stato inserito con successo.");
		List<Author> listAuthor = (List<Author>)authorService.getAuthorList();
		model.addAttribute("listAuthor", listAuthor);
		return "book";	
	}
	
	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public String authorGet (HttpSession session) {
		return "new_author";
	}
	
	@RequestMapping(value = "/author", method = RequestMethod.POST)
	public String authorPost(Model model, Author author) {
		
		authorService.addAuthor(author);
		
		model.addAttribute("newAuthorMessage", "L'autore " + author.getName() + " " + author.getName() + " è stato inserito con successo.");
		
		return "new_author";
	}
	
}