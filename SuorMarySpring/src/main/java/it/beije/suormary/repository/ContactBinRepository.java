package it.beije.suormary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.suormary.model.ContactBin;


@Repository
public interface ContactBinRepository extends JpaRepository<ContactBin, Integer>{
	
}
