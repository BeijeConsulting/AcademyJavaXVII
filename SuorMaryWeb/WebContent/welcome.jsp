<%@page import="it.beije.suormary.bookstore3.User"%>
<%@page import="it.beije.suormary.bookstore3.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
HttpSession currSession = request.getSession();
List<Book> books = (List) currSession.getAttribute("books");
%>
<h1>Welcome <%= currSession.getAttribute("email") %></h1>
<form action="./createBook" method ="GET">
<input type="submit" value="aggiungi un libro">
</form>

<%
 for(Book b : books){
	 %>
   <h2><%= b.getTitle() %></h2>
   <form action="updateBook" action="GET">
   <input type="hidden" name="id" value=<%=b.getId() %> />
   <input type="submit" value="Modifica libro" />
   </form>
	 
<% 
 }
%>
 
 

</body>
</html>