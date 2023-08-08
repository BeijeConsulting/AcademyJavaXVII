package it.beije.suormary.bookstore3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class QuantityBook
 */
@WebServlet("/quantityBook")
public class QuantityBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuantityBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String quantity = request.getParameter("quantity");
		String idStr = request.getParameter("bookId");
		Book book = BookStoreUtility.getBookById(idStr);
		int quantityId = Integer.parseInt(quantity);
		if(quantityId > book.getQuantity() ) {
			session.setAttribute("ErrorQuantity", "Hai inserito una quantità maggiore rispetto a quelli disponibili");
		}
		else {
			session.setAttribute("quantity", quantityId);
		}
		response.sendRedirect("newOrder.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
