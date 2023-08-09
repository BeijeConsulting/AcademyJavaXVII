package it.beije.suormary.bookstore1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore1.model.Author;
import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.repository.AuthorRepositoryRest;
import it.beije.suormary.bookstore1.repository.BookRepositoryRest;

@Service
public class BookServiceRest {
	@Autowired
	private BookRepositoryRest brr;
	
	@Autowired
	private AuthorService as;
	
	public List<Book> findallBooks(){
		List<Book> books = brr.findAll();
		return books;
	}
	
	public List<Book> findallBooksByAuthor(Integer id){
		Author a = as.findById(id);
		List<Book> books = brr.findAllBooksByAuthor(a);
		return books;
	}
	
	public Book insertBook(Book book) {
		
		Author a = book.getAuthor();
		Integer i = a.getId();
		Author author = as.findById(i);
		if(a.equals(author)) {
			return brr.save(book);
		} 
		
		//se sono diversi modifico prima l'autore
		book.setAuthor(as.updateAuthor(a));
		return brr.save(book);
		
		//brr.flush();
		
		//Author a = book.getAuthor();
		
		//book.setAuthor(a);
		
		
		//return brr.save(book);
		
		
	}
	
	public Book upDateBook(Book book) {
		Optional<Book> optional = brr.findById(book.getId());
		if(optional.isPresent()) {
			Book copy = optional.get();
			BeanUtils.copyProperties(book, copy);
			return insertBook(copy);
		}
		return null;
	}
	
	public void deleteBook(Integer id) {
		brr.deleteById(id);
	}
	
}
	
