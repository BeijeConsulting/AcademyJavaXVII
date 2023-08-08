package it.beije.suormary.bookstore3;

import java.io.IOException;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
            if(session.getAttribute("email") != null) {
            	 response.sendRedirect("welcome");
             }
             else response.sendRedirect("register.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		LocalDateTime date = LocalDateTime.now();
		BookStoreUtility.registerUser(name, surname, email, password, date);
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		System.out.println(session.getAttribute("email"));
		response.sendRedirect("welcome");
	}

}
