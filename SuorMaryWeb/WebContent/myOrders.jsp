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
		Totale: <%= o.getAmount() %><br/>
		<form action="recapOrder" action="GET">
			   <input type="hidden" name="order" value="<%= o.getId() %>" />
			   <input type="submit" value="Visualizza Dettagli Ordine" class="button"/>
			</form> 
		<br/><hr><br/>
	<%}%>
</body>
</html>