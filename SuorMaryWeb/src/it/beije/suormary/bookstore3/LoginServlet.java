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
 * Servlet implementation class CheckLogServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("email") != null) {
			response.sendRedirect("welcome");
		}
		else response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		//String password = request.getParameter("password");
		User user = BookStoreUtility.loginUser(email);
		HttpSession session = request.getSession();
		if(user != null) {
		    session.setAttribute("email", email);
			response.sendRedirect("welcome");
		}
		else {
			session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");

			response.sendRedirect("login.jsp");
		}
		
	}

}
