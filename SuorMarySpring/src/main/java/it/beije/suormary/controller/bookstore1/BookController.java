package it.beije.suormary.controller.bookstore1;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bin.bookstore1.Author;
import it.beije.suormary.bin.bookstore1.Book;
import it.beije.suormary.dumpster.bookstore1.AuthorUtils;
import it.beije.suormary.dumpster.bookstore1.BookUtils;
import it.beije.suormary.service.bookstore1.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String bookGet (HttpSession session) {
		List<Author> listAuthor = (List<Author>)AuthorUtils.getAuthorList();
		session.setAttribute("listAuthor", listAuthor);
		return "book"; 
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public String bookPost(Model model, Book book) {
		
		bookService.addNewBook(book);
		model.addAttribute("newBookMessage", "Il libro " + book.getTitle() + " è stato inserito con successo.");
		return "book";	
	}
	
}
