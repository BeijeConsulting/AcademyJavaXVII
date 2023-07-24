package it.beije.suormary.bookstore1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore1.model.Author;
import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorService authorService;
	
	public List<Book> getAllBooks(){
		List<Book> books = bookRepository.findAll();
		List<Integer> quantityBook = null;
		for(int i = 0; i<books.size(); i++) {
			quantityBook=new ArrayList<>();	
			books.get(i).setItemQuantity(books.get(i).getQuantity());
			//books.get(i).setAuthor(getAuthorById(books.get(i).getAuthorId()));
		}
		return books;
	}
	
	public Optional<Book> getBook(int id) {
		return bookRepository.findById(id);
	}
	
	public Map<Book,Integer> getBooks(Map<Integer,Integer> cart){
		
		Map<Book,Integer> books = new HashMap<>();
		Optional<Book> b = null;
		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
			b = getBook(entry.getKey());
			b.get().setItemQuantity(entry.getValue());
			//b.get().setAuthor(getAuthorById(b.get().getAuthorId()));
			books.put(b.get(), entry.getValue());
			
		}
		return books;	
	}
	
	public void addNewBook(Book book){
		bookRepository.save(book);
	}
	
	public Book getBookById(Integer id) {
		Optional<Book> optional = bookRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public Author getAuthorById(Integer id) {
		return authorService.findById(id);
	}
	
}
