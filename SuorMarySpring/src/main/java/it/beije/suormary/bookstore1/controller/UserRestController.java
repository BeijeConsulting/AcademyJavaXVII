package it.beije.suormary.bookstore1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore1.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	
	
}
