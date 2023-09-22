

package it.beije.suormary.web.ws.jaxws.server;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
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
	public String addBook(@WebParam(name = "title") String title,@WebParam(name = "description") String description,@WebParam(name = "author id") int idAuthor, @WebParam(name = "editor") String editor, @WebParam(name = "price") double price, @WebParam(name = "quantity") int quantity);
	
	@WebMethod
	public String getOrders(@WebParam(name = "user id") int userId);
	
	
	//AUTHOR	
	@WebMethod
    public String getAuthors();

    @WebMethod
    public String addAuthor( @WebParam(name = "name") String name, @WebParam(name = "surname") String surname, @WebParam(name = "description") String description);
	
    
    //USER
	@WebMethod
    public String login(@WebParam(name = "email") String email, @WebParam(name = "password") String password);
    
	@WebMethod
    public String signup(@WebParam(name = "name") String name,@WebParam(name = "surname")  String surname, @WebParam(name = "email") String email,@WebParam(name = "password")  String password);
	
}