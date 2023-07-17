<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.suormary.bookstore3.Book"%>
<%@page import="it.beije.suormary.bookstore3.Order"%>
<%@page import="it.beije.suormary.bookstore3.BookStoreUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>

<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Recap Order</title>
</head>
<body>
<%@ include file="header.jsp" %>
    <% 
   		Order order =(Order) session.getAttribute("order");
        Order orderfound = BookStoreUtility.findOrder(order);
     
        List<Book> booksOrder = (List<Book>) session.getAttribute("booksOrder");
 
    %>

    <h2>Riepilogo Ordine n° <%= orderfound.getId() %></h2>

    <h3>Acquistati <%= booksOrder.size() %> libri:<br/>
        <% for (Book b : booksOrder) { %>
            <%= b.getTitle() %><br/>
        <% } %>
        Stato Ordine: <%= orderfound.getStatus() %><br/>
        Totale acquisto: <%= orderfound.getAmount() %> <br/>
        Indirizzo Spedizione: <%= orderfound.getShippingAddress()%><br/>
    </h3>
    <form action="deleteOrder" action="GET">
   <input type="submit" value="Cancella Ordine" />
   </form>
<form action="updateOrder" action="GET">
   <input type="submit" value="Modifica ordine" />
</form> 
   <form action="payment" action="GET">
   <input type="submit" value="Paga Ordine" />
</form> 

    
</body>
</html>