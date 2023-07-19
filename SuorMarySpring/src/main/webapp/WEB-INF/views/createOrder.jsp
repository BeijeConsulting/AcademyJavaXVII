<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.controller.User"%>
<%@page import="it.beije.suormary.controller.Book"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.beije.suormary.controller.Author"%>
<%@page import="it.beije.suormary.controller.BookStoreUtility"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<c:forEach items="${books}" val="book">
<div class="card">
	  <h5>Quantità disponibile : ${book.quantity}</h5>
            <h3>Titolo :  ${book.title} </h3>
            <c:if test="${not empty ${book.description}"> 
            <span class="desc">Descrizione : </span>
             <span>${book.description}</span>
            </c:if>
            <div class="buttons">
           <form action="quantityBook" method="get">
   <input type="hidden" name="bookId" value= "${book.id}" />
   <label>Inserisci quantità : </label>
   <input type="number" name="quantity" />
   <input type="submit" value="inserisci" />
   </form>
   <form action="addBookToOrder" action="GET">
   <input type="hidden" name="bookOrderId" value="${book.id}" />
   <input type="hidden" name="quantity" value = "<%= session.getAttribute("quantity") %>" />
   <input type="submit" value="Aggiungi all`ordine" />
   </form>
            </div>
        </div>
 </c:forEach>

  <form action="recapOrder" action="GET">
   <input type="submit" value="Recap Ordine" class="button"/>
   </form>

</body>
</html>