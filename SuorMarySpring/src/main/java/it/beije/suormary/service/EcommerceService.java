package it.beije.suormary.service;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import it.beije.suormary.model.Book;
import it.beije.suormary.model.JPAEntityFactory;


@Service
public class EcommerceService {

	private EntityManager em;
	
	public List<Book> bookList() {
		em = JPAEntityFactory.openEntity();
    	Query query = em.createQuery("SELECT b from Book as b order by b.title");
    	List<Book> books = query.getResultList();
    	if (books.size() == 0) return null;
    	em.close();
    	return books;
	}
}
