package it.beije.suormary.web.rubrica.trapani;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class RubricaServlet
 */
@WebServlet("/rubrica")
public class RubricaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RubricaServlet() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sceltaString = request.getParameter("scelta");
		if (sceltaString ==null) {
		response.sendRedirect("menuscelta.jsp");
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String scelta = request.getParameter("scelta");
//		StringBuilder contatti = new StringBuilder();
//		
//		if (scelta!=null && scelta.equals("Visualizza lista contatti completa")) {
//		List<Contact> rubrica = DBthroughJPA.listContacts();
//		for(Contact contact : rubrica) {
//			contatti.append(contact.toString()).append(" \n");
//		}	
//		response.getWriter().append(contatti.toString());
		
		String sceltaString = request.getParameter("scelta");
		if (sceltaString!=null) {
			switch (sceltaString) {
			case "Visualizza lista contatti completa":
				response.sendRedirect("elencorubrica.jsp");
				sceltaString = null;
				break;
			case "Cerca contatto":
				response.sendRedirect("ricerca_contatto.jsp");
				sceltaString = null;
				break;
			case "Inserisci nuovo contatto":
				response.sendRedirect("insert_contact.jsp");
				sceltaString = null;
				break;
			case "Modifica contatto":

				sceltaString = null;
				break;
			case "Cancella contatto":
				response.sendRedirect("delete_contact.jsp");
				sceltaString = null;
				break;
			case "Trova contatti doppi":
				
				sceltaString = null;
				break;
			case "Unisci contatti doppi":
				sceltaString = null;
				break;
			default: response.sendRedirect("menuscelta.jsp");
				sceltaString = null;
				break;
		} 		
	} else {
		doGet(request,response);
	}

	}}
