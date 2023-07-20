<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.model.Book"%>
    <%@page import="it.beije.suormary.model.Author"%>
    <%@page import="java.util.List"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica autore</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Modifica autore</h1>

<form action="updateAuthor" method="POST">
<input type="hidden" name="id" value="${author.id}" />
<label for="name">Nome : </label>
<input type="text" name="name" value="${author.name}"/> <br> <br>
<label for="surname">Cognome : </label> 
<input type="text" name="surname" value="${author.surname}"/> <br> <br>
<label for="description">Descrizione : </label>
<input type="text" name="description" value="${author.description}"/> <br> <br>

<input type="submit" value="Modifica autore" />
</form>
</body>
</html>