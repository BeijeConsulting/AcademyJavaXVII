package it.beije.suormary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.beije.suormary.model.Contact;
import it.beije.suormary.service.ContactService;

@Controller
@RequestMapping(value = "/api")
public class ContactRestController {
	@Autowired
	private ContactService contactService;
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public @ResponseBody Contact contacts(Model model,	@RequestParam Integer id) {
		System.out.println("GET /contact?id=" + id);
		
		model.addAttribute("contact", contactService.getContact(id));		
		
		return contactService.getContact(id);
	}
}
