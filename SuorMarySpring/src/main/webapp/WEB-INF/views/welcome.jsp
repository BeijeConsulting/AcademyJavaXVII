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
BUONGIORNO&nbsp;${loggedUser.fullName}&nbsp;!!<br/>
<br/>
Questi sono i libri che hai ordinato:<br/>

<%-- c:if test="${empty libri}">NESSUNO</c:if --%>
<c:choose>
	<c:when test="${libri.size() < 0}">NESSUNO</c:when>
	<c:otherwise>
		<c:forEach items="${libri}" var="libro">
			${libro}<br/>
		</c:forEach>
	</c:otherwise>
</c:choose>

</p>
</body>
</html>