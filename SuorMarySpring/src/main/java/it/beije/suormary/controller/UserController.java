package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping(value="/api")
public class UserController {
	
	 @Autowired
	    private UserService userService;

	    @GetMapping(value="/users")
	    public List<User> listUsers() {
	        return userService.getAllUsers();
	    }


	    @GetMapping(value="/users/{id}")
	    public User user(@PathVariable Integer id) {
	        return userService.getUsersById(id);
	    }

	    @PostMapping(value ="/users")
	    public User insertUser(@RequestBody User user) {
	        return userService.insertUsers(user);
	    }

	    @DeleteMapping(value="/users/{id}") 
	    public Map<String, String> deleteUser(@PathVariable Integer id) {
	        Map<String, String> message = new HashMap<String, String>();

	        try {
	        	userService.deleteUserById(id);
	            message.put("message", "autore rimosso correttamente");
	        } catch (Exception e) {
	            message.put("message", e.getMessage());
	        }

	        return message;
	    }


	    @PutMapping(value = "/users/{id}")
	    public User updateUser(@PathVariable Integer id, @RequestBody User user) {

	        if (id.compareTo(user.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");

	        return userService.updateUser(user);
	    }
}
