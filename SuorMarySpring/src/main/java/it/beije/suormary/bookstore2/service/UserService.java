package it.beije.suormary.bookstore2.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore2.model.Book;
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
	
    
    public String save(User user) {
    	User newUser = userRepository.save(user);
    	if (newUser == null) return "Utente non inserito correttamente";
    	else return "Utente inserito correttamente";
    }
    
    public User findById(Integer id) {
    	Optional<User> u = userRepository.findById(id);
		User user = u.isPresent() ? u.get() : null;
		return user;
    }
	
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
