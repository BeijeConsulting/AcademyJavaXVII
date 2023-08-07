<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page JSP</title>
</head>
<body>

<p style="color:red">${loginError}</p>

<form action="./login" method="POST">
  <label for="username">Usernameeeee:</label><br>
  <input type="text" name="username" ><br>
  <label for="password">Password:</label><br>
  <input type="text" name="password" ><br><br>
  <input type="submit" value="Submit">
</form> 

</body>
</html>