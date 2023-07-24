<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit author</title>
</head>
<body>
	<div>
		<h3>EDIT BOOK</h3>
		<form action="updateauthor" name="updateauthor" method="POST">
			  <label for="name">Name:</label><br>
			  <input type="text" name="name" readonly value="${author.name}"><br><br>
			  
			  <label for="surname">Surname:</label><br>
			  <input type="text" name="surname" readonly value="${author.surname}"><br><br>
			  
			  <label for="description">Description:</label><br>
			  <textarea name="description">${author.description}</textarea><br><br>
			  
			  <input type="submit" value="Save">	
		</form>
		<br>
	</div>
</body>
</html>