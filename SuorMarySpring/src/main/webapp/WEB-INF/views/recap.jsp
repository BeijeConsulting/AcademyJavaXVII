<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.suormary.controller.Book"%>
<%@page import="it.beije.suormary.controller.Order"%>
<%@page import="it.beije.suormary.controller.OrderItem"%>
<%@page import="it.beije.suormary.controller.BookStoreUtility"%>
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
    </h3>

    <hr><br/>
    <% if(orderfound.getStatus()=='I'){%>
<form>
<label for="address">Indirizzo Spedizione:</label><br>
  <input type="text" id="address" name="address"><br>
<input type="submit" value="Conferma" class="button">
</form>
<form action="payment" action="GET">
   <input type="hidden" name="sAddress" value="<%= request.getParameter("address") %>" />
   <input type="submit" value="Paga Ordine" class="button"/>
</form> 
<form action="deleteOrder" action="GET">
   <input type="submit" value="Cancella Ordine" class="button"/>
   </form>
<form action="updateOrder" action="GET">
   <input type="submit" value="Modifica ordine" class="button"/>
</form> 
   <form action="payment" action="GET">
   <input type="submit" value="Paga Ordine" class="button"/>
</form> 
<%} else {%>
<form action="myOrders" action="GET">
   <input type="submit" value="Torna Ai Miei Ordini" class="button"/>
</form>
 <%}%>   

</body>
</html>