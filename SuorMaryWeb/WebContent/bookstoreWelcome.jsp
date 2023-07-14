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
                <h2 style="text-align: center; color: #333;"><%= book.getTitle() %></h2>
                <hr>
                <p style="text-align: center"><strong>AuthorId:</strong> <%= book.getAuthorId() %></p>
                <p style="text-align: center"><strong>Description:</strong> <%= book.getDescription() %></p>
                <form style="text-align: center" action="bookstoreBookDetails" method="get">
                    <input type="hidden" name="id" value="<%= book.getId() %>">
                    <button type="submit" style="background-color: #2c5e29; color: white; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer;">View Details</button>
                </form>
            </div>
        <% } %>
    </div>
<% } else { %>
    <div style="text-align: center;">
        <p style="font-size: 18px; color: #555;">No book found.</p>
    </div>
<% } %>




</body>
</html>