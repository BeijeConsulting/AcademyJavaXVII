package it.beije.suormary.bookstore2.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore2.model.User;
import it.beije.suormary.bookstore2.service.UserService;


@Controller
public class LoginController  {
	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/bookstore_login", method = RequestMethod.GET)
	public String getLogin(HttpSession session) {
		System.out.println("bookstoreLogin doGet");

		User user = (User) session.getAttribute("user");
		System.out.println("user " + user);
		if (user != null) { // utente loggato
			return "bookstore_welcome";
		} else { // non loggato
			return "bookstore_login";
		}
	

	}

	
	@RequestMapping(value = "/bookstore_login", method = RequestMethod.POST)
	public String loginPost(HttpSession session, Model model,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "password", required = true) String password) {
		
		System.out.println("bookstoreLogin doPost");
		
		System.out.println("email : " + email);
		System.out.println("JSESSIONID: " + session.getId());
		
		User user = userService.findByEmailAndPassword(email, password);
		
		if (user != null ) { // OK
			user.setPassword("");
			
			session.setAttribute("user", user);
			

			return "redirect:/bookstore_welcome";
			
		} else { // KO

			model.addAttribute("loginError", "CREDENZIALI NON VALIDE!!!");
			return "bookstore_login";
		}
	}

}
