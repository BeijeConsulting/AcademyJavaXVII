package it.beije.suormary.bookstore2.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bookstore2.model.User;


@Controller
public class LoginController  {
	
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
//
//		String email = request.getParameter("email");
//		String password = request.getParameter("password");
		
		System.out.println("email : " + email);
		System.out.println("password : " + password);
//
//		session = request.getSession();
		System.out.println("JSESSIONID: " + session.getId());
		User user = BookstoreUtility.checkUser(email, password);
		
		if (user != null ) { // OK
			user.setPassword("");
			
			session.setAttribute("user", user);
			
			model.addAttribute("user", user);
			
//			return "bookstore_welcome";
			return "redirect:/bookstore_welcome";
			
		} else { // KO
//			session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");
			model.addAttribute("loginError", "CREDENZIALI NON VALIDE!!!");
			return "bookstore_login";
		}

	}

}
