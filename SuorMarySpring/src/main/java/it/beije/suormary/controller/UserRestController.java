package it.beije.suormary.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import it.beije.suormary.model.User;
import it.beije.suormary.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/users")
	public List<User> users(){
		System.out.println("GET users");
		
		List<User> users = userService.getAllUser();
		
		return users;
	}
	
	@GetMapping(value = "/user_id/{id}")
	public User getUserId(@PathVariable Integer id) {
		System.out.println("GET user_id/" + id);
		
		User user = userService.findUser(id);
		
		return user;
	}

	@GetMapping(value = "/user_email/{email}")
	public User getUserEmail(@PathVariable String email) {
		System.out.println("GET user_email/" + email);		
		User user = userService.loginUser(email);		
		return user;
	}
	
	@PostMapping(value = "/user")
	public User insertUser(@RequestBody User user) {
		System.out.println("POST user/" + user.getName());
		
		user.setCreationDate(LocalDateTime.now());
		
		return userService.insertUser(user);
	}
	
	@PutMapping(value = "/user_update/{id}")
	public User updateUser(@PathVariable Integer id, @RequestBody User user) {
		System.out.println("PUT user_update/");
				
		user.setCreationDate(userService.findUser(id).getCreationDate());
		
		return userService.updateUser(user);
	}
	
	@DeleteMapping(value = "/delete_user/{id}")
	public Map<String,String> deleteUser(@PathVariable Integer id){
		Map<String,String> message = new HashMap<String,String>();
		
		try {
			userService.deleteUser(id);
			message.put("message:" , "User deleted");
		} catch (Exception e) {
			message.put("message:", e.getMessage());
		}
		return message;
	}

}
