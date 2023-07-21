package it.beije.suormary.bookstore2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.PersistenceManagerJPA;
import it.beije.suormary.bookstore2.repository.AuthorRepository;
import it.beije.suormary.bookstore2.repository.BookRepository;
import it.beije.suormary.model.Contact;

@Service
public class BookService {
	
	@Autowired
	public BookRepository bookRepository;
	
	@Autowired
	public AuthorRepository authorRepository;
	
	/*
	public List<Book> readBooksFromDb() {
		
		List<Book> newList = new ArrayList<>();
		EntityTransaction transaction = null;
		EntityManager entityManager = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			transaction = entityManager.getTransaction();
			transaction.begin();
			Query query = entityManager.createQuery("SELECT b FROM Book as b");
			newList = query.getResultList();
			for (Book b : newList) {
				System.out.println(b.toString());

			}

			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		System.out.println(newList);

		return newList;
		
	}*/
	
	
	public List<Book> getBookList(){
		List<Book> books = bookRepository.findAll();
		return books;
	}
	
	/*
	public Author findAuthorFromId(int authorId) {

		EntityManager entityManager = null;

		Author author = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			author = entityManager.find(Author.class, authorId);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return author;

	}*/
	
	public Author findAuthorById(Integer id) {
		Optional<Author> a = authorRepository.findById(id);
		Author author = a.isPresent() ? a.get() : null;
		return author;
	}
	
	public Book findBook(int bookId) {

		EntityManager entityManager = null;

		Book book = null;

		try {

			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();

			book = entityManager.find(Book.class, bookId);

			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}

		return book;

	}
	
	
}
