package it.beije.suormary.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController { 
	   @RequestMapping(value = "/createBook", method = RequestMethod.GET)
       public String createBookGet(HttpSession session, Model model) {
		    if(session.getAttribute("email")!= null) {
		    	List<Author> authors = BookStoreUtility.getAuthors();
		    	model.addAttribute("authors", authors);
		    	return "createBook";
		    }
		    else return "login";
       }
	   @RequestMapping(value = "/createBook", method = RequestMethod.POST)
       public String createBookPost(HttpSession session, HttpServletRequest request) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String editor = request.getParameter("editor");
			String price = request.getParameter("price");
			String quantity = request.getParameter("quantity");
			String authorId = request.getParameter("authorId");
			BookStoreUtility.addBook(title, description, editor, price, quantity, authorId);
			return "welcome";
       }
}
