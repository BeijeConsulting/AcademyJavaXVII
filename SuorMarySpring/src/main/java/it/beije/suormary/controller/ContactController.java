package it.beije.suormary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.suormary.model.Contact;
import it.beije.suormary.repository.ContactRepository;
import it.beije.suormary.service.ContactService;


@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;

//	@Autowired
//	private ContactRepository contactRepository;

	@RequestMapping(value = "/contacts", method = RequestMethod.GET)
	public String contacts(Model model,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String surname) {
		System.out.println("GET /contacts " + surname);
		
		List<Contact> contacts = 
				surname != null ? 
						name != null ? contactService.findByNameAndSurname(name, surname) : contactService.findBySurname(surname)
						: contactService.getContactList();
		System.out.println("contacts size : " + contacts.size());
		
		model.addAttribute("contacts", contacts);		
		
		return "contacts";
	}
}
