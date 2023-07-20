package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor.OptimalPropertyAccessor;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Contact;
import it.beije.suormary.repository.ContactRepository;


@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
//	@Transactional
//	public Contact getContact(Integer id) {
//		
//		Contact contact = null;
//		try {
//			contact = contactRepository.getOne(id);
//			//contact.getDetails();
//		} catch (EntityNotFoundException e) {
//			System.out.println("### " + e);
//			//e.printStackTrace();
//		}
//		
//		System.out.println("contact : " + contact);
//		
//		return contact;
//	}


	public Contact getContact(Integer id) {
		
		Optional<Contact> c = contactRepository.findById(id);
		
		Contact contact = c.isPresent() ? c.get() : null;
		
		System.out.println("contact : " + contact);
		
		return contact;
	}

	@Transactional
	public List<Contact> getContactList() {
		
		List<Contact> contacts = contactRepository.findAll();
		System.out.println("contacts : " + contacts);
		
		return contacts;
	}

	public List<Contact> findBySurname(String surname) {
		//System.out.println("countBySurname: " + contactRepository.countBySurname(surname));
		
		return contactRepository.findBySurname(surname); 
	}

	public List<Contact> findByNameAndSurname(String name, String surname) {
		return contactRepository.findByNameAndSurname(name, surname); 
	}
	
	public int countBySurname(String surname) {
		return contactRepository.countBySurname(surname); 
	}

}
