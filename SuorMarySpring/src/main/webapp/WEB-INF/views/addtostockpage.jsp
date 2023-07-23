<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add to stock</title>
</head>
<body>

<c:when test="${empty user}">
<form:form action="home" method="GET">
	<input type="submit" value="Log in">
	</form:form>
</c:when>
<c:otherwise>
	<form:form action="infouser" method="GET">
	<input type="submit" value="Profile">
	</form:form> 
	
	<form:form action="booklist" method="GET">
	<input type="submit" value="Buy book">
	</form:form> 
	
	<form:form action="stock" method="GET">
	<input type="submit" value="Stock" disabled>
	</form:form> 
</c:otherwise>

<h2>lista libri e form inserimento</h2>
<h2>lista autori e form inserimento</h2>
</body>
</html>