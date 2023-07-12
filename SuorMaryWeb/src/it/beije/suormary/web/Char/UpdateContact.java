package it.beije.suormary.web.Char;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateContact
 */
@WebServlet("/updateContact")
public class UpdateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager entityManager = JPAmanagerFactory.createEntityManager();
		 String name = request.getParameter("name");
		 String surname = request.getParameter("surname");
		 String email = request.getParameter("email");
		 String phone = request.getParameter("phone");
		 String note = request.getParameter("note");
		 String id = request.getParameter("id");
		 RubricaJPA.updateContact(id,name, surname, email, phone, note, entityManager);
		 response.sendRedirect("home.jsp");
	}

}