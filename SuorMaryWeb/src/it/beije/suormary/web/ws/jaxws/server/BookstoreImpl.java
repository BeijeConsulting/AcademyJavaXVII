package it.beije.suormary.web.ws.jaxws.server;

import java.util.List;

import javax.jws.WebService;

import it.beije.suormary.bookstore4_ceccarelli_iannetta.Book;
import it.beije.suormary.bookstore4_ceccarelli_iannetta.EcommerceManager;

@WebService(endpointInterface = "it.beije.suormary.web.ws.jaxws.server.Bookstore")
public class BookstoreImpl implements Bookstore{

	EcommerceManager manager = new EcommerceManager();
	
	@Override
	public String getBooks() {
		StringBuilder result = new StringBuilder();
		List <Book> list = manager.listBook();
		for (Book i : list) result.append(i.toString() + "\n");
		return result.toString();
	}

}
