package it.beije.suormary.bookstore2.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.PersistenceManagerJPA;
import it.beije.suormary.bookstore2.model.User;
import it.beije.suormary.bookstore2.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private BookService bookService;
	
    @Autowired
    public UserService(BookService bookService) {
        this.bookService = bookService;
    }
	
    /*
	public void insertUser(User user) {
		EntityManager entityManager = null;
		
		try {
			entityManager = PersistenceManagerJPA.getEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			
			User newUser = new User();
			
			if(user!=null) {
				try {
					newUser.setEmail(user.getEmail());
					newUser.setPassword(user.getPassword());
					newUser.setSurname(user.getSurname());
					newUser.setName(user.getName());
					newUser.setCreationDate(LocalDateTime.now());
					entityManager.persist(newUser); // salva l'user nel database
					transaction.commit();
				} catch (Exception e) {
					System.out.println("Insert non valido: " + user.toString());
					transaction.rollback();
					throw e; //rilancia eccezione al catch più esterno
				} 
					
			} else {
				System.out.println("User mancante");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/
    
    public String save(User user) {
    	User newUser = userRepository.save(user);
    	if (newUser == null) return "Utente non inserito correttamente";
    	else return "Utente inserito correttamente";
    }
	
	
	/*public User checkUser(String email, String password) {
		EntityManager entityManager = null;
		User user=null;
		try {
			entityManager = PersistenceManagerJPA.getEntityManager();
			// Recupera l'utente dal database utilizzando JPA

	        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
	        query.setParameter("email", email);
	        query.setParameter("password", password);
	        user = (User) query.getSingleResult();
	        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				entityManager.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}*/
	
	public User findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}
	
	
	public List<Book> getTheBooksFromId(List<Integer> ids) {
		
		List<Book> books = new ArrayList<>();
		Book book = null;
		for (int i : ids) {
			book = bookService.findBook(i);
			books.add(book);
		}
		
		return books;
	}
	

}
