package it.beije.suormary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Contact;
import it.beije.suormary.model.ContactDetail;
import it.beije.suormary.repository.ContactDetailRepository;

@Service
public class ContactDetailService {
	@Autowired
	private ContactDetailRepository contactDetailRepository;
	
	public ContactDetail saveContactDetail(ContactDetail contactDetail) {
		// ... elaborazione per dettagli
		return contactDetailRepository.save(contactDetail);
	}
}
