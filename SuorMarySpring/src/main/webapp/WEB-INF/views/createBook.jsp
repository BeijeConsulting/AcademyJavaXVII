<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="it.beije.suormary.controller.Author"%>
    <%@page import="java.util.List"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi un libro</title>
</head>
<body>
<%@ include file="header.jsp" %>

<h1>Aggiungi un libro</h1>
<form action="createBook" method="POST">
<label for="title">Titolo : </label>
<input type="text" name="title" /> <br> <br>
<label for="description">Descrizione : </label>
<input type="text" name="description" /> <br> <br>
<label for="editor">Editore : </label> 
<input type="text" name="editor" /> <br> <br>
<label for="price">Prezzo : </label>
<input type="text" name="price" /> <br> <br>
<label for="quantity">Quantità : </label>
<input type="number" name="quantity" /> <br> <br>
<label for="quantity">Scegli un autore : </label> <br>
<c:forEach items="${authors}" var="author">
<label for ="authorId">${author.name}</label> 
<input type="radio"  name="authorId" value="${author.id}"> <br>
 </c:forEach>
<input type="submit" value="Aggiungi libro" />
</form>
</body>
</html>