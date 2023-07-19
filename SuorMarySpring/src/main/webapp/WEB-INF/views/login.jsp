<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
    	background-color: #FADAFF;
	}
</style>
<meta charset="ISO-8859-1">
<title>Login Page JSP</title>
</head>
<body>
<div style="text-align: center">
<h1 style="font-family: fantasy; font-size: 36px">BOOKstoreONE</h1>
<p style="color:red">${loginError}</p>
<p style="color:green">${registrationSuccess}</p>


<form action="./login" method="POST">
  <label for="email">Email:</label><br>
  <input type="text" name="email" ><br>
  <label for="password">Password:</label><br>
  <input type="text" name="password" ><br><br>
  <input type="submit" value="Submit">
</form> 
<br><br>
<a href="registration">Non ho un account</a><br/><br/>
<a href="./author">Aggiungi un autore</a><br/><br/>
<a href="./book">Aggiungi un libro</a>
</div>
</body>
</html>