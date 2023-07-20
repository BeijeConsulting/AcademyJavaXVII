package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Author;
import it.beije.suormary.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	 public List<Author> getAuthors(){
	  	   List<Author> authors = authorRepository.findAll();
	  	   return authors;
	     }
	 public void createAuthor(String idStr, String name, String surname, String description) {
		 Integer id = Integer.valueOf(idStr);
		 Author author = new Author();
		 author.setId(id);
		 author.setName(name);
		 author.setSurname(surname);
		 author.setDescription(description);
		 authorRepository.save(author);
		 
	 }
	 public Author getAuthorById(String idStr) {
		 Integer id = Integer.valueOf(idStr);
		 Optional<Author> authorOpt = authorRepository.findById(id);
		 Author author = authorOpt.isPresent() ? authorOpt.get() : null;
		 return author;
		 
	 }
	 public void updateAuthor(String idStr, String name, String surname, String description) {
		 Integer id = Integer.valueOf(idStr);
		 Author author = getAuthorById(idStr);
		 author.setId(id);
		 author.setName(name);
		 author.setSurname(surname);
		 author.setDescription(description);
		 authorRepository.save(author);
		 
	 }
	 public void deleteAuthor(String idStr) {
		 Author author = getAuthorById(idStr);
		 authorRepository.delete(author);
	 }
	

}
