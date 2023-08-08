package it.beije.suormary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import it.beije.suormary.model.Author;
import it.beije.suormary.service.AuthorService;



@Controller
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@PutMapping(value = "/authors/{id}")
	public Author updateauthor(@PathVariable Integer id, @RequestBody Author author) {
		
		if (id.compareTo(author.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");
		
		return authorService.updateAuthor(author);
	}

}