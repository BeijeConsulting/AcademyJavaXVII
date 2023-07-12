package it.beije.suormary.web.Char;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateContactDetail
 */
@WebServlet("/UpdateContactDetail")
public class UpdateContactDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String contatto = request.getParameter("contatto");
		String tipo = request.getParameter("tipo");
		String label = request.getParameter("label");
		EntityManager entityManager = JPAmanagerFactory.createEntityManager();
		RubricaJPA.updateContactDetail(id, contatto, tipo, label, entityManager);
		response.sendRedirect("home.jsp");
	}

}
