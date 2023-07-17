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
    <title>Recap Order</title>
</head>
<body>
<form action="payment" action="GET">
   <input type="submit" value="Paga Ordine" />
</form> 
    <% 
   		Order order =(Order) session.getAttribute("order");
        Order orderfound = BookStoreUtility.findOrder(order);
     
        List<Book> booksOrder = (List<Book>) session.getAttribute("booksOrder");
 
    %>

    <h2>Riepilogo Ordine n� <%= orderfound.getId() %></h2>

    <h3>Acquistati <%= booksOrder.size() %> libri:<br/>
        <% for (Book b : booksOrder) { %>
            <%= b.getTitle() %><br/>
        <% } %>
        Stato Ordine: <%= orderfound.getStatus() %><br/>
        Totale acquisto: <%= orderfound.getAmount() %> <br/>
    </h3>

    
</body>
</html>