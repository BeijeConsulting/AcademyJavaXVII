package it.beije.suormary.controller;


import java.util.List;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.model.Book;
import it.beije.suormary.model.User;
import it.beije.suormary.service.BookService;
import it.beije.suormary.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
 
	@GetMapping(value = "/login")
	public String loginGet(HttpSession session, Model model) {
		if(session.getAttribute("email") != null) {
			List<Book> books = bookService.loadBooks();
			model.addAttribute("books", books);
			return "welcome";
		}
		else return "login"; 
	}
	@PostMapping(value = "/login")
	public User loginPost(@RequestBody User user) {
		return  userService.loginUser(user);

	}
	@GetMapping(value = "/register")
	public String registerGet(HttpSession session, Model model) {

        if(session.getAttribute("email") != null) {
        	List<Book> books = bookService.loadBooks();
			model.addAttribute("books", books);
        	 return "welcome";
         }
         else return "register";
	}
	@PostMapping(value = "/register")
	public User registerPost(@RequestBody User user) {
		System.out.println("pass" + user.getPassword());
		return userService.registerUser(user);

		
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}


}
