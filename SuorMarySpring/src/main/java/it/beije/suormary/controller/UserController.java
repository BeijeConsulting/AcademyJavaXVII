package it.beije.suormary.controller;


import java.util.List;



import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(HttpSession session, Model model) {
		if(session.getAttribute("email") != null) {
			List<Book> books = BookStoreUtility.loadBooks();
			model.addAttribute("books", books);
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
		User user = userService.loginUser(email);
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
	public String registerGet(HttpSession session, Model model) {

        if(session.getAttribute("email") != null) {
        	List<Book> books = BookStoreUtility.loadBooks();
			model.addAttribute("books", books);
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
		userService.registerUser(name, surname, email, password, date);
		session.setAttribute("email", email);
		return "welcome";
		
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "welcome";
	}


}
