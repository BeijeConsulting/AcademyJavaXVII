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
<form action="createAuthor" method ="GET">
<input type="submit" value="aggiungi un autore" class="button" style="background-color: #4CAF50;color: white;border: none;padding: 5px 10px;text-decoration: none;cursor: pointer;margin-right: 5px;display: inline-block;margin-right: 10px;">
</form>
&nbsp;

<c:choose>
	<c:when test="${empty authors}"><h2>NESSUN AUTORE DISPONIBILE</h2></c:when>
	<c:otherwise>
		<c:forEach items="${authors}" var="author">
		<div style="border:1px solid #ccc;border-radius: 5px;padding: 10px;margin-bottom: 10px;background-color: #f9f9f9;">
			<h3>Nome:&nbsp;${author.name}</h3>
			<h3>Cognome:&nbsp;${author.surname}</h3>
			<h4>Descrizione:&nbsp;${author.description}</h4>
			 <form action="updateAuthor" method="GET">
                    <input type="hidden" name="id" value="${author.id}" />
                    <input type="submit" value="Modifica autore" style="background-color: #4CAF50;color: white;border: none;padding: 5px 10px;text-decoration: none;cursor: pointer;margin-right: 5px;display: inline-block;margin-right: 10px;"/>
                </form>
                <br>
                <form action="deleteAuthor" method="GET">
                    <input type="hidden" name="id" value="${author.id}" />
                    <input type="submit" value="Elimina autore" style="background-color: #4CAF50;color: white;border: none;padding: 5px 10px;text-decoration: none;cursor: pointer;margin-right: 5px;display: inline-block;margin-right: 10px;"/>
                </form>
                </div>
		</c:forEach>
	</c:otherwise>
</c:choose>
</body>
</html>