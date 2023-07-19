<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.suormary.controller.Book"%>
<%@page import="it.beije.suormary.controller.Order"%>
<%@page import="it.beije.suormary.controller.OrderItem"%>
<%@page import="it.beije.suormary.controller.BookStoreUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    %>

    <h2>Riepilogo Ordine n° ${order.id} </h2>


<% for(OrderItem orderItem :orderfound.getItems()){ Book book = BookStoreUtility.getBookById(orderItem.getBookId());%> 
       <%= book.getTitle() %>
             <%=" - " + "Quantità : " +  orderItem.getQuantity() %><br/>
	<%
	}
	%>

        Stato Ordine: ${order.status}<br/>
        Totale acquisto: ${order.amount} <br/>

    <hr><br/>
<c:choose>
	<c:when test='${order.status eq "I"}'>
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
	</c:when>
	<c:otherwise>
		<form action="my_orders" action="GET">
   			<input type="submit" value="Torna Ai Miei Ordini" class="button"/>
		</form>
	</c:otherwise>
 
</c:choose>
</body>
</html>