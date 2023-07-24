package it.beije.suormary.bookstore2.controller;


import java.util.ArrayList;
import java.util.List;

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
public class WelcomeController {
	
	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/bookstore_welcome", method = RequestMethod.GET)
	public String welcomeGet(HttpSession session, Model model)  {
		System.out.println("bookstoreWelcome doGet");
		
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Author> authors = new ArrayList<>();
        System.out.println("user:" + user);

        if (user == null) {
        	
            return "bookstore_login";
        } else {
            List<Book> books = bookService.getBookList();
            System.out.println("books : " + books);
            for (Book book : books) {
            	authors.add(bookService.findAuthorById(book.getAuthorId()));
            }
            
            model.addAttribute("authors", authors);
            model.addAttribute("books", books);
            
            return "bookstore_welcome";
        }
	}

	
	
	@RequestMapping(value = "/bookstore_home", method = RequestMethod.GET)
	public String homeGet(Model model)  {
		System.out.println("bookstoreWelcome doGet");
		
        List<Author> authors = new ArrayList<>();
     
            List<Book> books = bookService.getBookList();
            System.out.println("books : " + books);
            for (Book book : books) {
            	authors.add(bookService.findAuthorById(book.getAuthorId()));
            }
            
            model.addAttribute("authors", authors);
            model.addAttribute("books", books);
            
            return "bookstore_home";
        }



	
	}
