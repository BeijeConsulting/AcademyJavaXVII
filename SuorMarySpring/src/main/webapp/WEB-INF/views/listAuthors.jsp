<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Autori</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h2>Autori disponibili:</h2><br/>

<c:choose>
	<c:when test="${empty authors}"><h2>NESSUN AUTORE DISPONIBILE</h2></c:when>
	<c:otherwise>
		<c:forEach items="${authors}" var="author">
			<h3>Nome:&nbsp;${author.name}</h3>
			<h4>Cognome:&nbsp;${author.surname}</h4>
			<h4>Descrizione:&nbsp;${author.description}</h4><br/> <br/>
			 <form action="updateAuthor" method="GET">
                    <input type="hidden" name="id" value="${author.id}" />
                    <input type="submit" value="Modifica autore" />
                </form>
                <form action="deleteAuthor" method="GET">
                    <input type="hidden" name="id" value="${author.id}" />
                    <input type="submit" value="Elimina autore" />
                </form>
		</c:forEach>
	</c:otherwise>
</c:choose>
</body>
</html>