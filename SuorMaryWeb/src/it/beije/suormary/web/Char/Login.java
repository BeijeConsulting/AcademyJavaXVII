package it.beije.suormary.web.Char;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String form = "<!DOCTYPE html>\r\n"
            		+ "<html>\r\n"
            		+ "<head>\r\n"
            		+ "<meta charset=\"ISO-8859-1\">\r\n"
            		+ "<title>Login Page</title>\r\n"
            		+ "</head>\r\n"
            		+ "<body>\r\n"
            		+ "\r\n"
            		+ "<form action=\"./home\" method=\"POST\">\r\n"
            		+ "  <label for=\"name\">First name:</label><br>\r\n"
            		+ "  <input type=\"text\" name=\"name\" ><br>\r\n"
            		+ "  <label for=\"surname\">Last name:</label><br>\r\n"
            		+ "  <input type=\"text\" name=\"surname\" ><br><br>\r\n"
            		+ "  <input type=\"submit\" value=\"Submit\">\r\n"
            		+ "</form> \r\n"
            		+ "\r\n"
            		+ "</body>\r\n"
            		+ "</html>";
            response.getWriter().append(form);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("FirstServlet doPost");

	}

}
