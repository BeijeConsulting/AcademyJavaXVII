package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import it.beije.suormary.model.ContactDetail;

public interface ContactDetailsRepository extends JpaRepository<ContactDetail, Integer>{

	void deleteByContactId(Integer id);

	


}
