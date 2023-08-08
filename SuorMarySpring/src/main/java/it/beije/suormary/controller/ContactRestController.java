package it.beije.suormary.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.suormary.model.Contact;
import it.beije.suormary.service.ContactService;


//@Controller
@RestController
@RequestMapping(value = "/api")
public class ContactRestController {

	@Autowired
	private ContactService contactService;

//	@RequestMapping(value = "/contact", method = RequestMethod.GET)
//	public @ResponseBody Contact contacts(@RequestParam Integer id) {
	@GetMapping(value = "/contact/{id}")
	public Contact getContact(@PathVariable Integer id) {
		System.out.println("GET /api/contact/" + id);
		
		return contactService.getContact(id);
	}

	@GetMapping(value = "/contacts")
	public List<Contact> contacts(@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname) {
		System.out.println("GET /api/contacts " + surname);
		
		List<Contact> contacts = 
				surname != null ? 
						name != null ? contactService.findByNameAndSurname(name, surname) : contactService.findBySurname(surname)
						: contactService.getContactList();
		
		return contacts;
	}
	
	@PostMapping(value = "/contact")
	public Contact insertContact(@RequestBody Contact contact) {
		System.out.println("POST /api/contact : " + contact);
		
		contactService.insertContact(contact);
		
		return contact;
	}

	@PutMapping(value = "/contact/{id}")
	public Contact updateContact(@PathVariable Integer id, @RequestBody Contact contact) {
		System.out.println("PUT /api/contact/" + id + " : " + contact);
		
		if (id.compareTo(contact.getId()) != 0) throw new RuntimeException("ID NON CORRISPONDENTI!!!");
		
		return contactService.updateContact(contact);
	}

	@DeleteMapping(value = "/contact/{id}")
	public Map<String, String> deleteContact(@PathVariable Integer id) {
		System.out.println("DELETE /api/contact/" + id);
		
		Map<String, String> message = new HashMap<String, String>();

		try {
			contactService.deleteContact(id);
			message.put("message", "contatto rimosso correttamente");
		} catch (Exception e) {
			message.put("message", e.getMessage());
		}		
		
		return message;
	}

}
