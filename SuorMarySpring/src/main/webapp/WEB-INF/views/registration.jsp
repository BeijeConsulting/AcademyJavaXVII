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

<title>Registrazione</title>
</head>
<body>

<div style="text-align: center">
<h1 style="font-family: fantasy; font-size: 36px">BOOKstoreONE</h1>
<h3>Registrati qui!</h3>
<p style="color:red">${registrationError}</p>

<form action="./registration" method="POST">
  <label for="email">Email:</label><br>
  <input type="text" name="email" ><br>
  <label for="password">Password:</label><br>
  <input type="text" name="password" ><br>
  <label for="name">Nome:</label><br>
  <input type="text" name="name" ><br>
  <label for="surname">Cognome:</label><br>
  <input type="text" name="surname" ><br><br>
  <input type="submit" value="Submit">
</form> <br>
<a href="./login">Accedi con un account esistente</a>
</div>
</body>
</html>