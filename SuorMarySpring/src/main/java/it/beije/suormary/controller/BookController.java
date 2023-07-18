package it.beije.suormary.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController { 
	   @RequestMapping(value = "/createbook", method = RequestMethod.GET)
       public String createBookGet(HttpSession session) {
		    if(session.getAttribute("email")!= null) return "createBook";
		    else return "login";
       }
}
