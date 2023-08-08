package it.beije.suormary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.ContactDetail;
import it.beije.suormary.repository.ContactDetailsRepository;

@Service
public class ContactDetailsService {

	
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;
	
	public void saveAllContactDetails(List<ContactDetail> details) {
		contactDetailsRepository.saveAll(details);
	}
	
	public void deleteByContactId(Integer id) {
		contactDetailsRepository.deleteByContactId(id);
	}
}
