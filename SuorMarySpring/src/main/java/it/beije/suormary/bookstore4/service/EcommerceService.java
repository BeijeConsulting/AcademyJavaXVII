package it.beije.suormary.bookstore4.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore4.model.BasketItem;
import it.beije.suormary.bookstore4.model.Book;
import it.beije.suormary.bookstore4.repository.BasketItemRepository;
import it.beije.suormay.bookstore4.repository.BookRepository;


@Service
public class EcommerceService {

	@Autowired
	private BookRepository bookRepo;
	
	public List<Book> bookList() {
    	List<Book> books = bookRepo.findAll();
    	if (books.size() == 0) return null;
    	return books;
	}
	
	@Autowired
	private BasketItemRepository basketItemRepo;
	
	public List<BasketItem> basket(Integer userId) {
    	List<BasketItem> books = basketItemRepo.findByUserId(userId);
    	if (books.size() == 0) return null;
    	return books;
	}
	
}
