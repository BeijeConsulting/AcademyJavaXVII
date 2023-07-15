<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<%
String loginError = (String) session.getAttribute("loginError");
if (loginError != null) {
	%>
	<p style="color:red"><%= loginError %></p>
	<%
	
	session.removeAttribute("loginError");
}
%>

<form action="./bookstoreLogin" method="POST">
  <label for="email">Email:</label><br>
  <input type="email" name="email" id="email" required><br>
  
  <label for="password">Password:</label><br>
  <input type="password" name="password" id="password" required><br><br>
  
  <input type="submit" value="Login">
</form> 
<br>
<button type="button" onclick="window.location.href='bookstoreRegistration.jsp'">Vuoi registrarti?</button>


</body>
</html>