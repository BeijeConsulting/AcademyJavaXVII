<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.model.Author"%>
    <%@page import="java.util.List"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi un autore</title>
</head>
<body>
<%@ include file="header.jsp" %>

<h1>Aggiungi un autore</h1>
<form action="createAuthor" method="POST">
<label for="name">Nome : </label>
<input type="text" name="name" /> <br> <br>
<label for="surname">Cognome : </label> 
<input type="text" name="surname" /> <br> <br>
<label for="description">Descrizione : </label>
<input type="text" name="description" /> <br> <br>
<input type="submit" value="crea autore" />
</form>
</body>
</html>