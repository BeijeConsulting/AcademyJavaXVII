package it.beije.suormary.bookstore1.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dumpster.bookstore1.BookUtils;
import dumpster.bookstore1.OrderItemUtils;
import dumpster.bookstore1.UserUtils;
import it.beije.suormary.bookstore1.model.Book;
import it.beije.suormary.bookstore1.model.Cart;
import it.beije.suormary.bookstore1.model.Order;
import it.beije.suormary.bookstore1.model.OrderItem;
import it.beije.suormary.bookstore1.repository.BookRepository;
import it.beije.suormary.bookstore1.repository.OrderRepository;
import it.beije.suormary.bookstore1.repository.UserRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookService bookService;
	
	@Transactional
	public Order createOrder(String address, HttpSession session) {
			
			
			try {
				int id = userService.getUserId((String)session.getAttribute("email"));
				
				Order order = new Order();
						
				order.setUserId(id);
				order.setShippingAddress(address);
				order.setDate(LocalDateTime.now());
				order.setStatus("I");
				
				Map<Integer,Integer> cart = Cart.getCart(session);
				
				Map<Book,Integer> books = bookService.getBooks(cart);
				
				double amount = 0;
				for (Map.Entry<Book, Integer> entry : books.entrySet()) {
					double price = entry.getKey().getPrice() * entry.getValue();
					amount += price;
				}
				
				order.setAmount(amount);
				orderRepository.save(order);
				
				orderRepository.flush();
				
				order.setItems(createOrderItems(order.getId(), books));
				orderRepository.save(order);
				return order;
			} catch (Exception e){
				e.printStackTrace();
				return null;
				//TRANSACTION ROLLBACK DA CAPIRE COME FARE!!!!
			}
		
		
	}
	
	public List<OrderItem> createOrderItems(Integer orderId, Map<Book,Integer> books) throws Exception {

			System.out.println("Item iniziati");
			OrderItem om = null;
			List<OrderItem> oi = new ArrayList<>();
			Book book = null;
			for (Map.Entry<Book, Integer> entry : books.entrySet()) {
				om = new OrderItem();
				book=bookService.getBookById(entry.getKey().getId());
				
				om.setOrderId(orderId);
				om.setPrice(entry.getKey().getPrice());
				om.setQuantity(entry.getValue());
				
				
				if(book==null) {
					throw new Exception("non è stato trovato un libro dal carrello");
				} 
				om.setBook(book);
				oi.add(om);
				
				om.getBook().setQuantity(book.getQuantity() - entry.getValue());
				
				bookRepository.save(book);
			}
			System.out.println("Item finiti");
			return oi;

	}
	
	@Transactional
	public void deleteOrder(int idOrder) {
		Order order = null;
		Optional<Order> optional = orderRepository.findById(idOrder);
		try {
			if(optional.isPresent()) {
				order=optional.get();
				orderRepository.delete(order);
			} else {
				throw new Exception("stai provando a cancellare un ordine inesistente");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public Order editStatus(String status, int orderId) {
		try {
			Order order = null;
			Optional<Order> optional = orderRepository.findById(orderId);
			if(optional.isPresent()) {
				order=optional.get();
				order.setStatus(status);
				orderRepository.save(order);
				return order;
			}else {
				throw new Exception("non è stato trovato un ordine con l'id fornito");
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Transactional
	public List<Order> getOrders(int userId){
		List<Order> lo= orderRepository.findAll();
		for(int i = 0; i<lo.size(); i++) {
			lo.get(i).getItems();
			System.out.println(lo);
			//System.out.println(lo.get(i).getItems());
			//Book b = null;
			for(int j=0; j<lo.get(i).getItems().size(); j++) {
				/*Optional<Book> book =bookRepository.findById(lo.get(i).getItems().get(j).getBookId());
				if(book.isPresent()) {
					b=book.get();
				}
				lo.get(i).getItems().get(j).setBook(b);*/
				//lo facciamo in caso di LAZY
				lo.get(i).getItems().get(j).getBook();
			}
		}
		return lo;
	}
	
	@Transactional
	public List<Order> getAllOrders(){
		List<Order> lo= orderRepository.findAll();
		for(int i = 0; i<lo.size(); i++) {			
			System.out.println(lo.get(i).getItems());
			for(int j=0; j<lo.get(i).getItems().size(); j++) {
				System.out.println(lo.get(i).getItems().get(j).getBook());
			}
		}
		return lo;
	}
	
	@Transactional
	public Order getOrder(Integer id) {
		Optional<Order> optOrder = orderRepository.findById(id);
		if(optOrder.isPresent()) {
			System.out.println(optOrder.get().getItems());
			return optOrder.get();
		}else {
			return null;
		}
	}
	
	public String getInserted() {
		return "I";
	}
	
	public String getPaid() {
		return "P";
	}
	
	public String getCancelled() {
		return "C";
	}
	
}
