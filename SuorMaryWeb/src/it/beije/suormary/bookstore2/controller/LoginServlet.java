package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.suormary.bookstore2.model.User;

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
		System.out.println("bookstoreLogin doGet");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println("user " + user);
		if (user != null) { // utente loggato
			response.sendRedirect("bookstoreWelcome");
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
		System.out.println("bookstoreLogin doPost");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("email : " + email);
		//System.out.println("password : " + password);

		HttpSession session = request.getSession();
		System.out.println("JSESSIONID: " + session.getId());
		User user = UserUtility.checkUser(email, password);
		
		if (user != null ) { // OK
			user.setPassword("");
			session.setAttribute("user", user);
			response.sendRedirect("bookstoreWelcome");
			
		} else { // KO
			session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");

			response.sendRedirect("bookstoreLogin.jsp");
		}

	}

}
