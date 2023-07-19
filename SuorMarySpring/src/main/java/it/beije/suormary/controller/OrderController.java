package it.beije.suormary.controller;

import java.util.ArrayList;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		}
		
		return "recap";
	}
	  @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
	   public String deleteOrder(HttpSession session, Model model) {
	        int orderId= (int) session.getAttribute("orderId");			
			orderService.deleteOrder(orderId);
	        model.addAttribute("deleteOrder", "L`ordine è stato cancellato");
	        
			return "welcome";	
	   }


}
