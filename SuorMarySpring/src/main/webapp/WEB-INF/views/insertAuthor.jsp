<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>insert Author</title>
</head>
<body>
<div>
<h3>NEW AUTHOR</h3><br/>
	<form action="insertauthor" name="author" method="POST">
		  <label for="name">Name:</label><br>
		  <input type="text" name="name" required><br><br>
		  <label for="surnmae">Surname:</label><br>
		  <input type="text" name="surname" required><br><br>
		  <label for="description">Description:</label><br>
		  <textarea name="description"></textarea><br><br>
		  <input type="submit" value="Add">		
	</form>
</div>

</body>
</html>