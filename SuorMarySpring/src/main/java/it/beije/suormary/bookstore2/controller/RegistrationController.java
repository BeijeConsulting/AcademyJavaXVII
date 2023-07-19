package it.beije.suormary.bookstore2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore2.model.User;
import it.beije.suormary.bookstore2.service.UserService;

@Controller
public class RegistrationController{
	
	
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/bookstore_registration", method = RequestMethod.GET)
	public String registrationGet(HttpSession session)  {

		System.out.println("bookstore_registration doGet");
		
		User user = (User) session.getAttribute("user");

		if (user != null) { 
			return "bookstore_welcome";
		} else { 
			return "bookstore_registration";
		}
	}

    @RequestMapping(value = "/bookstore_registration", method = RequestMethod.POST)
	public String registerPost(HttpSession session,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password,
			@RequestParam(name = "name", required = true) String name,
			@RequestParam(name = "surname", required = false) String surname)  {
		
    	System.out.println("bookstore_registration doPost");

        // Crea un oggetto Utente con i dati inseriti
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        
        userService.insertUser(user);
        
        return "bookstore_login";
	}

}
