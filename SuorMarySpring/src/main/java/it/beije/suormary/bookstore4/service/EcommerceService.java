package it.beije.suormary.bookstore4.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore4.model.BasketItem;
import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.repository.BasketItemRepository;
import it.beije.suormary.bookstore4.repository.BookRepository;


@Service
public class EcommerceService {

//	@Autowired
//	private BookRepository bookRepository;
	
//	@Transactional
//	public List<Book> bookList() {
//    	List<Book> books = bookRepository.findAll();
//    	//if (books.size() == 0) return null;
//    	return books;
//	}
	
	@Autowired
	private BasketItemRepository basketItemRepository;
//	
//	public List<BasketItem> basket(Integer userId) {
//    	List<BasketItem> books = basketItemRepository.findByUserId(userId);
//    	if (books.size() == 0) return null;
//    	return books;
//	}
	
}
