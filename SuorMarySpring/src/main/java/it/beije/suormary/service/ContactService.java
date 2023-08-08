//
//package it.beije.suormary.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import it.beije.suormary.model.Contact;
//import it.beije.suormary.model.ContactDetail;
//import it.beije.suormary.repository.ContactRepository;
//import it.beije.suormary.repository.ContactDetailRepository;
//
//@Service
//public class ContactService {
//	
//	@Autowired
//	private ContactRepository contactRepository;
//	
//	@Autowired
//	private ContactDetailRepository contactDetailRepository;
//	
//
//	public Contact getContact(Integer id) {
//		
//		Optional<Contact> c = contactRepository.findById(id);
//		
//		Contact contact = c.isPresent() ? c.get() : null;
//		
//		System.out.println("contact : " + contact);
//		
//		return contact;
//	}
//
//	@Transactional
//	public List<Contact> getContactList() {
//		
//		List<Contact> contacts = contactRepository.findAll();
//		System.out.println("contacts : " + contacts);
//		
//		return contacts;
//	}
//
//	public List<Contact> findBySurname(String surname) {
//		//System.out.println("countBySurname: " + contactRepository.countBySurname(surname));
//		
//		return contactRepository.findBySurname(surname); 
//	}
//
//	public List<Contact> findByNameAndSurname(String name, String surname) {
//		return contactRepository.findByNameAndSurname(name, surname); 
//	}
//	
//	public int countBySurname(String surname) {
//		return contactRepository.countBySurname(surname); 
//	}
//	
//	@Transactional
//	public Contact insertContact(Contact contact) {
//		// ... elaborazione per dettagli
//		contactRepository.save(contact);
////		contactRepository.flush();
//		
//		List<ContactDetail> contactDetails = contact.getDetails();
//		for(ContactDetail cd : contactDetails ) {
//			cd.setContactId(contact.getId());
//			contactDetailRepository.save(cd);
//		}
//		
//		contact.setDetails(contactDetails);		
//		
//		return contactRepository.save(contact);
//	}
//	
//	public Contact updateContact(Contact contact) {
//		
//		Optional<Contact> c = contactRepository.findById(contact.getId());
//		
//		if (!c.isPresent()) throw new RuntimeException("ID ERRATO!!!");
//		
//		Contact co =  c.get();
//		
//		BeanUtils.copyProperties(contact, co);
//		
//		contactRepository.save(co);
//		
//		System.out.println("updated contact : " + co);
//		
//		return contact;
//	}
//	
//	@Transactional
//	public void deleteContact(Integer id) {
//
//		contactDetailRepository.deleteByContactId(id);
//		contactDetailRepository.flush();
//		contactRepository.deleteById(id);
//		
//	}
//
//
//}
