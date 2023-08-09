package it.beije.suormary.bookstore1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.bookstore1.model.User;
import it.beije.suormary.bookstore1.service.UserService;
import it.beije.suormary.dto.AuthCredentials;


@RestController
@RequestMapping(value = "/api")
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
	
	@PostMapping(value = "/login")
	public String loginApi(@RequestBody AuthCredentials login) {
		User user = userService.checkUser(login.getEmail(), login.getPassword());
		if(user != null) {
			return "Login avvenuto";
		}
		return "Login fallito";
	}
	
	@GetMapping(value = "/logout")
	public String logoutApi() {
		//Passare la sessione da cancellare????
		return "boh";
	}
}
