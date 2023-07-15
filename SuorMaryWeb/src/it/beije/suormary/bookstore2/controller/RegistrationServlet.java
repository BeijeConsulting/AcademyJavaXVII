package it.beije.suormary.bookstore2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.suormary.bookstore2.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/bookstoreRegistration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
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
		// Recupera i valori inseriti dall'utente nel form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nome = request.getParameter("name");
        String cognome = request.getParameter("surname");

        // Crea un oggetto Utente con i dati inseriti
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(nome);
        user.setSurname(cognome);
        
        UserUtility.insertUser(user);
        response.sendRedirect("bookstoreLogin.jsp");
	}

}
