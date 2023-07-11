package it.beije.suormary.web.ceccarelli;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class rubricaServlet
 */
@WebServlet("/rubricaServlet")
public class rubricaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public rubricaServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		DbWithHBM mr = new DbWithHBM();
		List<Contact> contact = mr.listContactHBM();
		
		if (contact != null) {
			request.getSession().setAttribute("contacts", contact);
			
		}
		response.sendRedirect("contactPage.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println(request.getParameter("listaContatti"));
//		System.out.println(request.getParameter("cercaContatto"));
		
		//String[] but = request.getParameter().;
		doGet(request, response);
	}

}
