<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>book list</title>
</head>
<body>
<h1>LISTA LIBRI</h1>
<c:choose>
	<c:when test="${empty booklist}">NO BOOKS</c:when>
	<c:otherwise>
		<c:forEach items="${booklist}" var="book">
			${book.title}&nbsp;${book.price}&nbsp;
			<form:form action="/addtobasket" modelAttribute="bookForm">
			    <form:hidden path="/addtobasket" value="${book.id}"/>
			    <input type="submit" value="+" />
			</form:form>
			<br/>
			<br/>
		</c:forEach>
	</c:otherwise>
</c:choose>
<c:if test="${empty user }"></c:if>
	<c:choose>
	<c:when test="${empty basket}">CART EMPTY</c:when>
	<c:otherwise>
		<c:forEach items="${basket}" var="book">
			${basket.bookId}&nbsp;${basket.quantity}<br/>
			<br/>
		</c:forEach>
	</c:otherwise>
</c:choose>
</body>
</html>
