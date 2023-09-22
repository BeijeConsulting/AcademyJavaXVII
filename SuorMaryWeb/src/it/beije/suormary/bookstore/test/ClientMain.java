package it.beije.suormary.bookstore.test;

import it.beije.suormary.bookstore.client.Bookstore;
import it.beije.suormary.bookstore.client.BookstoreImplService;

public class ClientMain {

	public static void main(String[] args) {
		BookstoreImplService service = new BookstoreImplService();
        Bookstore bookstore = service.getBookstoreImplPort();
        
        System.out.println("Book to string: " + bookstore.getBook(1).toString());
        System.out.println("Book: " + bookstore.getBook(1));
        System.out.println("Book: " + bookstore.getBook(1).getTitle());
	}

}
