package it.beije.suormary.web.sala;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ListaContatti
 */
@WebServlet("/lista")
public class ListaContatti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// Chiamata al metodo per ottenere la lista dei contatti
		
		
		// Imposta la lista dei contatti come attributo della richiesta
		//request.setAttribute("contacts", contacts);
		/*for(Contatto c:contacts) {
			System.out.println(c.toString());
		}*/
		
		// Inoltra la richiesta alla JSP per la visualizzazione dei dati
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/listContatti.jsp");
	    //dispatcher.forward(request, response);
	}

	

}
