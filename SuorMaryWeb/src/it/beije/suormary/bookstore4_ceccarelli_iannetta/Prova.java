package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.util.ArrayList;
import java.util.List;

public class Prova {

	public static void main(String[] args) {
		EcommerceManager em = new EcommerceManager();
		
		List<Integer> bookListId = new ArrayList<>();
		bookListId.add(3);
		bookListId.add(9);
		bookListId.add(11);
		bookListId.add(31);
		
		List<Integer> bookListQuantity = new ArrayList<>();
		bookListQuantity.add(1);
		bookListQuantity.add(1);
		bookListQuantity.add(2);
		bookListQuantity.add(2);
		
		int userId = 5;
		String shippingAddress = "indirizzo di prova";
		String paymentType = "cash";
		
		em.instantBuy(bookListId, bookListQuantity, userId, shippingAddress, paymentType);
		
//		System.out.println(em.userOrders(5));

	}

}
