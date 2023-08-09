package it.beije.suormary.bookstore1.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore1.model.Author;
import it.beije.suormary.bookstore1.model.User;
import it.beije.suormary.bookstore1.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	public List<Author> searchAuthorByNameAndSurname(String name, String surname){
		List<Author> author = authorRepository.findByNameAndSurname(name, surname);
		return author;
	}
	
	public List<Author> searchAllAuthors(){
		List<Author> authors = authorRepository.findAll();
		return authors;
	}
	
	public void addAuthor(Author author) {
		authorRepository.save(author);
	}
	
	/*public List<Author> getAuthorList(){
		return authorRepository.findAll();
	}*/
	
	public Author findById(Integer id) {
		Optional<Author>optional=authorRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public Author insertAuthor(Author a) {
		return authorRepository.save(a);
	}
	
	public Author updateAuthor(Author a) {
		Optional<Author> optional = authorRepository.findById(a.getId());
		
		if(optional.isPresent()) {
			//creo la copia dell'oggetto sul db
			Author copy = optional.get();
			BeanUtils.copyProperties(a, copy);
			return insertAuthor(copy);
		}
		return null;
	}
	
	public void deleteAuthor(Integer id) {
		authorRepository.deleteById(id);
	}
}
