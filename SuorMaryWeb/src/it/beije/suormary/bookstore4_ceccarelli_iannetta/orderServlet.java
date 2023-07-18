package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/orderservlet")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EcommerceManager em = new EcommerceManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int idBook = Integer.parseInt(request.getParameter("indexBook"));
		int idUser = ((User)session.getAttribute("user")).getId();
		em.addToBasket(idBook, idUser );
		
		HashMap<Book,Integer> basket = em.basket(idUser);
		//Order basket = em.basket(idUser);
		
		session.setAttribute("basket", basket);
		session.setAttribute("basketAmount", em.getBasketAmount(idUser));
		response.sendRedirect("buypage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// aggiorna Order status
		//doGet(request, response);
	}

}
