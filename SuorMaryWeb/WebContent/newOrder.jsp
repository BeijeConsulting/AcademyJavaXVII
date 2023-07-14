<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.bookstore3.User"%>
<%@page import="it.beije.suormary.bookstore3.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
HttpSession currSession = request.getSession();
List<Book> books = (List) currSession.getAttribute("books");
%>
<%
 for(Book b : books){
	 %>
   <h2><%= b.getTitle() %></h2>
   <form action="addBookToOrder" action="GET">
   <input type="hidden" name="bookOrderId" value="<%=b.getId() %>" />
   <input type="submit" value="Aggiungi all`ordine" />
   </form>
	 
<% 
 }
%>
  <form action="payOrder" action="GET">
   <input type="submit" value="PAGA" />
   </form>
</body>
</html>