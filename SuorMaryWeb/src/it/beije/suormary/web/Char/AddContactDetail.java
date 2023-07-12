package it.beije.suormary.web.Char;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddContactDetail
 */
@WebServlet("/AddContactDetail")
public class AddContactDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddContactDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String id_rubrica = request.getParameter("id_rubrica");
		String tipo = request.getParameter("tipo");
		String contatto = request.getParameter("contatto");
		String label = request.getParameter("label");
		EntityManager entityManager = JPAmanagerFactory.createEntityManager();
		RubricaJPA.createContactDetail(id_rubrica, contatto, tipo, label, entityManager);
		response.sendRedirect("home.jsp");
		
	}

}
