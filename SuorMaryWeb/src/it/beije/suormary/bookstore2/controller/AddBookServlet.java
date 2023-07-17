package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/bookstoreInsertABook")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Author> authors = BookstoreUtility.getAllAuthor(); 
		System.out.println(authors);
        
        request.setAttribute("authors", authors);
       
        request.getRequestDispatcher("/bookstoreInsertABook.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String descpription = request.getParameter("description");
		String a = request.getParameter("author");
		int authorId = Integer.parseInt(a);
		String editor = request.getParameter("editor");
		String p = request.getParameter("price");
		int price = Integer.parseInt(p);
		String q = request.getParameter("quantity");
		int quantity = Integer.parseInt(q);
		
		Book book = new Book();
		book.setTitle(title);
		book.setDescription(descpription);
		book.setAuthorId(authorId);
		book.setEditor(editor);
		book.setPrice(price);
		book.setQuantity(quantity);
		
		BookstoreUtility.insertABook(book);
		
		
		 response.sendRedirect("bookstoreWelcome");
	}

}
