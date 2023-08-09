package it.beije.suormary.bookstore1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore1.dto.UserCredential;

import it.beije.suormary.bookstore1.model.User;

import it.beije.suormary.bookstore1.service.UserServiceRest;

@RestController
@RequestMapping(value = "/personal")
public class AccessRestController {
	@Autowired
	private UserServiceRest userServiceRest;
	
	
	@GetMapping(value="/login/{email}/{password}")
	public ResponseEntity<String> loginGet(@PathVariable String email, @PathVariable String password){
		User u = userServiceRest.checkUser(email, password);
		if(u!=null) {
			return ResponseEntity.ok("utente con tali credenziali presente");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("non presente");
		}
	}
	
	@PostMapping(value="/registration")
	public User registrationPost(@RequestBody User u) {
		if(u!=null) {
			userServiceRest.insertUser(u);
			return u;
		}
		return null;
	}
	
	@PutMapping(value="/registration/{id}")
	public User registrationPut(@PathVariable Integer id, @RequestBody User u) {
		User output=null;
		
			if(u!=null && id>0) {
				if(id.compareTo(u.getId()) == 0){
					output=userServiceRest.updateUser(u);
					
				}
			} else throw new RuntimeException("id non valido");
			
		return output;
	}
	
	@DeleteMapping(value = "/registration/{id}")
	public Map<String, String> registrationDelete(@PathVariable("id") Integer id){
		Map<String, String> message = new HashMap<>();
		try {
			userServiceRest.deleteUser(id);
			message.put("message", "utente eliminato!");
		} catch(Exception e) {
			message.put("message", "impossibile eliminare utente");
		}
		return message;
	}
	
	
	/*
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(HttpSession session) {
		System.out.println("GET /login");
		
		String email = (String) session.getAttribute("email");
		
		if (email != null) { //utente loggato
			return "shop";
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
		
		
		User user = userService.checkUser(email, password);
		
		System.out.println(user);
		
		if (user!=null) { //OK
			session.setAttribute("email", email);
			
			List<Book> books = bookService.getAllBooks();
			System.out.println(books);
			model.addAttribute("books", books);
			
			return "shop";
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
	public String registrationPost(HttpSession session, Model model, User user) {
		System.out.println("POST /registration");
		System.out.println(user);	
		if(userService.userExists(user.getEmail())) {
			model.addAttribute("registrationError", "L' email inserita è già associata ad un account.");
			return "registration";
		}else {
			userService.createUser(user);
			model.addAttribute("registrationSuccess", "L'account è stato registrato con successo.");
			return "login";
		}
		
	}
	*/

}
