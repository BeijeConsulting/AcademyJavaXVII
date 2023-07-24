package it.beije.suormary.bookstore2.service;

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
}
