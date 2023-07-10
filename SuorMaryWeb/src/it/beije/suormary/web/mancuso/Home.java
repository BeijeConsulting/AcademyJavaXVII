package it.beije.suormary.web.mancuso;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static String purple = "#6B07B1";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String writeHTML(String name, String surname) {
    	StringBuilder html = new StringBuilder();
		
		html.append("<!DOCTYPE html><html>");
		
		StringBuilder head = new StringBuilder();
		
		head.append("<head>"
				+ "<title>Homepage</title>"
				+ "</head>");
		
		html.append(head);
		
		StringBuilder body = new StringBuilder();
		
		body.append("<body style=\"background-color: black; color: white\">");
		
		body.append("<div style=\"margin-top: 50px; margin-bottom: 50px;\">");
		
		body.append("<h1 style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">Welcome");
		
		if(surname != "") {
			body.append(" ").append(surname);
		}
		if(name != "") {
			body.append(" ").append(name);
		}
		body.append("!</h1>");
		
		body.append("<h2 style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">What's your name?</h2>");
		
		body.append("</div>");
		
		body.append("<div style=\"margin-left: 200px; margin-right: 200px; padding: 20px;\"><hr style=\"border-color: ").append(purple).append(";\"></div>");
		
		body.append("<div style=\"text-align: center; margin-top: 50px;\">");
		
		body.append("<form method=\"POST\">"
				+ "<label for=\"name\" style=\"color:white; font-family: Arial, Helvetica, sans-serif;\">First Name</label><br/>"
				+ "<input type=\"text\" name=\"name\" style=\"margin-bottom: 20px; background-color:").append(purple).append("; color: white;\"/><br/>"
				+ "<label for=\"name\" style=\"color: white; font-family: Arial, Helvetica, sans-serif;\">Last Name</label><br/>"
				+ "<input type=\"text\" name=\"surname\" style=\"margin-bottom: 20px; background-color:").append(purple).append("; color: white;\"/><br/>"
				+ "<button type=\"submit\" value=\"Submit\" style=\"background-color: ").append(purple).append("; max-width: 100%; font-size: 18px; color: white;\">Submit</button>"
				+ "</form>");
		
		body.append("</div>");
		
		body.append("</body>");
		
		html.append(body);
		html.append("</html>");
		
		return html.toString();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String html = writeHTML("", "");
		
		response.getWriter().append(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		String html = writeHTML(name, surname);
		
		response.getWriter().append(html);
	}

}
