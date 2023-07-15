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
<!--button type="button" onclick="window.location.href='bookstoreRegistration.jsp'">Vuoi registrarti?</button-->
<form style="text-align: left" action="./bookstoreRegistration">
	<button type="submit" style="background-color: #2c5e29; color: white; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer;">Registrati</button>
</form>


</body>
</html>