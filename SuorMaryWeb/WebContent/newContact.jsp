<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crea un nuovo contatto</title>
</head>
<body>
<h1>Crea un nuovo contatto</h1>
<form action="./newContact" method="POST">
 	<label for="name">Nome : </label>
 	<input type="text" name="name"> <br> <br>
 	<label for="surname">Cognome : </label>
 	<input type="text" name="surname"> <br> <br>
	<label for="note">Note : </label>
	<input type="text" name="note"> <br>  <br>
	<input type="submit" value="Crea">
</form>
</body>
</html>