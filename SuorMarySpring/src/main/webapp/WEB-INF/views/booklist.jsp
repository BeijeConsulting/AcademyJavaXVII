<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			${book.title}&nbsp;${book.price}<br/>
			<br/>
		</c:forEach>
	</c:otherwise>
</c:choose>
</body>
</html>