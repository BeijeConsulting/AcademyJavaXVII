package web.rubrica.caroselli;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MenuServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("MenuServlet doGet");

		response.sendRedirect("menu.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String selected = request.getParameter("menu");
		
		if (selected != null && !selected.isEmpty()) {
		// switch choices
			switch(selected) {
			case "view all contacts":
				// chiamo il metodo che mi prende i contatti li setto e li mando
				List<Contact> contacts = RubricaUtilsJPA.readContactsFromDb();
				request.setAttribute("contacts", contacts);
				// chiama la jsp
				request.getRequestDispatcher("contacts.jsp").forward(request, response);

				break;
				
			case "insert a contact":
                
                Contact contact = RubricaUtilsJPA.insertContact(request);
                request.getSession().setAttribute("contact", contact);

                request.getRequestDispatcher("insertContact.jsp").forward(request, response);
                break;
				
			}
			
	

		}

	}

	
}
