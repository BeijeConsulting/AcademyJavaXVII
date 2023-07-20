package dumpster.bookstore1;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.beije.suormary.bookstore1.model.Order;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> oi = OrderUtils.getOrders(UserUtils.getUserId((String)(request.getSession().getAttribute("email"))));
		request.getSession().setAttribute("orders", oi);
		request.getRequestDispatcher("./orders.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("updateOrder");
		
		switch(status) {
			case "Invia":
				String address = request.getParameter("address");
				OrderUtils.createOrder(address, request);
				break;
			case "Paga":
				OrderUtils.editStatus('P', Integer.valueOf(request.getParameter("orderId")));
				break;
			case "Annulla":
				OrderUtils.editStatus('C', Integer.valueOf(request.getParameter("orderId")));
				break;
			default:
				System.out.println("Errore");
				break;
		}
		response.sendRedirect("./OrderServlet");
	}

}
