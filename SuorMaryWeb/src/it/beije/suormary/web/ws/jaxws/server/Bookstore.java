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

	//BOOK
	@WebMethod
	public String getBooks();
	
	@WebMethod
	public String addBook(String title, String description, int idAuthor, String editor, double price, int quantity);
	
	@WebMethod
	public String getOrders(int userId);
	
	
	//AUTHOR	
	@WebMethod
    public String getAuthors();

    @WebMethod
    public String addAuthor(String name, String surname, String description);
	
    
    //USER
	@WebMethod
    public String login(String email, String password);
    
	@WebMethod
    public String signup(String name, String surname, String email, String password);
	
}
