package web.rubrica.caroselli;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		List<String> contacts = Arrays.asList("hello", "world");
		List<Contact> contacts = RubricaUtils.readContactsFromDb();
		System.out.println("Number of contacts: " + contacts.size());
		
		req.setAttribute("contacts", contacts);
		req.getRequestDispatcher("contacts.jsp").forward(req, resp);
		
	}

}
