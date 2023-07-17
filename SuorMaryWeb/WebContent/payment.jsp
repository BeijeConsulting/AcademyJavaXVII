<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.beije.suormary.bookstore3.Order"%>
<%@page import="it.beije.suormary.bookstore3.BookStoreUtility"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Payment</title>
</head>
<body>

<h3>Contatti per spedizione: </h3>
<form>
<label for="address">Indirizzo Spedizione:</label><br>
  <input type="text" id="address" name="address"><br>
<input type="submit" value="Conferma" class="button">
</form>  
<%
	String address = request.getParameter("address");
	Order order =(Order) session.getAttribute("order");
	BookStoreUtility.payment(order.getId(), address);
%>
<form action="./welcome" action="GET">
   <input type="submit" value="Torna alla Home" class="button" onclick="alert:('Ordine Pagato!')"/>
</form>

</body>
</html>