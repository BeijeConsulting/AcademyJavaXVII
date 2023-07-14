<%@page import="it.beije.suormary.bookstore2.model.User"%>
<%@page import="it.beije.suormary.bookstore2.model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>


<p>
<%
/*
String username = (String) session.getAttribute("username");
if (username == null) response.sendRedirect("login");
*/
String welcome = "BUONGIORNO";

/*
User user = (User) session.getAttribute("user");
if (user == null) {
	user = new User();
	session.setAttribute("user", user);
}
*/
%>

<jsp:useBean id="user" class="it.beije.suormary.bookstore2.model.User" scope="session"></jsp:useBean>
<%= welcome %> <jsp:getProperty property="name" name="user"/> <jsp:getProperty property="surname" name="user"/> !!
</p>

<% List<Book> books = (List<Book>) request.getAttribute("books");%> 

<% if (books != null && !books.isEmpty()) { %>
    <div style="display: flex; flex-wrap: wrap; gap: 20px;">
        <% for (Book book : books) { %>
            <div style="width: 300px; border: 1px solid #ccc; padding: 10px; background-color: #f9f9f9;">
                <p><strong>Title:</strong> <%= book.getTitle() %></p>
                <p><strong>Author:</strong> <%= book.getAuthorId() %></p>
              	<p><strong>Description:</strong> <%= book.getDescription() %></p>
              	<p><strong>Editor:</strong> <%= book.getEditor() %></p>
              	<p><strong>Price:</strong> <%= book.getPrice() %></p>
              	<p><strong>Quantity:</strong> <%= book.getQuantity() %></p>
              	<a href="BookDetailsServlet?id=<%= book.getId() %>">View Details</a>

            </div>
        <% } %>
    </div>
<% } else { %>
    <p>No books found.</p>
<% } %>



</body>
</html>