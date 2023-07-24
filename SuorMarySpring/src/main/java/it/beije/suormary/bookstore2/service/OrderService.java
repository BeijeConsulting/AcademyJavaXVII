package it.beije.suormary.bookstore2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.suormary.bookstore2.model.Book;
import it.beije.suormary.bookstore2.model.Order;
import it.beije.suormary.bookstore2.model.OrderItem;
import it.beije.suormary.bookstore2.repository.OrderItemRepository;
import it.beije.suormary.bookstore2.repository.OrderRepository;

@Service
public class OrderService {

	
	@Autowired
	private OrderRepository orderRepository;
	
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
		Book book = null;
		for (OrderItem oi: orderItems) {
			book = bookService.findBook(oi.getBookId());
			books.add(book);
			}
		
		return books;
	}
	
	
	public List<OrderItem> getOrderItemsInOrder(Integer orderId) {
		return orderRepository.findOrderItemsOfAOrder(orderId);
		
	}
	
	public Book getBookInOrderByBookId(Integer bookId) {
		return bookService.findBook(bookId);
		
	}
	
	@Transactional
	public Order insertOrder(Order order) {
		return orderRepository.save(order);

	}
	
	

	@Transactional
	public void inserOrderItems(List<OrderItem> orderItems) {
		for (OrderItem oi : orderItems) {
			orderItemRepository.save(oi);
			Book book = bookService.findBook(oi.getBookId());
			book.setQuantity(book.getQuantity()-oi.getQuantity());
			bookService.save(book);
		}
	
	}
	
	@Transactional
	public void cancelOrder(Integer orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		List<OrderItem> items = order.get().getItems();
		System.out.println("items in cancelOrder " + items);
		Book book = null;
		if(order.isPresent()) {
			order.get().setStatus("C");
			order.get().setItems(items);
			for (OrderItem orderItem : items) {
				book = bookService.findBook(orderItem.getBookId());
				book.setQuantity(book.getQuantity() + orderItem.getQuantity());
				bookService.save(book);
        }
			
		}
		
	}

}
