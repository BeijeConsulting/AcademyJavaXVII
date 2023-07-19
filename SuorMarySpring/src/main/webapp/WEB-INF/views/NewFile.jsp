<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List of books</title>
</head>
<body>
<h2>BOOKS</h2><br/><br/>
<c:choose>
	<c:when test="${empty booklist}">No book in the list</c:when>
	<c:otherwise>
		<c:forEach items="${booklist}" var="book">
			${book}<br/>
			${book.id}<br/>
			${book.title}<br/>
			${book.authorId}<br/>
			${book.price}<br/>
			${book.quantity}<br/>
		</c:forEach>
	</c:otherwise>
</c:choose>
</body>
</html>