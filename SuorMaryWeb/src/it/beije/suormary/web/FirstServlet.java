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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet doGet");
		
		//....
		String adesso = LocalTime.now().toString();
		
		String name = request.getParameter("nome");
		
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
		
		String fname = request.getParameter("nome");
		String lname = request.getParameter("cognome");
		String[] selection = request.getParameterValues("sport");
		StringBuilder html = new StringBuilder();
		if(selection !=null) {
		
		html.append("<html><body><p>")
				.append("Ciao ").append(fname).append(" ").append(lname).append("!!!")
				.append("</p>").append("<p> Questi sono gli sport selezionati<br>").append(printString(selection)).append("</p></body></html>");

		}else {
			
		}
		response.getWriter().append(html.toString());
		
	}
	
	public String printString(String[] s) {
	    StringBuilder sb = new StringBuilder();
	    for (String ss : s) {
	        sb.append(ss).append("<br>");
	    }
	    return sb.toString();
	}
}
