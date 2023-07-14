package it.beije.suormary.bookstore1;

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

		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		if (email != null) { //utente loggato
			//ok perchè reindirizzo da servlet a servlet, se fosse verso jsp, meglio usare response forward
			response.sendRedirect("./ShopServlet");
		} else { //non loggato
			request.getRequestDispatcher("./login.jsp").forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("email : " + email);
		System.out.println("password : " + password);
		
		HttpSession session = request.getSession();
		
		User user = UserUtils.checkUser(email, password);
		
		
		
		if (user!=null) { //OK
			session.setAttribute("email", email);
			
			response.sendRedirect("./ShopServlet");
		} else { //KO
			session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");

			response.sendRedirect("./LoginServlet");
		}
		
	}

}
