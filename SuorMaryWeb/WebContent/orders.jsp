<%@page import="it.beije.suormary.bookstore1.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>I miei ordini</title>
</head>
<body>
	<ul>
		<li style="display: inline; margin-right:30px;"><a href="./ShopServlet">Shop</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./CartServlet">Cart</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./OrderServlet">Orders</a></li>
		<li style="display: inline;"><a href="./LogoutServlet">Logout</a></li>
	</ul>
	<%List<Order> lo = (List<Order>)session.getAttribute("orders");%>

</body>
</html>