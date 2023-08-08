package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.suormary.model.ContactDetail;

public interface ContactDetailRepository extends JpaRepository <ContactDetail, Integer>{

	public void deleteByContactId(Integer id);

}
