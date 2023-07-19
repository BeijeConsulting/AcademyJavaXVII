<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@page import="it.beije.suormary.controller.User"%>
<%@page import="it.beije.suormary.controller.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.beije.suormary.controller.Author"%>
<%@page import="it.beije.suormary.controller.BookStoreUtility"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
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
	 Author author = BookStoreUtility.getAuthorById(b.getAuthorId());
	 %>
   <div class="card">
    <h3><%= b.getTitle() %></h3>
   <h5><%= "Quantità disponibile : " + (b.getQuantity()) %></h5>
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
   <form action="quantityBookModOrder" method="get">
   <input type="hidden" name="bookId" value= "<%= b.getId() %>" />
   <label>Inserisci quantità : </label>
   <input type="number" name="quantity" />
   <input type="submit" value="inserisci" />
   </form>
   <form action="addBookToModOrder" action="GET">
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
   <input type="submit" value="Salva modifica" class="button" /> 
   </form>

</body>
</html>