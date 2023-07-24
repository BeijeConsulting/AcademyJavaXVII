<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.suormary.model.Book"%>
<%@page import="it.beije.suormary.model.Order"%>
<%@page import="it.beije.suormary.model.OrderItem"%>
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

    <h2>Riepilogo Ordine n° ${order.id} </h2>

<c:forEach items="${order.items}" var="orderItem">
    <c:set var="book" value="${BookStoreUtility.getBookById(orderItem.bookId)}" />
    ${book.title} - Quantità: ${orderItem.quantity} <br/>
</c:forEach>

        <c:choose> 
	<c:when test='${order.items.size() eq 0}'> Totale acquisto: 0.00 <br/> </c:when>
	<c:otherwise>
	   Totale acquisto: ${order.amount} <br/>
	 </c:otherwise>
	
	 </c:choose>

    <hr><br/>
<c:choose>
	<c:when test='${order.status eq "I"}'>
	   
     	<form action="payment" method="GET">
		    <label for="address">Indirizzo Spedizione:</label><br>
		    <input type="text" id="address" name="address" required><br>
		    <input type="submit" value="Paga Ordine" class="button"/>
		</form>
		<form action="deleteOrder" action="GET">
		<input type="hidden" name="orderId" value="${order.id}" />
   			<input type="submit" value="Cancella Ordine" class="button"/>
   		</form>
		<form action="updateOrder" action="GET">
		<input type="hidden" name="orderId" value="${order.id}" />
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