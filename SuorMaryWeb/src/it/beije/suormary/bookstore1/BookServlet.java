package it.beije.suormary.bookstore1;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Author> listAuthor = (List<Author>)AuthorUtils.getAuthorList();
		request.getSession().setAttribute("listAuthor", listAuthor);
		request.getRequestDispatcher("./book.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//(String title, String description, String editor, double price, int quantity, Author a
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String editor = request.getParameter("editor");
		double price = Double.valueOf(request.getParameter("price"));
		int quantity = Integer.valueOf(request.getParameter("quantity"));
		int idAuthor = Integer.valueOf(request.getParameter("author"));
		BookUtils.addNewBook(title, description, editor, price, quantity, idAuthor);
		request.getSession().setAttribute("newBookMessage", "Il libro " + title + " è stato inserito con successo.");
		response.sendRedirect("./BookServlet");
	}

}
