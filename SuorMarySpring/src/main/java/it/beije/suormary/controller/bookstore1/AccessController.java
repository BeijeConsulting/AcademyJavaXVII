package it.beije.suormary.controller.bookstore1;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.bin.bookstore1.User;
import it.beije.suormary.dumpster.bookstore1.UserUtils;

@Controller
public class AccessController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(HttpSession session) {
		System.out.println("GET /login");
		
		String email = (String) session.getAttribute("email");
		
		if (email != null) { //utente loggato
			return "";
		//chiamare metodo di shop!!!
		} else { //non loggato
		
			return "login";
		}

		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(HttpSession session, Model model,
			@RequestParam String email,
			@RequestParam String password) {
		System.out.println("POST /login");
		
		System.out.println("email : " + email);
		System.out.println("password : " + password);
		
		
		User user = UserUtils.checkUser(email, password);
		
		System.out.println(user);
		
		if (user!=null) { //OK
			session.setAttribute("email", email);
				
			//response.sendRedirect("./ShopServlet");
			return null;
		} else { //KO
			//session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");
			model.addAttribute("loginError", "CREDENZIALI NON VALIDE!!!");
			return "login";
			
		}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGet(HttpSession session) {
		session.removeAttribute("email");
		//session.removeAttribute("books");
		//session.removeAttribute("cart");
		return "login";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationGet() {
		System.out.println("GET /registration");
		
		return "registration";
		
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrationPost(HttpSession session, Model model,
			@RequestParam String email,
			@RequestParam String password, @RequestParam String name, 
			@RequestParam String surname) {
		System.out.println("POST /registration");
		
		
		
		if(UserUtils.userExists(email)) {
			model.addAttribute("registrationError", "L' email inserita è già associata ad un account.");
			return "registration";
		}else {
			UserUtils.createUser(email, password, name, surname);
			model.addAttribute("registrationSuccess", "L'account è stato registrato con successo.");
			return "login";
		}
		
	}

	}
