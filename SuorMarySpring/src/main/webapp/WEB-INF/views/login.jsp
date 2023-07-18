<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page JSP</title>
</head>
<body>

  <p style="color:red;">${loginError}</p>

<h3>Non hai un account? <a href="http://localhost:8080/SuorMarySpring/register">Registrati</a></h3>
<form action="./login" method="POST">
  <label for="email">Email:</label><br>
  <input type="text" name="email" ><br>
  <label for="password">Password:</label><br>
  <input type="password" name="password" ><br><br>
  <input type="submit" value="Login">
</form> 


</body>
</html>