package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.model.Author;
import it.beije.suormary.model.Book;
import it.beije.suormary.repository.AuthorRepository;


@Service
public class AuthorService {
	
	@Autowired
    private AuthorRepository authorRepository;
	
	@Autowired
    private BookService bookService;
	
	public List<Author> getAllAuthors() {
		return authorRepository.findAll(); 
		
	}

	public Author getAuthorById(Integer id) {
		Optional<Author> author = authorRepository.findById(id);
		if(author.isPresent()) {
			return author.get();
		}
		return null;
	}
	
	public Author insertAuthor(Author author) {
		return authorRepository.save(author);
	}
	
	public void deleteAuthorById(Integer id) {
		List<Book> books = bookService.findBookFromAuthorId(id);
		for(Book b : books) {
			bookService.deleteBook(b.getId());
		}
		
		authorRepository.deleteById(id);
	}
	
}
