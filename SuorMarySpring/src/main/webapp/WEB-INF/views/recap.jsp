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
   <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f5f5f5;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 10px;
        }

        hr {
            border: 1px solid #ccc;
            margin: 20px 0;
        }

        .button-container {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .button {
            background-color: #4CAF50;
            color: #fff;
            cursor: pointer;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            border-radius: 3px;
        }

        .button:hover {
            background-color: #45a049;
        }
    </style>
    <title>Recap Order</title>
</head>
<body>
<%@ include file="header.jsp" %>

    <h2>Riepilogo Ordine n° ${order.id} </h2>

<c:forEach items="${order.items}" var="orderItem">
    <c:set var="book" value="${BookStoreUtility.getBookById(orderItem.bookId)}" />
   <h2> ${book.title} - Quantità: ${orderItem.quantity}</h2>
</c:forEach>

        <c:choose> 
	<c:when test='${order.items.size() eq 0}'> <h2>Totale acquisto: 0.00 </h2><br/> </c:when>
	<c:otherwise>
	  <h2 style="color:green">Totale acquisto: ${order.amount} </h2> <br/>
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