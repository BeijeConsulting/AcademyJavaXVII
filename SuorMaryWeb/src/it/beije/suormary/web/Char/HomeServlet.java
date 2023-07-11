package it.beije.suormary.web.Char;

import java.io.IOException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//System.out.println("FirstServlet doPost");
//		
//		String fname = request.getParameter("name");
//		String lname = request.getParameter("surname");
//		
//		StringBuilder html = new StringBuilder("<html><body><p>")
//				.append("Ciao ").append(fname).append(" ").append(lname).append("!!!")
//				.append("</p></body></html>");
//
//		
//		response.getWriter().append(html.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("FirstServlet doPost");
		
		String fname = request.getParameter("name");
		String lname = request.getParameter("surname");
		
		StringBuilder html = new StringBuilder("<html><body><p>")
				.append("Ciao ").append(fname).append(" ").append(lname).append("!!!")
				.append("</p></body></html>");

		
		response.getWriter().append(html.toString());
	}

}
