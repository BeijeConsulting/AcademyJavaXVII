package it.beije.suormary.web.sala;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ContattoServlet
 */
@WebServlet("/contatto")
public class ContattoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ContattoServlet doGet");
		
		HttpSession session = request.getSession();
		String nome = (String) session.getAttribute("nome");
		String cognome = (String) session.getAttribute("cognome");
		
		if (nome != null) { //utente loggato
			response.sendRedirect("welcome.jsp");
		} else { //non loggato
			response.sendRedirect("cerca.jsp");
		}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ContattoServlet doPost");
		
		//GestoreRubrica gr = new GestoreRubrica();
		//List<Contatto> c = gr.cercaContatti();
		
		//for(Contatto c1:c) {
			//System.out.println(c1);
		//}

		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		System.out.println("nome : " + nome);
		System.out.println("cognome : " + cognome);
		
		HttpSession session = request.getSession();
		System.out.println("JSESSIONID: " + session.getId());
		
		List<Contatto> contatti = RubricaUtilsJPA.loadRubricaFromDBCercaCON(nome, cognome);
		
		
		//SELECT * from Users WHERE username = :username AND password = :password
		
		if (nome != null && nome.equalsIgnoreCase("anna") && cognome != null && cognome.equals("sala")) { //OK
			session.setAttribute("nome", nome);
			session.setAttribute("cognome", cognome);
			//session.removeAttribute("nome");
			//session.removeAttribute("cognome");
			response.sendRedirect("welcome.jsp");
		} else { //KO
			session.setAttribute("loginError", "CREDENZIALI NON VALIDE!!!");

			response.sendRedirect("cerca.jsp");
		}
		
	}

}
