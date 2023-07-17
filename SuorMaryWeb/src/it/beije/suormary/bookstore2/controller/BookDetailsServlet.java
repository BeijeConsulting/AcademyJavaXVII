package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.suormary.bookstore2.model.Author;
import it.beije.suormary.bookstore2.model.Book;

/**
 * Servlet implementation class BookDetailsServlet
 */
@WebServlet("/bookstoreBookDetails")
public class BookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookDetailsServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	int bookId = Integer.parseInt(request.getParameter("id"));

 
        Book book = BookstoreUtility.findBook(bookId);
        Author author = BookstoreUtility.findAuthorFromId(book.getAuthorId());
        System.out.println("book: " + book);
        request.setAttribute("book", book);
        request.setAttribute("author", author);

        // Forward the request to the JSP page for displaying book details
        request.getRequestDispatcher("bookstoreBookDetails.jsp").forward(request, response);
    
    }

   
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
