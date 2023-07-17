<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.suormary.bookstore3.Book"%>
<%@page import="it.beije.suormary.bookstore3.Order"%>
<%@page import="it.beije.suormary.bookstore3.OrderItem"%>
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

    <% 
   		int orderId = (int) session.getAttribute("orderId");
        Order orderfound = BookStoreUtility.getOrderById(orderId);
        List<Book> booksOrder = (List<Book>) session.getAttribute("booksOrder");
 
    %>

    <h2>Riepilogo Ordine n° <%= orderfound.getId() %></h2>
<% for(OrderItem orderItem :orderfound.getItems()){ Book book = BookStoreUtility.getBookById(orderItem.getBookId());%> 
       <%= book.getTitle() %>
             <%=" - " + "Quantità : " +  orderItem.getQuantity() %><br/>
	<%
	}
	%>
        Stato Ordine: <%= orderfound.getStatus() %><br/>
        Totale acquisto: <%= orderfound.getAmount() %> <br/>
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