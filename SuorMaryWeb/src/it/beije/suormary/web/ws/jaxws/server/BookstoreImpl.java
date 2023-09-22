package it.beije.suormary.web.ws.jaxws.server;

import java.util.List;

import javax.jws.WebService;

import it.beije.suormary.bookstore4_ceccarelli_iannetta.Book;
import it.beije.suormary.bookstore4_ceccarelli_iannetta.Author;
import it.beije.suormary.bookstore4_ceccarelli_iannetta.EcommerceManager;
import it.beije.suormary.bookstore4_ceccarelli_iannetta.Order;

@WebService(endpointInterface = "it.beije.suormary.web.ws.jaxws.server.Bookstore")
public class BookstoreImpl implements Bookstore{

	EcommerceManager manager = new EcommerceManager();
	
	
	//BOOK
	@Override
	public String getBooks() {
		StringBuilder result = new StringBuilder();
		List <Book> list = manager.listBook();
		for (Book i : list) result.append(i.toString() + "\n");
		return result.toString();
	}

	@Override
	public String getOrders(int userId) {
		StringBuilder result = new StringBuilder();
		List <Order> list = manager.userOrders(userId);
		for (Order i : list) result.append(i.toString() + "\n");
		return result.toString();
	}

	@Override
	public String addBook(String title, String description, int idAuthor, String editor, double price, int quantity) {
		Book book = manager.addBook(title, description, idAuthor, editor, price, quantity);
		return (book == null ) ? "Something went wrong" : "Book added";
	}

	
	//AUTHOR
	@Override
    public String getAuthors() {
        StringBuilder result = new StringBuilder();

        List<Author> authors = manager.listAuthor();
        for (Author a : authors) result.append(a.toString() + "\n");
        return result.toString();
    }

    @Override
    public String addAuthor(String name, String surname, String description) {
        String result;
        Author author = manager.addAuthor(name, surname, description);

        if(author!=null) {
            result = "Autore inserito correttamente!";
        } else {
            result = "Operazione non riuscita";
        }
        return result;
    }
    
    
    //USER
    @Override
    public String login(String email, String password) {
        if(manager.isUser(email, password) == null) return "error";
        else return "You are now logged";
    }

    @Override
    public String signup(String name, String surname, String email, String password) {
        if(manager.insertUser(name, surname, email, password) == null) return "error";
        else return "You are now registered";
    }
}
