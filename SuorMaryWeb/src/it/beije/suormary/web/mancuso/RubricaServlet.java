package it.beije.suormary.web.mancuso;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RubricaServlet
 */
@WebServlet("/RubricaServlet")
public class RubricaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RubricaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Contact> contacts = JPAUtils.getAllContacts();
		request.setAttribute("contacts", contacts);
		request.getRequestDispatcher("./rubrica.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idContatto = Integer.valueOf((String)(request.getParameter("idContatto")));
		
		JPAUtils.deleteContact(idContatto);
		
		request.getSession().setAttribute("message", "Contatto eliminato");
		
		response.sendRedirect("./rubrica.jsp");
	}

}