package it.beije.suormary.bookstore1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore1.model.User;
import it.beije.suormary.bookstore1.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/user/{id}")
	public User selectUser(@PathVariable Integer id) {
		System.out.println("User GET");
		User user = userService.getUserById(id);
		return user;
	}
	
	@GetMapping(value = "/users")
	public List<User> selectUsers() {
		System.out.println("Users GET");
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@PostMapping(value = "/user")
	public User insertUser(@RequestBody User user) {
		userService.createUser(user);
		return user;
	}
	
}
