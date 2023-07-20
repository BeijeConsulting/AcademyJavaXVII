package it.beije.suormary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.suormary.model.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{
	
	public List<Contact> findBySurname(String surname);

	public List<Contact> findByNameAndSurname(String name, String surname);
	
	//@Query(value = "SELECT COUNT(id) FROM Contact WHERE surname = :surname")
	@Query(value = "SELECT COUNT(*) FROM rubrica WHERE cognome = :surname", nativeQuery = true)
	public int countBySurname(@Param("surname") String surname);
	
	@Query(value = "SELECT * FROM Contact ORDER BY surname")
	public List<Contact> OrderedBySurname();
}
