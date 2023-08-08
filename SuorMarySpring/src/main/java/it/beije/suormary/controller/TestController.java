package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.dto.User;
import it.beije.suormary.service.TestService;


@Controller
public class TestController {
	
	@Autowired
	private TestService testService;
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "test"; //  /WEB-INF/views/test.jsp
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpSession session, Model model) {
		System.out.println(request.getContextPath());
		System.out.println(session.getId());
		
		model.addAttribute("messaggio", "CIAO SUOR MARY!!");
		
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		System.out.println("GET /login");

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(HttpSession session, Model model,
			@RequestParam(name = "username", required = false) String username,
			@RequestParam String password) {
		System.out.println("POST /login");
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		
		//SELECT * from Users WHERE username = :username AND password = :password
		if (username != null && username.equalsIgnoreCase("pippo@beije.it") && password != null && password.equals("12345")) { //OK
			
			
//			List<String> libri = new ArrayList<String>();
//			libri.add("I Promessi Sposi");
//			libri.add("La Divina Commedia");
//			libri.add("Manuale OCA");
//			libri.add("Tre metri sopra il cielo");
//			libri.add("Guida galattica per programmatori");
			
//			TestService testService = new TestService();
			System.out.println("testService : " + testService.hashCode());
			List<String> books = testService.getBooks();
			
			model.addAttribute("libri", books);
			
			User user = new User();
			user.setEmail("pippo@beije.it");
			user.setName("Pippo");
			user.setSurname("Pluto");

			session.setAttribute("loggedUser", user);

			model.addAttribute("loggedUser", user);

			return "welcome";
		} else { //KO
			model.addAttribute("loginError", "CREDENZIALI NON VALIDE!!!");

			return "login";
		}
	}
	
	
	@RequestMapping(value = "/form_user", method = RequestMethod.GET)
	public String formUser() {
		System.out.println("GET /form_user");

		return "form_user";
	}

	@RequestMapping(value = "/insert_user", method = RequestMethod.POST)
	public String insertUser(User user, Model model) {
		System.out.println("POST /insert_user : " + user);
		
		model.addAttribute("user", user);
		
		return "insert_user";
	}

}
