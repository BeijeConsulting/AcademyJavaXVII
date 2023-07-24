<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit book</title>
</head>
<body>
<div>

	<h3>EDIT BOOK</h3>
	<div>
	<form action="updatebook" name="updatebook" method="POST">
		  <label for="title">Title:</label><br>
		  <input type="text" name="title" value="${book.title }" readonly><br><br>
		  
		  <label for="description">Description:</label><br>
		  <textarea name="description" readonly>${book.description }</textarea><br><br>
		  
		  <label for="author">Author:</label><br>
		  <input type="text" name="author" value="${book.authorId }"readonly><br><br>
	
		  <label for="editor">Editor:</label><br>
		  <input type="text" name="editor" value="${book.editor }" readonly><br><br>
		  
		  <label for="price">Price:</label><br>
		  <input type="number" step="0.01" name="price" placeholder="00.00" min="0.00" value="${book.price}"><br><br>
		  
		  <label for="quantity">Quantity:</label><br>
		  <input type="number" name="quantity" min="1" value="${book.quantity }"><br><br>
		  <input type="hidden" name="bookId" value="${book.id}"><br><br>
		  <input type="submit" value="Save">		
	</form><br>
</div>
</div>
</body>
</html>