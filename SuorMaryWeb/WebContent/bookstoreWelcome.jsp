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


<p>Hello world</p>
	
	<% List<Book> books = (List<Book>) request.getAttribute("books");%> 
	


	<%
	if (books != null) {
		
		for (Book b : books) {
	%>
	<p>
		Element:
		<%=b.getTitle()%>
	
	</p>
	<%
	}
	}
	%>

</body>
</html>