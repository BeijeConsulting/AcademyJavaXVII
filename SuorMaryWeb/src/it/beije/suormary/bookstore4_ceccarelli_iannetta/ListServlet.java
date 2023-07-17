package it.beije.suormary.bookstore4_ceccarelli_iannetta;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/listservlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EcommerceManager em = new EcommerceManager();
	List<Book> books = null;
	List<Author> authors = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ListServlet doGet");
		
		HttpSession session = request.getSession();
		books = em.listBook();
		session.setAttribute("allBooks", books);
		authors = em.listAuthor();
		session.setAttribute("allAuthors", authors);
		response.sendRedirect("listpage.jsp");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ListServlet doPost");
		
		HttpSession session = request.getSession();	
		
		String form = (String) session.getAttribute("form");
		
		if (form.equals("author")) {
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String description = request.getParameter("description");
			Author author = em.addAuthor(name, surname, description);
			if(author == null) {
				session.setAttribute("AuthorError", "Author already exists");
			}
		}
		else if (form.equals("book")) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			int idAuthor = Integer.parseInt(request.getParameter("author"));
			System.out.println("ID AUTORE" + idAuthor); 
			String editor = request.getParameter("editor");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Book book = em.addBook(title, description, idAuthor, editor, price, quantity);
			if(book == null) {
				session.setAttribute("BookError", "Book already exists");
			}
		}
		else if (form.equals("updatebook")) {
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int bookId = ((Book) session.getAttribute("book")).getId();
			em.updateBook(bookId, price, quantity);
		}
		else if (form.equals("updateauthor")) {
			String description = request.getParameter("description");
			int authorId = ((Author) session.getAttribute("author")).getId();
			em.updateAuthor(authorId, description);
		}
		
		response.sendRedirect("./listservlet");
	}
	
	
//	private void allBook(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		books = em.listBook();
//		session.setAttribute("allBooks", books);
//	}
//	
//	private void allAuthors(HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		books = em.listBook();
//		session.setAttribute("allBooks", books);
//	}

}
