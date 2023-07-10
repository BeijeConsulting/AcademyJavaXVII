package it.beije.suormary.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String form =
		"<!DOCTYPE html>\r\n" + 
		"<html>\r\n" + 
		"<head>\r\n" + 
		"<meta charset=\"ISO-8859-1\">\r\n" + 
		"<title>Login Servlet Page</title>\r\n" + 
		"</head>\r\n" + 
		"<body>\r\n" + 
		"\r\n" + 
		"<form action=\"./first\" method=\"POST\">\r\n" + 
		"  <label for=\"fname\">First name:</label><br>\r\n" + 
		"  <input type=\"text\" name=\"fname\" ><br>\r\n" + 
		"  <label for=\"lname\">Last name:</label><br>\r\n" + 
		"  <input type=\"text\" name=\"lname\" ><br><br>\r\n" + 
		"  <input type=\"submit\" value=\"Submit\">\r\n" + 
		"</form> \r\n" + 
		"\r\n" + 
		"</body>\r\n" + 
		"</html>";
		response.getWriter().append(form);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
