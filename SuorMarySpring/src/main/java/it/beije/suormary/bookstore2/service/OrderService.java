package it.beije.suormary.bookstore2.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	public Order insertOrder(Integer userId, Map<Integer, Integer> cart, String shippingAddress) {
		Order orderTemp = new Order();
		orderTemp.setUserId(userId);
        orderTemp.setDate(LocalDateTime.now());
        orderTemp.setStatus("I"); 
        orderTemp.setShippingAddress(shippingAddress);
        
        double totalAmount = 0.0;
        
        List<OrderItem> orderItems = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            Integer bookId = entry.getKey();
            int quantity = entry.getValue();
            Book book = this.getBookInOrderByBookId(bookId);
            double price = book.getPrice();
            
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(bookId);
            orderItem.setQuantity(quantity);
            orderItem.setPrice(price);
            orderItems.add(orderItem);

            totalAmount += price * quantity;
        }

        orderTemp.setAmount(totalAmount);
        Order newOrder = orderRepository.save(orderTemp);
        
        for (OrderItem oi : orderItems) {
        	oi.setOrderId(newOrder.getId());
        	//orderItemRepository.save(oi);
			Book book = bookService.findBook(oi.getBookId());
			book.setQuantity(book.getQuantity()-oi.getQuantity());
			bookService.save(book);
		}
        orderTemp.setItems(orderItems);
        
		return newOrder;
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
