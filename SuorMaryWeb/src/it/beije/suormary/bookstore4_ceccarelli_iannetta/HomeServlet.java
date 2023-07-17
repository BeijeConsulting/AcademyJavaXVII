package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/homeservlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EcommerceManager em = new EcommerceManager();
	User user = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HomeServlet doGet");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HomeServlet doPost");
		
		//HttpSession session = request.getSession();
		
		String name = (String) request.getParameter("name");
		String surname = (String) request.getParameter("surname");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		
		
		String nextPage;
		if(name == null) nextPage = nextPage(request, email, password);
		else nextPage = subscribe(request, name, surname, email, password);
	
		response.sendRedirect(nextPage);

	}
	
	
	private String nextPage(HttpServletRequest request, String email, String password) {
		HttpSession session = request.getSession();
		String nextPage = null;
		if (email == null && password == null) {
			nextPage = "loginpage.jsp";
		}
		else {
			user = em.isUser(email, password);
			if(user != null) {
				session.setAttribute("user", user);
				nextPage = "listpage.jsp";
			}
			else {
				session.setAttribute("loginError", "Email or password incorrect");
				nextPage = "loginpage.jsp";
			}
		}	
		return nextPage;
	}
	
	private String subscribe(HttpServletRequest request, String name, String surname, String email, String password) {
		System.out.println("subcribe");
		String nextPage;
		HttpSession session = request.getSession();
		
		user = em.insertUser(name, surname, email, password);
		if (user == null) {
			session.setAttribute("loginError", "Email already exists");
			nextPage = "loginpage.jsp";
		}
		else nextPage = "listpage.jsp";
		return nextPage;
	}
	
	
	

}
