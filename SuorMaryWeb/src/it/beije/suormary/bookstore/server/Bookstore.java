package it.beije.suormary.bookstore.server;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.servlet.http.HttpServletRequest;

import it.beije.suormary.bookstore.entities.Author;
import it.beije.suormary.bookstore.entities.Book;
import it.beije.suormary.bookstore.entities.Cart;
import it.beije.suormary.bookstore.entities.CartItem;
import it.beije.suormary.bookstore.entities.Order;
import it.beije.suormary.bookstore.entities.OrderItem;
import it.beije.suormary.bookstore.entities.User;

@WebService
@SOAPBinding(style = Style.RPC)
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
	List<OrderItem> getOrderItems(int orderId);
	
	@WebMethod
	User checkUser(String email, String password);
	
	@WebMethod
	boolean userExists(String email);
	
	@WebMethod
	int getUserId(String email);
	
	@WebMethod
	void createUser(String email, String password, String name, String surname);
	
	@WebMethod
	Cart getCart(int userId);
	
	@WebMethod
	void addCartItem(int userId, CartItem cartItem);
	
	@WebMethod
	void removeCartItem(int userId, CartItem cartItem);
	
	
}
