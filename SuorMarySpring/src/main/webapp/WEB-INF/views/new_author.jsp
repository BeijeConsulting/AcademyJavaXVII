<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
    	background-color: #FADAFF;
	}
</style>
<meta charset="ISO-8859-1">
<title>Nuovo Autore</title>
</head>
<body>
<div style="text-align: center">
<h1 style="font-family: fantasy; font-size: 36px">BOOKstoreONE</h1>
<p style="color:green">${newAuthorMessage}</p>
<form action="./author" method="POST">
  <label for="name">Nome:</label><br>
  <input type="text" name="name" ><br>
  <label for="surname">Cognome:</label><br>
  <input type="text" name="surname" ><br><br>
  <label for="description">Descrizione:</label><br>
  <textarea name="description" rows=5 cols=40></textarea><br><br>
  <input type="submit" value="Submit">
</form> 
<br><br>
<a href="./login">Torna al login</a>
</div>
</body>
</html>