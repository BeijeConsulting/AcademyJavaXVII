package it.beije.suormary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Contact;
import it.beije.suormary.model.ContactDetail;
import it.beije.suormary.repository.ContactDetailsRepository;
import it.beije.suormary.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ContactDetailsRepository contactDetailsRepository;

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
		// System.out.println("countBySurname: " +
		// contactRepository.countBySurname(surname));

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
		contactRepository.save(contact);
		contactRepository.flush();
		List<Contact> contacts = contactRepository.findBySurname(contact.getSurname());
		List<ContactDetail> details = new ArrayList<>();
		if (contact.getDetails() != null) {
			details = contact.getDetails();

			for (ContactDetail cd : details) {
				cd.setContactId(contacts.get(0).getId());

			}
			contactDetailsRepository.saveAll(details);
			contactDetailsRepository.flush();

		}

		return contact;

	}

	public Contact updateContact(Contact contact) {

		Optional<Contact> c = contactRepository.findById(contact.getId());

		if (!c.isPresent())
			throw new RuntimeException("ID ERRATO!!!");

		Contact co = c.get();

		BeanUtils.copyProperties(contact, co);

		contactRepository.save(co);

		System.out.println("updated contact : " + co);

		return contact;
	}

	@Transactional
	public void deleteContact(Integer id) {
		contactDetailsRepository.deleteByContactId(id);
		contactDetailsRepository.flush();
		contactRepository.deleteById(id);
	}

}
