package it.beije.suormary.controller;


import java.util.List;



import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(HttpSession session) {
		if(session.getAttribute("email") != null) {
			return "welcome";
		}
		else return "login"; 
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(HttpSession session, @RequestParam(name = "email")String email, Model model) {

		List<Book> books = BookStoreUtility.loadBooks();
		model.addAttribute("books", books);
		
		
		if(session.getAttribute("email") != null) {
			return "welcome";
		}
		System.out.println(email);
		User user = BookStoreUtility.loginUser(email);
		if(user != null) {
		    session.setAttribute("email", email);
		    model.addAttribute("email", email);	    
			return "welcome";
		}
		else {
			  model.addAttribute("loginError", "CREDENZIALI NON VALIDE!!");
			    
			  return "login";
		}
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerGet(HttpSession session) {

        if(session.getAttribute("email") != null) {
        	
        	 return "welcome";
         }
         else return "register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(HttpServletRequest request,HttpSession session, Model model) {
		List<Book> books = BookStoreUtility.loadBooks();
		model.addAttribute("books", books);
		
		String name=request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LocalDateTime date = LocalDateTime.now();
		BookStoreUtility.registerUser(name, surname, email, password, date);
		session.setAttribute("email", email);
		return "welcome";
	}
	@RequestMapping(value = "/my_order", method = RequestMethod.GET)
	public String myOrderGet(HttpSession session) {

        if(session.getAttribute("email") != null) {
        	
        	 return "my_order";
         }
         else return "register";
	}

}
