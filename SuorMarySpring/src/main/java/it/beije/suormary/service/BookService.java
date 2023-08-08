package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.beije.suormary.controller.BookStoreUtility;
import it.beije.suormary.controller.JPAmanagerFactory;
import it.beije.suormary.model.Author;
import it.beije.suormary.model.Book;
import it.beije.suormary.repository.AuthorRepository;
import it.beije.suormary.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
     public Book addBook(Book bookReq) {
  		   Book book = new Book();
  		   book.setTitle(bookReq.getTitle());
  		   book.setDescription(bookReq.getDescription());
  		   book.setEditor(bookReq.getEditor());
  		   book.setQuantity(bookReq.getQuantity());
  		   book.setPrice(bookReq.getPrice());
  		   book.setAuthor(bookReq.getAuthor());
  		  return bookRepository.save(book);	
     }
     public Book getBookById(String idStr) {
  	   int id = Integer.parseInt(idStr);
  	   
  	   Optional<Book> b = bookRepository.findById(id);
  	   Book book = b.isPresent() ? b.get() : null;
  	   return book;
     }
     public Book getBookById(int id) {
       Optional<Book> b = bookRepository.findById(id);
       Book book = b.isPresent() ? b.get() : null;
  	   return book;
     }
     public  Book updateBook(Book bookReq) {
	
  		   Book book = getBookById(bookReq.getId());
  		   book.setTitle(bookReq.getTitle());
  		   book.setDescription(bookReq.getDescription());
  		   book.setEditor(bookReq.getEditor());
  		   book.setQuantity(bookReq.getQuantity());
  		   book.setPrice(bookReq.getPrice());
  		   book.setAuthor(bookReq.getAuthor());
  		  return bookRepository.save(book);
     }
     public  void deleteBook(Integer id) {
  	   Book book = getBookById(id);
  	   bookRepository.delete(book);
     }
 
     public List<Book> loadBooks(){
  	   List<Book> listBooks = bookRepository.findAll();
  	    return listBooks;
     }
}
