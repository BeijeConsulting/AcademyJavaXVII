<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login page</title>
</head>
<body>
<p style="color:red">${loginError}</p>

<form:form action="loginpage" method="POST">
  <label for="username">Email:</label><br>
  <input type="text" name="email" ><br>
  <label for="password">Password:</label><br>
  <input type="text" name="password" ><br><br>
  <input type="submit" value="Login">
</form:form> 
<br>
<form:form action="signup" method="POST">
  <input type="submit" value="Sign up">
</form:form> 
</body>
</html>