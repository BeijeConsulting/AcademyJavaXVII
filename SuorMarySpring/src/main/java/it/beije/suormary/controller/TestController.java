package it.beije.suormary.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestController {
	
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

}
