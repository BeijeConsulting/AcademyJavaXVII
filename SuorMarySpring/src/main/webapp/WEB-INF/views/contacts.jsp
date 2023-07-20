<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CONTACT LIST</title>
</head>
<body>

<c:choose>
	<c:when test="${empty contacts}">NO CONTACTS</c:when>
	<c:otherwise>
		<c:forEach items="${contacts}" var="contact">
			${contact.surname}&nbsp;${contact.name}<br/>
		</c:forEach>
	</c:otherwise>
</c:choose>


</body>
</html>