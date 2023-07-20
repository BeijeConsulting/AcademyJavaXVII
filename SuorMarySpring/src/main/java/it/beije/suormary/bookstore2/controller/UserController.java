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

import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.User;
import it.beije.suormary.bookstore2.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	@RequestMapping(value = "/bookstore_favorites", method = RequestMethod.POST)
	public String addToFav(HttpSession session, Model model,
			@RequestParam(name = "id", required = true) String bookIdString,
			@RequestParam(name = "action", required = true) String action) {
		
		//vedere se tramite la action chiamare il service di user che mi aggiunge ai preferiti i libri
	
		
		System.out.println("Favorites doPost");
		
		int bookId = Integer.parseInt(bookIdString);
	
	
		
		List<Integer> favoritesBooksIds = (List<Integer>) session.getAttribute("favoritesBooksIds");
		if (favoritesBooksIds == null) {
		    favoritesBooksIds = new ArrayList<>();
		}
		if (!favoritesBooksIds.contains(bookId)) {
		    favoritesBooksIds.add(bookId);
		    session.setAttribute("favoritesBooksIds", favoritesBooksIds);
		}
		
		model.addAttribute("favoritesBooksIds", favoritesBooksIds);

		
		System.out.println("favoritesBooksIds" + favoritesBooksIds);
		
		return "redirect:bookstore_welcome";
	}
	
	@RequestMapping(value = "/bookstore_profile", method = RequestMethod.GET)
	public String getLogin(HttpSession session, Model model) {
		System.out.println("bookstoreProfile doGet");
		
		List<Integer> ids = (List<Integer>) session.getAttribute("favoritesBooksIds");

		List<Book> books = userService.getTheBooksFromId(ids);
	
		model.addAttribute("books", books);
		
		return "bookstore_profile";

	}


}
