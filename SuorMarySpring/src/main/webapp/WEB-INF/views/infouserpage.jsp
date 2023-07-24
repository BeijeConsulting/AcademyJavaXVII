<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>
<div style="display: flex;">
	<div style="flex: 1;">
	<form:form action="infouser" method="GET">
	<input type="submit" value="Profile" disabled>
	</form:form> 
	</div>
	
	<div style="flex: 1;">
	<form:form action="booklist" method="GET">
	<input type="submit" value="Buy book">
	</form:form> 
	</div>
	
	<div style="flex: 1;">
	<form:form action="stock" method="GET">
	<input type="submit" value="Stock">
	</form:form> 
	</div>
</div>

<div>
<h2>Info user</h2>
<br/>
	Name: ${user.name}<br/>
	Surname: ${user.surname}<br/>
	Email: ${user.email}<br/>
</div>

<br/>
<form:form action="logout" method="GET">
<input type="submit" value="Log out">
</form:form> 

<br/>
<div>
<h2>Orders</h2>
<br/>
<c:if test="${empty orders}">NO ORDERS</c:if>
	<c:forEach items="${orders}" var="order">
		Id: ${order.id}<br/>
		Date: ${order.date}<br/>
		Status: ${order.status}<br/>
		Amount: ${order.amount}<br/>
		Address: ${order.shippingAddress}<br/>
		Items: ${order.items}<br/>
		<c:if test="${order.status.equals(\"C\")}">
		<form:form action="deleteorder" method="POST">
			<input type="hidden" name="orderId" value="${order.id}"/>
		    <input type="submit" value="Delete"/>
		</form:form>
		</c:if>
		<br/>
	</c:forEach>
</div>
</body>
</html>