package it.beije.suormary.bookstore.server;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import it.beije.suormary.bookstore.entities.Author;
import it.beije.suormary.bookstore.entities.Book;
import it.beije.suormary.bookstore.entities.CartItem;
import it.beije.suormary.bookstore.entities.Order;
import it.beije.suormary.bookstore.entities.User;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface Bookstore {
	
	@WebMethod
	List<Book> getAllBooks();
	
	@WebMethod
	Book getBook(int id);
	
	@WebMethod
	void addNewBook(String title, String description, String editor, double price, int quantity, int authorId);
	
	@WebMethod
	void addAuthor(String name, String surname, String description);
	
	@WebMethod
	List<Author> getAuthorList();
	
	@WebMethod
	void createOrder(String address, int userId);
	
	@WebMethod
	void deleteOrder(int idOrder);
	
	@WebMethod
	void editStatus(Character status, int orderId);
	
	@WebMethod
	List<Order> getOrders(int userId);
	
	@WebMethod
	Order getOrder(int orderId);
	
	@WebMethod
	User checkUser(String email, String password);
	
	@WebMethod
	boolean userExists(String email);
	
	@WebMethod
	int getUserId(String email);
	
	@WebMethod
	void createUser(String email, String password, String name, String surname);
	
	@WebMethod
	List<CartItem> getCart(int userId);
	
	@WebMethod
	void addCartItem(Integer userId, Integer bookId, Integer quantity);
	
	@WebMethod
	void removeCartItem(int itemId);
	
	@WebMethod
	void deleteCart(int userId);
	
	
}
