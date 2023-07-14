package it.beije.suormary.rubrica;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String selectedOption = request.getParameter("menu");
	    
	    // Eseguire le azioni in base all'opzione selezionata
	    if (selectedOption != null) {
	        switch (selectedOption) {
	            case "01":
	                // Azione per visualizzare la lista dei contatti
	                // reindirizza a un'altra pagina o esegui un'operazione specifica
	            	System.out.println("Sono 1");
	                break;
	            case "02":
	                
	            	System.out.println("Sono 2");
	                break;
	            case "03":
	                
	            	System.out.println("Sono 3");
	                break;
	            case "04":
	                
	            	System.out.println("Sono 4");
	                break;
	            case "05":
	                
	            	System.out.println("Sono 5");
	                break;
	            case "06":
	                
	            	System.out.println("Sono 6");
	                break;
	            case "07":
	                
	            	System.out.println("Sono 7");
	                break;
	            default:
	            	break;
	        }
	    }
	}

}
