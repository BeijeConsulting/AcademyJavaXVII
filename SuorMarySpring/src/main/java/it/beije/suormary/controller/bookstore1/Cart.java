package it.beije.suormary.controller.bookstore1;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Cart {
	
	private Cart() {}
	
	public static Map<Integer,Integer> getCart(HttpSession session){
		Map<Integer,Integer> cart = (Map<Integer,Integer>)session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
}
