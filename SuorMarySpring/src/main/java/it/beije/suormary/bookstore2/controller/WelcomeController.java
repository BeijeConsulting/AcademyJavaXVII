package it.beije.suormary.bookstore2.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.User;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/bookstore_welcome", method = RequestMethod.GET)
	public String welcomeGet(HttpSession session, Model model)  {
		System.out.println("bookstoreWelcome doGet");
		
//		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
//        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        List<Author> authors = new ArrayList<>();
        System.out.println("user:" + user);
//        System.out.println("cart:" + cart);
        if (user == null) {
            // Utente non autenticato, reindirizza alla pagina di login
            return "bookstore_login";
        } else {
            List<Book> books = BookstoreUtility.readBooksFromDb();
            for (Book book : books) {
            	authors.add(BookstoreUtility.findAuthorFromId(book.getAuthorId()));
            }
            model.addAttribute("authors", authors);
            model.addAttribute("books", books);
            return "bookstore_welcome";
        }
	}


}
