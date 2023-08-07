package it.beije.suormary.bookstore1.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore1.model.Author;
import it.beije.suormary.bookstore1.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	public void addAuthor(Author author) {
		authorRepository.save(author);
	}
	
	public List<Author> getAuthorList(){
		return authorRepository.findAll();
	}
	
	public Author findById(Integer id) {
		Optional<Author>optional=authorRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
}
