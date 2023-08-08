package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.suormary.model.Contact;
import it.beije.suormary.model.ContactDetail;

public interface ContactDetailRepository extends JpaRepository<ContactDetail, Integer>{

	void deleteByContactId(Integer id);

}
