package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.ContactWithDetailsRequest;
import it.beije.suormary.model.Contact;
import it.beije.suormary.model.ContactDetail;
import it.beije.suormary.repository.ContactDetailRepository;
import it.beije.suormary.repository.ContactRepository;


@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ContactDetailService contactDetailService;
	
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
	public Contact insertContact(ContactWithDetailsRequest request) {
		// ... elaborazione per dettagli
		
		Contact contact = request.getContact();
	    List<ContactDetail> details = request.getDetails();
		contactRepository.save(contact);
		
		contactRepository.flush();
		
		// Aggiungi dettagli al contatto
        //List<ContactDetail> details = contact.getDetails();
		contact.setDetails(details);
        if (details != null && !details.isEmpty()) {
            for (ContactDetail detail : details) {
                detail.setContactId(contact.getId()); // Imposta l'ID del contatto nei dettagli
                contactDetailService.saveContactDetail(detail); // Salva il dettaglio nel database
                //contactDetailRepository.flush();
            }
        }

        return contact;
    }
	
	
	public Contact updateContact(Contact contact) {
		
		Optional<Contact> c = contactRepository.findById(contact.getId());
		
		if (!c.isPresent()) throw new RuntimeException("ID ERRATO!!!");
		
		Contact co =  c.get();
		
		BeanUtils.copyProperties(contact, co);
		
		contactRepository.save(co);
		
		System.out.println("updated contact : " + co);
		
		return contact;
	}
	
	
	
	public void deleteContact(Integer id) {
		contactRepository.deleteById(id);
	}


}
