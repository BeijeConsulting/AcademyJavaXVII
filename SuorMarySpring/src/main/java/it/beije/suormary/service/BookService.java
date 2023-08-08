package it.beije.suormary.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
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
	
     public void addBook(String title, String description, String editor, String priceString, String quantityString, String authorIdStr) {
  	   int authorId = Integer.parseInt(authorIdStr);
  	   double price = Double.parseDouble(priceString);
  	   int quantity = Integer.parseInt(quantityString);	  
  		   Book book = new Book();
  		   book.setTitle(title);
  		   book.setDescription(description);
  		   book.setEditor(editor);
  		   book.setQuantity(quantity);
  		   book.setPrice(price);
  		   book.setAuthorId(authorId);
  		   bookRepository.save(book);	
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
       if (book == null) {
  		 System.out.println("Book not found");
  	 }
  	   return book;
     }
     public  void updateBook(String title, String description, String editor, String priceString, String quantityString, String authorIdStr,String bookIdStr) {
  	   int authorId = Integer.parseInt(authorIdStr);
  	   int bookId = Integer.parseInt(bookIdStr);
  	   double price = Double.parseDouble(priceString);
  	   int quantity = Integer.parseInt(quantityString);
  	
  		   Book book = getBookById(bookId);
  		   book.setTitle(title);
  		   book.setDescription(description);
  		   book.setEditor(editor);
  		   book.setQuantity(quantity);
  		   book.setPrice(price);
  		   book.setAuthorId(authorId);
  		   bookRepository.save(book);
     }
     public  void deleteBook(String idStr) {
  	   int id = Integer.parseInt(idStr);
  	   Book book = getBookById(id);
  	   bookRepository.delete(book);
     }
 
     public List<Book> loadBooks(){
  	   List<Book> listBooks = bookRepository.findAll();
  	    return listBooks;
     }

     public Book getBookByTitle(String title) {
    	 	
    	 String searchTitle = title.substring(0,1).toUpperCase() + title.substring(1).toLowerCase();
    	 
    	 Book book = bookRepository.findBookByTitle(searchTitle);
    	    
    	 if (book == null) {
    		 System.out.println("Book not found with title: " + title);
    	 }
    	    
	return book;
	}
     
	public Book addBook(Book book) {

		return bookRepository.save(book);	
	}

	public Book updateBook(Book book) {
	
	Optional<Book> b = bookRepository.findById(book.getId());
	
	if (!b.isPresent()) throw new RuntimeException("ID ERRATO!!!");
	
	Book bo =  b.get();
	
	BeanUtils.copyProperties(book, bo);
	
	bookRepository.save(bo);
	
	System.out.println("updated book : " + bo);
	
	return book;
}
	@Transactional
	public void deleteBook(Integer id) {
		bookRepository.deleteById(id);
	}

}
