package it.beije.suormary.web.ceccarelli;

import java.io.IOException;
import java.sql.SQLException;
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
		
		// lista con HBM
//		DbWithHBM mr = new DbWithHBM();
//		//List<Contact> contact = mr.listContactJoinHBM();
//		for(Contact c : contact) {
//			System.out.println(c);
//		}
//		
//		if (contact != null) {
//			request.getSession().setAttribute("contacts", contact);
//			
//		}
//		response.sendRedirect("contactPage.jsp");
//		String[] s = request.getParameterValues("selection");
//		System.out.println(s.length);
//		for(String ss : s) {
//			System.out.println(ss);
//		}
		//switch(s)
		//lista con JDBC
		NuovaRubricaMetodi mr = new NuovaRubricaMetodi();
		List<Contact2> contact = null;
		try {
			contact = mr.contactNew();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Contact2 c2 : contact ) {
			System.out.println(c2);
		}
		
		if(contact !=null) {
			request.getSession().setAttribute("contactNew", contact);
		}
		
		//response.sendRedirect("contactPage.jsp");
		
		//lista con JDBC con JOIN
				
		List<Contact2> contactJOIN = null;
		try {
			contactJOIN = mr.contactNewJOIN();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Contact2 c2 : contactJOIN ) {
			System.out.println(c2);
		}
		
		if(contact !=null) {
			request.getSession().setAttribute("contactNewJOIN", contactJOIN);
		}
		
		response.sendRedirect("contactPage.jsp");
		
		//ricerca contatto con JDBC
		
		
				
		
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
