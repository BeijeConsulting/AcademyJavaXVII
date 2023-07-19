<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>I Miei Ordini</title>
</head>
<body>
<%@ include file="header.jsp" %>
<p>
<c:choose>
	<c:when test="${empty usersOrder}"><h2>NESSUN ORDINE EFFETTUATO</h2></c:when>
	<c:otherwise>
		<c:forEach items="${usersOrder}" var="order">
		    <h3>N°Ordine:&nbsp;${order.id}</h3>
		    <h4>Stato Ordine:&nbsp;${order.status}</h4>
		    <h4>Totale Ordine:&nbsp;${order.amount}</h4>
		    <c:if test='${order.status eq "I"}'>
	            <form action="recapOrder" method="get">
	                <input type="hidden" name="order" value="${order.id}" />
	                <input type="submit" value="Visualizza Dettagli Ordine" class="button" />
	            </form>	
	        </c:if>		     
		</c:forEach>
	</c:otherwise>
</c:choose>
</p>
</body>
</html>