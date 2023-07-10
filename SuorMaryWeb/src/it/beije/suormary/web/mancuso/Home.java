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
		
		body.append("<body>");
		
		body.append("<div style=\"margin-top: 50px; margin-bottom: 50px;\">");
		
		body.append("<h1 style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">Benvenuta/o");
		
		if(surname != "") {
			body.append(" ").append(surname);
		}
		if(name != "") {
			body.append(" ").append(name);
		}
		body.append("!</h1>");
		
		body.append("<h2 style=\"text-align: center; font-family: Arial, Helvetica, sans-serif;\">Chi sei?</h2>");
		
		body.append("</div>");
		
		body.append("<div style=\"margin-left: 200px; margin-right: 200px; padding: 20px;\"><hr></div>");
		
		body.append("<div style=\"text-align: center; margin-top: 50px;\">");
		
		body.append("<form method=\"POST\">"
				+ "<input type=\"text\" name=\"name\" placeholder=\"Mario\" style=\"margin-bottom: 20px;\"/><br/>"
				+ "<input type=\"text\" name=\"surname\" placeholder=\"Rossi\" style=\"margin-bottom: 20px;\"/><br/>"
				+ "<input type=\"submit\" value=\"Submit\" style=\"background-color: black; color: white\"/>"
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
