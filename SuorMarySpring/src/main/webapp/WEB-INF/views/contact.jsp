<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CONTACT</title>
</head>
<body>

<c:choose>
	<c:when test="${empty contact}">NO CONTACT</c:when>
	<c:otherwise>
			${contact.surname}&nbsp;${contact.name}<br/>
			<c:forEach items="${contact.details}" var="detail">
			&nbsp;&nbsp;${detail.type}&nbsp;${detail.detail}<br/>
			</c:forEach>
	</c:otherwise>
</c:choose>


</body>
</html>