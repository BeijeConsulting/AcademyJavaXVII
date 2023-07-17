<%@page import="it.beije.suormary.bookstore3.BookStoreUtility"%>
<%@page import="it.beije.suormary.bookstore3.User"%>
<%@page import="it.beije.suormary.bookstore3.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>I miei ordini</title>
</head>
<body>
<%@ include file="header.jsp" %>
<%
	List<Order> orders = (List) session.getAttribute("orders");%>

<h2>I miei Ordini: </h2><br/><br/>


<%	for(Order o : orders){
		%> Ordine numero: <%= o.getId()%><br/>
		Stato Ordine: <%= o.getStatus() %><br/>
		<%if (o.getStatus()=='I' ){
		%>	
			<form action="payment" action="GET">
			   <input type="hidden" name="order" value="<%= o.getId() %>" />
			   <input type="submit" value="Paga Ordine" class="button"/>
			</form> 
			<form action="updateOrder" action="GET">
				<input type="hidden" name="order" value="<%= o.getId() %>" />
  				<input type="submit" value="Modifica ordine" class="button"/>
			</form> 
			<form action="deleteOrder" action="GET">
				<input type="hidden" name="order" value="<%= o.getId() %>" />
   				<input type="submit" value="Cancella Ordine" class="button"/>
   			</form>
		<%}%>
		
		Totale: <%= o.getAmount() %><br/>
		<br/><hr><br/>
	<%}%>
</body>
</html>