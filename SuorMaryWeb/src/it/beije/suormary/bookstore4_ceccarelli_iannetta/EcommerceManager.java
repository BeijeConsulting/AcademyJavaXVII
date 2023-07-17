package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.time.LocalDateTime;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transaction;



public class EcommerceManager {

    private EntityManager em;
    private EntityTransaction transaction;
    
    public User isUser(String email, String password) {
    	em = JPAEntityFactory.openEntity();
       //transaction = em.getTransaction();
        //transaction.begin();
        
        //Query query = em.createQuery("SELECT u from User as u");
    	Query query = em.createQuery("SELECT u from User as u WHERE u.email = :email AND password = :password");

    	query.setParameter("email", email);
    	query.setParameter("password", password);

    	
    	List<User> users = query.getResultList();
    	
    	//for (User u : users) System.out.println(u);
    	if (users.size() == 0) return null;
    	
    	//transaction.commit();
    	em.close();
    	//System.out.println("USER:" + users.get(0));
    	return users.get(0);
    }
    
    public User insertUser(String name, String surname, String email, String password) {
    	em = JPAEntityFactory.openEntity();
        transaction = em.getTransaction();
        transaction.begin();
//        Query<User> query = session.createQuery("SELECT u FROM User as u "
//				  + "WHERE email=:email");
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
    	user.setPassword(password);
    	user.setCreationDate(LocalDateTime.now());
    	
    	try {
    		em.persist(user);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    		user = null;
    	} finally {
        	em.close();    		
    	}
    
    	return user;
    }
    
    public List<Book> listBook(){
    	em = JPAEntityFactory.openEntity();
    	Query query = em.createQuery("SELECT b from Book as b order by b.title");
    	List<Book> books = query.getResultList();
    	if (books.size() == 0) return null;
    	em.close();
    	return books;
    }
    
    public List<Author> listAuthor(){
	   	em = JPAEntityFactory.openEntity();
       //transaction = em.getTransaction();
        //transaction.begin();

    	Query query = em.createQuery("SELECT a from Author as a order by a.surname");
    	List<Author> authors = query.getResultList();
    	if (authors.size() == 0) return null;
    	
    	//transaction.commit();
    	em.close();
    	return authors;
	}
        
    public Author addAuthor(String name, String surname, String description) {
    	Author author = new Author();
    	
    	author.setName(name);
    	author.setSurname(surname);
    	author.setDescription(description);
    	
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	try {
    		em.persist(author);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    		author = null;
    	} finally {
        	em.close();    		
    	}
    	return author;
    	
    }
    
    public Book addBook(String title, String description, int idAuthor, String editor, double price, int quantity) {
    	Book book = new Book();
    	
    	book.setTitle(title);
    	
    	book.setAuthorId(idAuthor);
    	book.setDescription(description);
    	book.setEditor(editor);
    	book.setPrice(price);
    	book.setQuantity(quantity);
    	
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	try {
    		em.persist(book);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    		book = null;
    	} finally {
        	em.close();    		
    	}
    	return book;
    }

    public void updateBook(int bookId, double price, int quantity) {
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	Book book = em.find(Book.class, bookId);
   
    	book.setPrice(price);
    	book.setQuantity(quantity);
    	
    	try {
    		em.persist(book);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    	} finally {
        	em.close();    		
    	}
    }
    
    public void updateAuthor(int authorId, String description) {
    	em = JPAEntityFactory.openEntity();
    	EntityTransaction transaction = em.getTransaction();
    	transaction.begin();
    	
    	Author author = em.find(Author.class, authorId);
   
    	author.setDescription(description);
    	
    	try {
    		em.persist(author);
    		transaction.commit();
    	} catch (Exception e) {
    		System.out.println("Non va bene");
    	} finally {
        	em.close();    		
    	}
    }
}
















