package it.beije.suormary.bookstore.server;

import javax.xml.ws.Endpoint;


public class BookstorePublisher {
	
	  public static void main(String[] args) {
	    	System.out.println("Bookstore Publisher running...");
	        Endpoint ep = Endpoint.create(new BookstoreImpl());
	        ep.publish("http://localhost:9000/bookstoreServer");
	    }
	
}
