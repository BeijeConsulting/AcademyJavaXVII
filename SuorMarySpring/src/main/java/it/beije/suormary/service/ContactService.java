package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Contact;
import it.beije.suormary.model.ContactBin;
import it.beije.suormary.model.ContactDetail;
import it.beije.suormary.repository.ContactBinRepository;
import it.beije.suormary.repository.ContactDetailRepository;
import it.beije.suormary.repository.ContactRepository;


@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private ContactBinRepository contactBinRepository;
	@Autowired
	private ContactDetailRepository contactDetailRepository;
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
	
	@Transactional
	public Contact insertContact(Contact contact) {
		// ... elaborazione per dettagli
		ContactBin contBin = new ContactBin(contact);
		contactBinRepository.save(contBin);
		contactBinRepository.flush();
		
		Integer id = contBin.getId();
		contact.setId(id);
		for(int i=0; i<contact.getDetails().size();i++) {
			contact.getDetails().get(i).setContactId(id);
			contactDetailRepository.save(contact.getDetails().get(i));
		}
		return contact;
	}
	
	@Transactional
	public Contact updateContact(Contact contact) {
		
		Optional<Contact> c = contactRepository.findById(contact.getId());
		
		if (!c.isPresent()) throw new RuntimeException("ID ERRATO!!!");
		
		Contact co =  c.get();
		
		BeanUtils.copyProperties(contact, co);
		System.out.println(co);
		contactRepository.save(co);
		
		for(ContactDetail cd : co.getDetails()) {
			contactDetailRepository.save(cd);
		}
		contactDetailRepository.flush();
		
		System.out.println("updated contact : " + co);
		
		return contact;
	}
	
	public void deleteContact(Integer id) {
		contactRepository.deleteById(id);
	}


}
