package it.beije.suormary.dumpster.bookstore1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewAuthorServlet
 */
@WebServlet("/NewAuthorServlet")
public class NewAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewAuthorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("./new_author.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String description = request.getParameter("description");
		
		AuthorUtils.addAuthor(name, surname, description);
		
		request.getSession().setAttribute("newAuthorMessage", "L'autore " + name + " " + surname + " è stato inserito con successo.");
		
		response.sendRedirect("./NewAuthorServlet");
	}

}
