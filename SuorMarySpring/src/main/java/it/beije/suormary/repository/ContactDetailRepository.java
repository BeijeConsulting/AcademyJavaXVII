package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.model.ContactDetail;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, Integer>{
	
}
