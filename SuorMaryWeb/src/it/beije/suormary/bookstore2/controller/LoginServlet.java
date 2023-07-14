package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/bookstoreLogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginServlet doGet");

		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");

		if (email != null) { // utente loggato
			response.sendRedirect("bookstoreWelcome.jsp");
		} else { // non loggato
			response.sendRedirect("bookstoreLogin.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("LoginServlet doPost");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("email : " + email);
		System.out.println("password : " + password);

		HttpSession session = request.getSession();
		System.out.println("JSESSIONID: " + session.getId());

		
		if (email != null && email.equalsIgnoreCase("pippo@beije.it") && password != null && password.equals("12345")) { // OK
			session.setAttribute("email", email);

			response.sendRedirect("bookstoreWelcome.jsp");
		} else { // KO
			session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");

			response.sendRedirect("bookstoreLogin.jsp");
		}

	}

}
