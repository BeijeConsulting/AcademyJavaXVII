<%@page import="it.beije.suormary.bookstore1.model.OrderItem"%>
<%@page import="it.beije.suormary.bookstore1.model.Order"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
    	background-color: #FADAFF;
	}
</style>
<meta charset="ISO-8859-1">
<title>I miei ordini</title>
</head>
<body>
	<ul>
		<li style="display: inline; margin-right:30px;"><a href="./shop">Shop</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./cart">Cart</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./order">Orders</a></li>
		<li style="display: inline;"><a href="./logout">Logout</a></li>
	</ul>
	
	<c:forEach  var="order" items="${orders}">
		<div style="border:1px solid; margin-bottom:20px; padding:10px; background-color: #C5F6FA">
			<div style="border:1px solid; margin-bottom:10px; padding:10px; background-color: #C3C5FF">
				<h3><b>Numero ordine: ${order.id}</b><br/></h3>
				
				<p><b>Indirizzo di spedizione</b>: ${order.shippingAddress}</p>
				<p><b>Stato </b>: 
				<c:choose>
				<c:when test="${order.status == inserted}">Inserito</c:when>
				<c:when test="${order.status == paid}">Pagato</c:when>
				<c:when test="${order.status == cancelled}">Annullato</c:when>
				<c:otherwise>Errore</c:otherwise>
				</c:choose>
				</p>	
			</div>
			<c:forEach var="orderItem" items="${order.items}">
				<h2>${orderItem.book.title}</h2>
				<p><b>Prezzo unitario</b>: ${orderItem.price} euro<br/>
				<b>Quantità</b>: ${orderItem.quantity}
				</p>
				<hr style="border-color: #E8C5FA"/><br/>
			</c:forEach>
			<p><b>TOTALE:</b> ${order.amount} euro</p>
			<c:choose>
			<c:when test="${order.status == inserted}">
				<form method="POST" action="./order">
					<input type="hidden" name="orderId" value="${order.id}" />
					<input type="submit" name="updateOrder" value="Paga" style="margin-right: 20px; background-color: #C5FAC7; font-size: 20px"/>
					<input type="submit" name="updateOrder" value="Annulla" style="background-color: #FAC5C5; font-size: 20px"/>
				</form>
			</c:when>
			</c:choose>
		</div>
	</c:forEach>

	

</body>
</html>