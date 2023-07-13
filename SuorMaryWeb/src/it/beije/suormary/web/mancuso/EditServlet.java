package it.beije.suormary.web.mancuso;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strId = (String)(request.getParameter("id"));
		//System.out.println(strId);
		int id = Integer.valueOf(strId); 
		Contact contact = JPAUtils.getContact(id);
		//System.out.println(contact);
		request.setAttribute("contact", contact);
		request.getRequestDispatcher("./modificaContatto.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("saveContact") != null) { // caso saveContact
			int id = Integer.valueOf(request.getParameter("id"));
			String name = request.getParameter("nome");
			String surname = request.getParameter("cognome");
			String notes = request.getParameter("note");

			JPAUtils.editContact(id, name, surname, notes);
			
			request.getSession().setAttribute("message", "Modifica contatto salvata correttamente");
			
		} else { 
			if(request.getParameter("saveRef") != null){
				// caso saveRef
				//System.out.println("id" + request.getParameter("idRef"));
				//System.out.println("label" + request.getParameter("label"));
				//System.out.println("detail" + request.getParameter("detail"));
				int idDetail = Integer.valueOf(request.getParameter("idRef"));
				
				String label = request.getParameter("label");
				String detail = request.getParameter("detail");
				String type = request.getParameter("type");

				JPAUtils.editContactDetail(idDetail, label, detail, type);
				
				request.getSession().setAttribute("message", "Modifica ref. contatto salvata correttamente");
				
			}else {
				
				if(request.getParameter("saveNewRef") != null) {
					String label = request.getParameter("label");
					String detail = request.getParameter("detail");
					String type = request.getParameter("type");
					
					int idContact = Integer.valueOf((String)request.getParameter("id"));
					
					ContactDetail cd = new ContactDetail();
					cd.setId_contact(idContact);
					cd.setLabel(label);
					cd.setType(type.charAt(0));
					cd.setDetail(detail);
					
					JPAUtils.addContactDetail(cd);
					
					request.getSession().setAttribute("message", "Ref. contatto inserito correttamente");
					
				}else {
					int idDetail = Integer.valueOf(request.getParameter("idRef"));
					JPAUtils.deleteContactDetail(idDetail);
					
					request.getSession().setAttribute("message", "Ref. contatto eliminato correttamente");
				}	
			
			}
			
		}
		
		
		response.sendRedirect("./EditServlet?id=" + request.getParameter("id"));
	}

}
