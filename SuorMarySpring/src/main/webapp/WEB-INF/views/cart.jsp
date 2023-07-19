<%@page import="java.util.Map"%>
<%@page import="it.beije.suormary.bin.bookstore1.Book"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
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
<title>Cart</title>
</head>
<body>
	<ul>
		<li style="display: inline; margin-right:30px;"><a href="./shop">Shop</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./cart">Cart</a></li>
		<li style="display: inline; margin-right:30px;"><a href="./orders">Orders</a></li>
		<li style="display: inline;"><a href="./logout">Logout</a></li>
	</ul>
	<hr/>

	<c:choose>
	<c:when test="${empty books}">NESSUNO</c:when>
	<c:otherwise>
		<c:forEach items="${books}" var="b">
			<div style="border: 1px solid; margin-bottom: 10px; padding: 5px; background-color: #C5F6FA">
				<h2>${b.key.title}</h2>
				<h3>di ${b.key.author.name} ${b.key.author.surname}</h3>
				<p>
				<c:choose>
				<c:when test="${empty b.key.description}"> </c:when>
				<c:otherwise> ${b.key.description}</c:otherwise>
				</c:choose>	
				</p>
				<p>${b.key.editor}</p>
				<p>Quantità: ${b.value}</p>
				<form method="POST" action="./cart">
					<input type="hidden" value="${b.key.id}" name="bookId" />
					<select name="quantity" >
						<c:forEach items="${b.key.itemQuantity }" var="value">
							<option value="${value}">${value}</option>
						</c:forEach>
					</select>
					<input type="submit" name="removeBook" value="Rimuovi" />
				</form>
			</div>
		</c:forEach>
	</c:otherwise>
	</c:choose>

	<br/>
	<hr/>
	<br/>
	<div style="margin-bottom: 20px; margin-top: 10px; padding: 5px;">
		<form method="POST" action="./OrderServlet">
			<label>Indirizzo di consegna: </label>
			<input type="text" name="address" style="width: 500px;" /> <br/><br/>
			<input type="submit" name="updateOrder" value="Invia" style="width: 300px; font-size: 20px;" />
		</form>
	</div>
</body>
</html>