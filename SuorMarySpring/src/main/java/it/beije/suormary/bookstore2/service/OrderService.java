package it.beije.suormary.bookstore2.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.OrderItem;
import it.beije.suormary.bookstore2.repository.BookRepository;
import it.beije.suormary.bookstore2.repository.OrderItemRepository;
import it.beije.suormary.bookstore2.repository.OrderRepository;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	private BookService bookService;
	
    @Autowired
    public OrderService(BookService bookService) {
        this.bookService = bookService;
    }
	

	public List<Order> getOrderList(Integer userId){
		List<Order> orders = orderRepository.findOrderListByUserId(userId);
		return orders;
	}
	
	@Transactional
	public Order getOrderById(Integer orderId) {
		Optional<Order> o = orderRepository.findById(orderId);
		
		Order order = o.isPresent() ? o.get() : null; 
		System.out.println(order.getItems());
		return order;
	}
	
	public List<Book> booksInOrder(List<OrderItem> orderItems) {
		List<Book> books = new ArrayList<>();
		Optional<Book> book = null;
		for (OrderItem oi: orderItems) {
			book = bookRepository.findById(oi.getBookId());
			Book b = book.isPresent() ? book.get() : null;
			books.add(b);
		}
		
	
		return books;
	}
	
	public void deleteOrderById(Integer orderId) {
		orderRepository.deleteById(orderId);
	}
	
	public List<OrderItem> getOrderItemsInOrder(Integer orderId) {
		return orderRepository.findOrderItemsOfAOrder(orderId);
		
	}
	
	public Book getBookInOrderByBookId(Integer bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		Book b = book.isPresent() ? book.get() : null;
		return b;
	}
	
	@Transactional
	public void insertOrder(Order order) {
		orderRepository.save(order);
	}
	

	@Transactional
	public void inserOrderItems(List<OrderItem> orderItems) {
		for (OrderItem oi : orderItems) {
			orderItemRepository.save(oi);
		}
	}

}
