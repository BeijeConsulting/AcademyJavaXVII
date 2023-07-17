<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.bookstore3.User"%>
<%@page import="it.beije.suormary.bookstore3.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.beije.suormary.bookstore3.Author"%>
<%@page import="it.beije.suormary.bookstore3.BookStoreUtility"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Ordine</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
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
	 Author author = BookStoreUtility.getAuthorById(b.getAuthorId());
	 %>
	  <div class="card">
	  <h5><%= "Quantità disponibile : " + (b.getQuantity()) %></h5>
            <h3><%= "Titolo : " + b.getTitle() %></h3>
            <h4><%="Autore : " +  author.getName()%></h4>
               <%
              if(b.getDescription() != null){
            	  
             %>
             <span class="desc">Descrizione : </span>
             <span><%= b.getDescription() %></span>
             <%
              }
             %>
            <div class="buttons">
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
            </div>
        </div>

	 
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