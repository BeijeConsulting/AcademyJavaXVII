package it.beije.suormary.bookstore2.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.repository.AuthorRepository;
import it.beije.suormary.bookstore2.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	public BookRepository bookRepository;
	
	@Autowired
	public AuthorRepository authorRepository;
	
	
	public List<Book> getBookList(){
		List<Book> books = bookRepository.findAll();
		return books;
	}
	
	public List<Author> getAuthorList(){
		List<Author> authors = authorRepository.findAll();
		return authors;
	}
	
	
	public Author findAuthorById(Integer id) {
		Optional<Author> a = authorRepository.findById(id);
		Author author = a.isPresent() ? a.get() : null;
		return author;
	}
	
	
	public Book findBook(Integer bookId) {
		Optional<Book> b = bookRepository.findById(bookId);
		Book book = b.isPresent() ? b.get() : null;
		return book;
	}
	
	public boolean save(Book book) {
    	Book newBook = bookRepository.save(book);
    	if (newBook == null) return false;
    	else return true;
    }
	
	
	
}
