package it.beije.suormary.bookstore1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
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
			books.put(b.get(), entry.getValue());
			
		}
		return books;	
	}
	
	public void addNewBook(Book book){
		bookRepository.save(book);
	}
	
}
