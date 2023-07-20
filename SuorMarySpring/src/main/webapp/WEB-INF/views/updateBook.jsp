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
<title>Modifica libro</title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1>Modifica libro</h1>

<form action="updateBook" method="POST">
<input type="hidden" name="id" value="${book.id}" />
<label for="title">Titolo : </label>
<input type="text" name="title" value="${book.title}"/> <br> <br>
<label for="description">Descrizione : </label>
<input type="text" name="description" value="${book.description}"/> <br> <br>
<label for="editor">Editore : </label> 
<input type="text" name="editor" value="${book.editor}"/> <br> <br>
<label for="price">Prezzo : </label>
<input type="text" name="price" value="${book.price}"/> <br> <br>
<label for="quantity">Quantità : </label>
<input type="text" name="quantity" value="${book.quantity}"/> <br> <br>
<label for="quantity">Scegli un autore : </label> <br>

 <c:forEach items="${authors}" var="author">
    <label for="authorId">${author.name}</label>
    <input type="radio" name="authorId" value="${author.id}" ${author.id == book.authorId ? 'checked' : ''}> <br>
</c:forEach>

<input type="submit" value="Modifica libro" />
</form>
</body>
</html>