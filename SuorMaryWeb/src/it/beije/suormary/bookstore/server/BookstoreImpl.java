package it.beije.suormary.bookstore.server;

import java.util.List;

import javax.jws.WebService;

import it.beije.suormary.bookstore.entities.Author;
import it.beije.suormary.bookstore.entities.Book;
import it.beije.suormary.bookstore.entities.CartItem;
import it.beije.suormary.bookstore.entities.Order;
import it.beije.suormary.bookstore.entities.OrderItem;
import it.beije.suormary.bookstore.entities.User;
import it.beije.suormary.bookstore.utils.AuthorUtils;
import it.beije.suormary.bookstore.utils.CartUtils;
import it.beije.suormary.bookstore.utils.UserUtils;

import it.beije.suormary.bookstore.utils.BookUtils;
import it.beije.suormary.bookstore.utils.UserUtils;
import it.beije.suormary.bookstore.utils.OrderUtils;

@WebService(endpointInterface = "it.beije.suormary.bookstore.server.Bookstore")
public class BookstoreImpl implements Bookstore{

	@Override
	public List<Book> getAllBooks() {
		return BookUtils.getAllBooks();
	}

	@Override
	public Book getBook(int id) {
		return BookUtils.getBook(id);
	}

	@Override
	public boolean addNewBook(String title, String description, String editor, double price, int quantity, int authorId) {
		return BookUtils.addNewBook(title, description, editor, price, quantity, authorId);
		
	}

	@Override
	public boolean addAuthor(String name, String surname, String description) {
		return AuthorUtils.addAuthor(name, surname, description);
		
	}

	@Override
	public List<Author> getAuthorList() {
		List<Author> authors = AuthorUtils.getAuthorList();
		return authors;
	}

	@Override
	public boolean createOrder(String address, int userId) {
		return OrderUtils.createOrder(address, userId);
		
	}

	@Override
	public boolean deleteOrder(int idOrder) {
		return OrderUtils.deleteOrder(idOrder);
		
	}

	@Override
	public boolean editStatus(Character status, int orderId) {
		return OrderUtils.editStatus(status, orderId);
		
	}

	@Override
	public List<Order> getOrders(int userId) {
		return OrderUtils.getOrders(userId);
	}

	@Override
	public Order getOrder(int orderId) {
		return OrderUtils.getOrder(orderId);
	}

	@Override
	public User checkUser(String email, String password) {
		return UserUtils.checkUser(email, password);
	}

	@Override
	public boolean userExists(String email) {
		return UserUtils.userExists(email);
	}

	@Override
	public int getUserId(String email) {
		int userId = UserUtils.getUserId(email);
		return userId;
	}

	@Override
	public boolean createUser(String email, String password, String name, String surname) {
		return UserUtils.createUser(email, password, name, surname);
	}

	@Override
	public List<CartItem> getCart(int userId) {
		return CartUtils.getCartItems(userId);
	}

	@Override
	public boolean addCartItem(Integer userId, Integer bookId, Integer quantity) {
		return CartUtils.addCartItem(userId, bookId, quantity);
		
	}

	@Override
	public boolean removeCartItem(int itemId) {
		return CartUtils.removeCartItem(itemId);
		
	}
	
	@Override
	public boolean deleteCart(int userId) {
		return CartUtils.deleteCart(userId);
		
	}
	
}
