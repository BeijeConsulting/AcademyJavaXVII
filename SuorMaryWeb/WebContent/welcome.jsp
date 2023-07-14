<%@page import="it.beije.suormary.bookstore.User"%>
<%@page import="it.beije.suormary.bookstore.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>
<%
HttpSession currSession = request.getSession();
List<Book> books = (List) currSession.getAttribute("books");
%>
<h1>Welcome <%= currSession.getAttribute("email") %></h1>
<form action="/createBook.jsp" method ="GET">
<input type="submit" value="aggiungi un libro">
</form>

<%
 for(Book b : books){
	 %>
   <h2><%= b.getTitle() %></h2>
	 
<% 
 }
%>
 
 

</body>
</html>