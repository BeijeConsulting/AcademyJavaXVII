<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
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

<div>
	<form action="./homeservlet" method="POST">
		  <label for="email">Email:</label><br>
		  <input type="text" name="email" required><br>
		  <label for="password">Password:</label><br>
		  <input type="text" name="password" required><br><br>
		  <input type="submit" value="Login">
	</form>
	<br>
	<a href="signpage.jsp">
	<button name="selection" value="listaContatti" style="color:blue"><u>Sign up</u></button>
	</a>
</div>

</body>


</html>