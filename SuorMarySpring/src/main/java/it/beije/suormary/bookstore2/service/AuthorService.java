package it.beije.suormary.bookstore2.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	public AuthorRepository authorRepository;
	
	public boolean save(Author author) {
		Author newAuthor = authorRepository.save(author);
    	if (newAuthor == null) return false;
    	else return true;
    }
	
	public Author getAuthorById(Integer id) {
		Optional<Author> a = authorRepository.findById(id);
		Author author = a.isPresent() ? a.get() : null;
		
		return author;
	}
	
	public List<Author> getAllAuthors(){
		List<Author> authors = authorRepository.findAll();
		return authors;
	} 
}
