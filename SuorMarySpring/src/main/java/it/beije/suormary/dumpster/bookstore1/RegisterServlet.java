package it.beije.suormary.dumpster.bookstore1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("./registration.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		if(UserUtils.userExists(email)) {
			request.getSession().setAttribute("registrationError", "L' email inserita è già associata ad un account.");
			response.sendRedirect("./RegisterServlet");
		}else {
			UserUtils.createUser(email, password, name, surname);
			request.getSession().setAttribute("registrationSuccess", "L'account è stato registrato con successo.");
			response.sendRedirect("./LoginServlet");
		}
		
	}

}
