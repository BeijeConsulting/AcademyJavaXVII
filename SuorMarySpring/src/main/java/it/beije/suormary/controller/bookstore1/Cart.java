package it.beije.suormary.controller.bookstore1;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Cart {
	
	private Cart() {}
	
	public static Map<Integer,Integer> getCart(HttpServletRequest request){
		Map<Integer,Integer> cart = (Map<Integer,Integer>)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<>();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
