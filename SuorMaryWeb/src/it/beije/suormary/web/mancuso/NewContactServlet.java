package it.beije.suormary.web.mancuso;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NewContactServlet
 */
@WebServlet("/NewContactServlet")
public class NewContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("./nuovoContatto.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("nome");
		String surname = request.getParameter("cognome");
		String notes = request.getParameter("note");
		
		Contact c = new Contact();
		c.setFirstName(name);
		c.setLastName(surname);
		c.setNotes(notes);
		
		int id = JPAUtils.addContact(c);
		
		
		
		request.getSession().setAttribute("message", "Contatto inserito correttamente");
		
		response.sendRedirect("./EditServlet?id=" + request.getParameter("id"));
	}

}
