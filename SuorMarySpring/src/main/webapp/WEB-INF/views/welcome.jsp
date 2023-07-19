<%@page import="it.beije.suormary.controller.BookStoreUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>WELCOME</title>
</head>
<body>

<p>
<h1> WELCOME&nbsp;${loggedUser.name}!!</h1><br/>

&nbsp;
<form action="myOrders" method ="post">
	<input type="submit" value="I miei ordini" class="button">
</form>
<h2>Libri disponibili:</h2><br/>

<c:choose>
	<c:when test="${empty books}"><h2>NESSUN LIBRO DISPONIBILE</h2></c:when>
	<c:otherwise>
		<c:forEach items="${books}" var="book">
			<h3>Titolo:&nbsp;${book.title}</h3>
			<h4>Descrizione:&nbsp;${book.description}</h4>
		</c:forEach>
	</c:otherwise>
</c:choose>

</p>
</body>
</html>