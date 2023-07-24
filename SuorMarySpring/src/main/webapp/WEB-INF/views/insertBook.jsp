<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Book</title>
</head>
<body>
	<div>
	<h3>NEW BOOK</h3><br/>
	<form action="insertbook" name="book" method="POST">
		  <label for="title">Title:</label><br>
		  <input type="text" name="title" required><br><br>
		  <label for="description">Description:</label><br>
		  <textarea name="description"></textarea><br><br>
		  
		  <label for="author">Author:</label><br>
		  <select name="author" style="width:135pt;" required>
		  	<c:forEach items="${authorlist}" var="author">
		  		<option value="${author.id}">${author.surname}</option>
		  	</c:forEach>
		  </select>
		  <br><br>
		  
		  <label for="editor">Editor:</label><br>
		  <input type="text" name="editor"><br><br>
		  <label for="price">Price:</label><br>
		  <input type="number" step="0.01" name="price" placeholder="00.00" min="0.00" required><br><br>
		  <label for="quantity">Quantity:</label><br>
		  <input type="number" name="quantity" min="1" required><br><br>
		  <input type="submit" value="Add">		
	</form>
	<br>
</div>
</body>
</html>