package it.beije.suormary.web;

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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doGet");
		
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		if (username != null) { //utente loggato
			response.sendRedirect("welcome.jsp");
		} else { //non loggato
			response.sendRedirect("login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		
		HttpSession session = request.getSession();
		System.out.println("JSESSIONID: " + session.getId());
		
		//SELECT * from Users WHERE username = :username AND password = :password
		if (username != null && username.equalsIgnoreCase("martina") && password != null && password.equals("12345")) { //OK
			session.setAttribute("username", username);
			
			response.sendRedirect("welcome.jsp");
		} else { //KO
			session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");

			response.sendRedirect("login.jsp");
		}
		
	}

}
//@WebServlet("/logina")
//public class LoginServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("LoginServlet doGet");
//		
//		HttpSession session = request.getSession();
//		String username = (String) session.getAttribute("username");
//		
//		if (username != null) { //utente loggato
//			response.sendRedirect("welcome.jsp");
//		} else { //non loggato
//			response.sendRedirect("login.jsp");
//		}
//		
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("LoginServlet doPost");
//		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		System.out.println("username : " + username);
//		System.out.println("password : " + password);
//		
//		HttpSession session = request.getSession();
//		System.out.println("JSESSIONID: " + session.getId());
//		
//		//SELECT * from Users WHERE username = :username AND password = :password
//		if (username != null && username.equalsIgnoreCase("pippo@beije.it") && password != null && password.equals("12345")) { //OK
//			session.setAttribute("username", username);
//			
//			response.sendRedirect("welcome.jsp");
//		} else { //KO
//			session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");
//
//			response.sendRedirect("login.jsp");
//		}
//		
//	}
//
//}
