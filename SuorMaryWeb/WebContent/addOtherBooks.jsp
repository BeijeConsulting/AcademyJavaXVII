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
//HttpSession currSession = request.getSession();
List<Book> books = (List) session.getAttribute("books");
List<Book> booksOrder = (List)session.getAttribute("booksOrder");
%>
<%
String ErrorQuantity = (String) session.getAttribute("ErrorQuantity");
if (ErrorQuantity != null) {
	%>
	<p style="color:red"><%= ErrorQuantity %></p>
	<%
	
	session.removeAttribute("ErrorQuantity");
}
%>
<%
 for(Book b : books){
	 %>
   <h2><%= b.getTitle() %></h2>
   <h5><%= "Quantità disponibile : " + (b.getQuantity()) %></h5>

   <form action="quantityBook" method="get">
   <input type="hidden" name="bookId" value= "<%= b.getId() %>" />
   <label>Inserisci quantità : </label>
   <input type="number" name="quantity" />
   <input type="submit" value="inserisci" />
   </form>
   <form action="addBookToOrder" action="GET">
   <input type="hidden" name="bookOrderId" value="<%=b.getId() %>" />
   <input type="hidden" name="quantity" value = "<%= session.getAttribute("quantity") %>" />
   <input type="submit" value="Aggiungi all`ordine" />

   </form>
	 
<% 
 }
%>
  <form action="recapOrder" action="GET">
   <input type="submit" value="Recap Ordine" />
   </form>
  <form action="deleteOrder" action="GET">
   <input type="submit" value="Cancella Ordine" />
   </form>
</body>
</html>