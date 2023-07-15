package it.beije.sourmary.web.sala.prove;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.beije.sourmary.web.sala.*;
import it.beije.suormary.web.sala.Contatto;
/**
 * Servlet implementation class Login
 */
@WebServlet("/LogServlet")
public class LogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession sessione = request.getSession();
		request.getRequestDispatcher("./mioLogin.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession();
		//String nome = (String) sessione.
		String nome = request.getParameter("username");
		String cognome = request.getParameter("password");
		//Contatto c = ContattoUtils.trovaContatto(nome, cognome);
		Contatto c = new Contatto(1, nome, cognome);
		System.out.println(c.getName());
		
		
		if(c.getName().equals("anna")) {
			response.sendRedirect("./HomeServlet");
			
		} else {
			String errore = "errore";
			sessione.setAttribute("errore", errore);
			response.sendRedirect("./mioLogin.jsp");
		}
	}

}
