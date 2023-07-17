<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div>
	<form action="./listservlet" name="author" method="POST">
		  <label for="name">Name:</label><br>
		  <input type="text" name="name" required><br><br>
		  <label for="surnmae">Surname:</label><br>
		  <input type="text" name="surname" required><br><br>
		  <label for="description">Description:</label><br>
		  <textarea name="description"></textarea><br><br>
		  <input type="submit" value="Add">		
	</form>
	<%session.setAttribute("form", "author");%>
</div>

</body>
</html>