package it.beije.suormary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.service.TestService;


@Controller
public class BookControllerExample {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "/mybooks", method = RequestMethod.GET)
	public String mybooks(Model model) {
		System.out.println("GET /mybooks");

//		TestService testService = new TestService();
		System.out.println("testService : " + testService.hashCode());
		List<String> books = testService.getBooks();
		
		model.addAttribute("libri", books);
		
		return "mybooks";
	}


}
