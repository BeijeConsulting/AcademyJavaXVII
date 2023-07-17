<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
</head>
<body>
<h1>Registrati</h1>
<%
String loginError = (String) session.getAttribute("loginError");
if (loginError != null) {
	%>
	<p style="color:red"><%= loginError %></p>
	<%
	
	session.removeAttribute("loginError");
}
%>

<form action="./register" method="POST">
  <label for="name">Nome:</label><br>
  <input type="text" name="name" ><br>
   <label for="username">Cognome:</label><br>
  <input type="text" name="surname" ><br>
  <label for="email">Email:</label><br>
  <input type="text" name="email" ><br>
  <label for="password">Password:</label><br>
  <input type="password" name="password" ><br><br>
  <input type="submit" value="Registrati">
</form> 

</body>
</html>