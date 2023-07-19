package it.beije.suormary.controller.bookstore1;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String getBook(HttpSession session) {
		List<Author> listAuthor = (List<Author>)AuthorUtils.getAuthorList();
		session.setAttribute("listAuthor", listAuthor);
		return "book"; 
	}
	
	@RequestMapping(value = "/loginEsempio", method = RequestMethod.POST)
	public String loginPost(HttpSession session, Model model,
			@RequestParam String title, @RequestParam String description,
			@RequestParam String editor, @RequestParam String price, @RequestParam String quantity,
			@RequestParam String author) {
		
		double priceDouble = Double.valueOf(price);
		int quantityInt = Integer.valueOf(quantity);
		int idAuthor = Integer.valueOf(author);
		BookUtils.addNewBook(title, description, editor, priceDouble, quantityInt, idAuthor);
		session.setAttribute("newBookMessage", "Il libro " + title + " è stato inserito con successo.");
		return "book";	
	}
	
}
