package it.beije.suormary.bookstore.server;

import java.util.List;

import javax.jws.WebService;

import it.beije.suormary.bookstore.entities.Author;
import it.beije.suormary.bookstore.entities.Book;
import it.beije.suormary.bookstore.entities.Cart;
import it.beije.suormary.bookstore.entities.CartItem;
import it.beije.suormary.bookstore.entities.Order;
import it.beije.suormary.bookstore.entities.OrderItem;
import it.beije.suormary.bookstore.entities.User;
import it.beije.suormary.bookstore.utils.AuthorUtils;
import it.beije.suormary.bookstore.utils.CartUtils;
import it.beije.suormary.bookstore.utils.UserUtils;

@WebService(endpointInterface = "it.beije.suormary.bookstore.server.Bookstore")
public class BookstoreImpl implements Bookstore{

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBook(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewBook(String title, String description, String editor, double price, int quantity, int authorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAuthor(String name, String surname, String description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Author> getAuthorList() {
		List<Author> authors = AuthorUtils.getAuthorList();
		return authors;
	}

	@Override
	public void createOrder(String address, int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(int idOrder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editStatus(Character status, int orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> getOrders(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User checkUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean userExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getUserId(String email) {
		int userId = UserUtils.getUserId(email);
		return userId;
	}

	@Override
	public void createUser(String email, String password, String name, String surname) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cart getCart(int userId) {
		return CartUtils.getCart(userId);
	}

	@Override
	public void addCartItem(CartItem cartItem) {
		CartUtils.addCartItem(cartItem);
		
	}

	@Override
	public void removeCartItem(int itemId) {
		CartUtils.removeCartItem(itemId);
		
	}
	
	@Override
	public void deleteCart(int userId) {
		CartUtils.deleteCart(userId);
		
	}
	
}
