package it.beije.suormary.bookstore2.controller;

import java.time.LocalDateTime;
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
	
	@RequestMapping(value = "/bookstore_profile", method = RequestMethod.GET)
	public String getLogin(HttpSession session, Model model) {
		System.out.println("bookstoreProfile doGet");
		
		List<Integer> ids = (List<Integer>) session.getAttribute("favoritesBooksIds");
		List<Book> books = null;
		
		if (ids != null && !ids.isEmpty()) books = userService.getTheBooksFromId(ids);
	
		model.addAttribute("books", books);
		
		return "bookstore_profile";

	}
	
	@RequestMapping(value = "/bookstore_favorites", method = RequestMethod.POST)
	public String addToFav(HttpSession session, Model model,
			@RequestParam(name = "id", required = true) String bookIdString,
			@RequestParam(name = "action", required = true) String action) {
		
		//vedere se tramite la action chiamare il service di user che mi aggiunge ai preferiti i libri
	
		
		System.out.println("Favorites doPost");
		
		Integer bookId = Integer.valueOf(bookIdString);
		
		List<Integer> favoritesBooksIds = (List<Integer>) session.getAttribute("favoritesBooksIds");
		if (favoritesBooksIds == null) {
		    favoritesBooksIds = new ArrayList<>();
		    session.setAttribute("favoritesBooksIds", favoritesBooksIds);
		}
		
		if (action.equals("addBookToFav")) {
			if (!favoritesBooksIds.contains(bookId)) {
			    favoritesBooksIds.add(bookId);
			}
			return "redirect:bookstore_welcome"; 
		}
		else if (action.equals("removeBookToFav") || action.equals("removeBookToFavFromProfile")) {
			if (favoritesBooksIds.contains(bookId)) {
			    favoritesBooksIds.remove(bookId);
			}
			
			if (action.equals("removeBookToFav")) {
                return "redirect:bookstore_welcome"; 
            } else {
                return "redirect:bookstore_profile";
            }
		} else {
			//casistica non gestita
			return "redirect:bookstore_welcome";
		}
	}
	
	@RequestMapping(value = "/bookstore_update_user", method = RequestMethod.GET)
	public String getUpdateUser(HttpSession session) {
		return "bookstore_update_user";
	}
	
	@RequestMapping(value = "/bookstore_update_user", method = RequestMethod.POST)
	public String registerPost(HttpSession session,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "surname", required = false) String surname)  {
		
    	System.out.println("bookstore_update_user doPost");
    	
    	User user = (User) session.getAttribute("user");
    	
        // Crea un oggetto Utente con i dati inseriti
        User userUpdate = userService.findById(user.getId());
        userUpdate.setName(name);
        user.setName(name);
        
        userUpdate.setSurname(surname);
        user.setSurname(surname);
        
        userService.save(userUpdate);      
        
        return "redirect:bookstore_profile";
	}


}
