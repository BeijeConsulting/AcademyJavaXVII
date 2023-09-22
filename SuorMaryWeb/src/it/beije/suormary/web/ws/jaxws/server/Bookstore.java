package it.beije.suormary.web.ws.jaxws.server;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import it.beije.suormary.bookstore4_ceccarelli_iannetta.*;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface Bookstore {

	//BOOKS
	@WebMethod
	public String getBooks();
	
}
