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
	 public Author createAuthor(Author authorReq) {
		 Author author = new Author();
		 author.setName(authorReq.getName());
		 author.setSurname(authorReq.getSurname());
		 author.setDescription(authorReq.getDescription());
		 authorRepository.save(author);
		 return author;
		 
	 }
	 public Author getAuthorById(String idStr) {
		 Integer id = Integer.valueOf(idStr);
		 Optional<Author> authorOpt = authorRepository.findById(id);
		 Author author = authorOpt.isPresent() ? authorOpt.get() : null;
		 return author;
		 
	 }
	 public Author getAuthorById(Integer id) {
		 Optional<Author> authorOpt = authorRepository.findById(id);
		 Author author = authorOpt.isPresent() ? authorOpt.get() : null;
		 return author;
		 
	 }
	 public Author updateAuthor(Integer id, Author authorReq) {
		 Author author = getAuthorById(id);
		 author.setName(authorReq.getName());
		 author.setSurname(authorReq.getSurname());
		 author.setDescription(authorReq.getDescription());
		return authorRepository.save(author);
		 
	 }
	 public void deleteAuthor(String idStr) {
		 Author author = getAuthorById(idStr);
		 authorRepository.delete(author);
	 }
	 public void deleteAuthor(Integer id) {
		 Author author = getAuthorById(id);
		 authorRepository.delete(author);
	 }
	

}
