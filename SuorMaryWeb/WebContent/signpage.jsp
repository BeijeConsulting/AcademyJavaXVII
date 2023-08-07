<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
</head>
<body>

<div>
	<form action="./homeservlet" method="POST">
		  <label for="name">Name:</label><br>
		  <input type="text" name="name" required><br>
		  <label for="surname">Surname:</label><br>
		  <input type="text" name="surname" required><br>
		  <label for="email">Email:</label><br>
		  <input type="text" name="email" required><br>
		  <label for="password">Password:</label><br>
		  <input type="text" name="password" required><br><br>
		  <input type="submit" value="Sign up">
	</form>
</div>

</body>
</html>