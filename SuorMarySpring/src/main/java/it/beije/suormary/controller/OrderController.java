package it.beije.suormary.controller;

import java.util.List;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.suormary.model.Book;
import it.beije.suormary.model.Order;
import it.beije.suormary.service.BookService;
import it.beije.suormary.service.OrderItemService;
import it.beije.suormary.service.OrderService;
 
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private BookService bookService;
	@Autowired
	private OrderItemService orderItemService;
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.GET)
	public String createOrderGet(HttpSession session, Model model) {
		if(session.getAttribute("email") == null) {
			return "login";
		}
		else {		
		List<Book> booksOrder = new ArrayList<>();
		List<Book> books = BookStoreUtility.loadBooks();
		session.setAttribute("booksOrder", booksOrder);
		model.addAttribute("booksOrder", booksOrder);
		model.addAttribute("books", books);
		String email = (String) session.getAttribute("email");
		Order order = orderService.createOrder(email);
		session.setAttribute("order", order);
		model.addAttribute("orderId", order.getId());
		session.setAttribute("orderId", order.getId());
		return "createOrder";
	    }
	}
	@RequestMapping(value="/addBookToOrder", method = RequestMethod.GET)
	public String addBookToOrder(HttpSession session,HttpServletRequest request, Model model) {
		List<Book> booksOrder = (List)session.getAttribute("booksOrder");
		String id = request.getParameter("bookOrderId");
		String quantity = request.getParameter("quantity");
		Book book = bookService.getBookById(id);
		int quantityInt = Integer.parseInt(quantity);
		book.setQuantity(quantityInt);
		booksOrder.add(book);
		for(Book b : booksOrder) {
			System.out.println(b.getId() +  " " + b.getQuantity());
		}
		session.removeAttribute("quantity");
		List<Book> books = BookStoreUtility.loadBooks();
		model.addAttribute("booksOrder", booksOrder);
		model.addAttribute("books", books);
		Order order = (Order) session.getAttribute("order");
		model.addAttribute("orderId", order.getId());
		session.setAttribute("orderId", order.getId());
		return "createOrder";
		

	}
	@RequestMapping(value="/recapOrder", method = RequestMethod.GET)
	public String recapOrder(HttpSession session,HttpServletRequest request, Model model) {
		if(request.getParameter("order")==null){
			List<Book> booksOrder = (List) session.getAttribute("booksOrder");
			model.addAttribute("booksOrder", booksOrder);
			int orderId = (int) session.getAttribute("orderId");
			orderItemService.createOrderItems(booksOrder,orderId);
			Order order = orderService.findOrder(orderId);
			model.addAttribute("order", order);
		} else {
			String orId = request.getParameter("order");
			int orderId= Integer.parseInt(orId);
			session.setAttribute("orderId", orderId);
			Order order = orderService.findOrder(orderId);
			model.addAttribute("order", order);
		}
		
		return "recap";
	}

	  @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
	   public String deleteOrder(HttpSession session, Model model) {
	        int orderId= (int) session.getAttribute("orderId");			
			orderService.deleteOrder(orderId);
	        model.addAttribute("deleteOrder", "L`ordine è stato cancellato");
	        List<Book> books = bookService.loadBooks();
	        model.addAttribute("books", books);
			return "welcome";	
	   }

    @RequestMapping(value = "/my_orders", method = RequestMethod.GET)
    public String myOrderGet(HttpSession session, Model model) {
        if(session.getAttribute("email") != null) {
        	List<Order> usersOrders = orderService.findByUserId((String) session.getAttribute("email"));
        	model.addAttribute("usersOrder", usersOrders);
        	 return "my_order";
         }
         else return "login";
	} 

    @RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
    public String updateOrder(HttpSession session, Model model) {
		int id = (int) session.getAttribute("orderId");
		Order order = orderService.getOrderById(id);
		model.addAttribute("order", order);
		session.setAttribute("order", order);
		return "updateOrder";
    }
    @RequestMapping(value = "/addOtherBooks", method = RequestMethod.GET)
    public String addOtherBooks(HttpSession session, Model model) {
		List<Book> booksOrder = new ArrayList<>();
		session.setAttribute("booksOrder", booksOrder);
		model.addAttribute("booksOrder", booksOrder);
		 List<Book> books = bookService.loadBooks();
         model.addAttribute("books", books);
		return "addOtherBooks";
    }
    @RequestMapping(value = "/quantityBookModOrder", method = RequestMethod.GET)
    public String quantityBookModOrder(HttpSession session, Model model, HttpServletRequest request) {
		String quantity = request.getParameter("quantity");
		String idStr = request.getParameter("bookId");
		Book book = bookService.getBookById(idStr);
		int quantityId = Integer.parseInt(quantity);
		if(quantityId > book.getQuantity() ) {
			model.addAttribute("ErrorQuantity", "Hai inserito una quantità maggiore rispetto a quelli disponibili");
		}
		else {
			session.setAttribute("quantity", quantityId);
		}
         List<Book> books = bookService.loadBooks();
         model.addAttribute("books", books);
		return "addOtherBooks";
    }
    @RequestMapping(value = "/addBookToModOrder", method = RequestMethod.GET)
    public String addBookToModOrder(HttpSession session, Model model, HttpServletRequest request) {
		List<Book> booksOrder = (List)session.getAttribute("booksOrder");
		String id = request.getParameter("bookOrderId");
		String quantity = request.getParameter("quantity");
		Book book = bookService.getBookById(id);
		int quantityInt = Integer.parseInt(quantity);
		book.setQuantity(quantityInt);
		booksOrder.add(book);
		session.removeAttribute("quantity");
		 List<Book> books = bookService.loadBooks();
         model.addAttribute("books", books);
		return "addOtherBooks";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment(HttpSession session, HttpServletRequest request, Model model) {
		String address = request.getParameter("address");
		int orderId = (int) session.getAttribute("orderId");
		
		orderService.payment(orderId,address);
		
		model.addAttribute("ordinePagato", "Ordine pagato con successo");
		List<Book> books = bookService.loadBooks();
		model.addAttribute("books", books);	
		return"welcome";

		
    }
    @RequestMapping(value = "/saveOrder", method = RequestMethod.GET)
    public String saveOrder(HttpSession session, HttpServletRequest request, Model model) {
    	if(request.getParameter("order")==null){
			List<Book> booksOrder = (List) session.getAttribute("booksOrder");
			model.addAttribute("booksOrder", booksOrder);
			int orderId = (int) session.getAttribute("orderId");
			orderItemService.createOrderItems(booksOrder,orderId);
			Order order = orderService.findOrder(orderId);
			model.addAttribute("order", order);
		} else {
			String orId = request.getParameter("order");
			int orderId= Integer.parseInt(orId);
			session.setAttribute("orderId", orderId);
			Order order = orderService.findOrder(orderId);
			model.addAttribute("order", order);
		}
		
    	 	return "recap";
    }

}
