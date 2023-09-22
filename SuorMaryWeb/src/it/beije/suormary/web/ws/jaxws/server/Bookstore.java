

package it.beije.suormary.web.ws.jaxws.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

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
	
	
	//ORDER
	public String instantBuy(@WebParam(name = "Books ids") String bookListId, @WebParam(name = "Books quantities") String bookListQuantity,@WebParam(name = "user id") int userId, @WebParam(name = "shipping address") String shippingAddress, @WebParam(name = "payment type") String paymentType);
}