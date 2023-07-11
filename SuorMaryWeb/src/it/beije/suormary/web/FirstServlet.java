package it.beije.suormary.web;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stubX
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet doGet");
		
		//....
		String adesso = LocalTime.now().toString();
		
		String name = request.getParameter("name");
		
		StringBuilder html = new StringBuilder("<html><body><p>")
				.append("Ciao ").append(name).append("<br/>")
				.append("sono le ").append(adesso)
				.append("</p></body></html>");

		
		response.getWriter().append(html.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet doPost");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		StringBuilder html = new StringBuilder("<html><body><p>")
				.append("Ciao ").append(fname).append(" ").append(lname).append("!!!")
				.append("</p></body></html>");

		
		response.getWriter().append(html.toString());
	}

}
