package it.beije.suormary.web.wws.jaxws.test;

import it.beije.suormary.web.ws.jaxws.client.Bookstore;
import it.beije.suormary.web.ws.jaxws.client.BookstoreImplService;

public class Test {

	public static void main(String args[]) {
		BookstoreImplService bis = new BookstoreImplService();
		
		Bookstore bookstore = bis.getBookstoreImplPort();
		
		
		int userId = 5;
		String shippingAddress = "indirizzo di prova";
		String paymentType = "cash";
		
		System.out.println("Ordini di " + userId);
		System.out.println(bookstore.getOrders(userId));
		
		//separate id e quantità con "-"
		System.out.println("Nuovo ordine");
		System.out.println(bookstore.instantBuy("1-2", "2-2",userId, shippingAddress, paymentType));
		
		
		


		
	}
}
