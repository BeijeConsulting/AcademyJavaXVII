package it.beije.suormary.bookstore3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateOrder
 */
@WebServlet("/updateOrder")
public class UpdateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	if(request.getParameter("order")!=null){
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("orderId");
		Order order = BookStoreUtility.getOrderById(id);
		System.out.println("order id " + order.getId());
		session.setAttribute("order", order);

		response.sendRedirect("updateOrder.jsp");
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
