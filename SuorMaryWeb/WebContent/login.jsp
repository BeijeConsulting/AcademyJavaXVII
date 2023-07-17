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
<%
String loginError = (String) session.getAttribute("loginError");
if (loginError != null) {
	%>
	<p style="color:red"><%= loginError %></p>
	<%
	
	session.removeAttribute("loginError");
}
%>

<%
String registrationSuccess = (String) session.getAttribute("registrationSuccess");
if (registrationSuccess != null) {
	%>
	<p style="color:green"><%= registrationSuccess %></p>
	<%
	
	session.removeAttribute("registrationSuccess");
}
%>
<form action="./LoginServlet" method="POST">
  <label for="email">Email:</label><br>
  <input type="text" name="email" ><br>
  <label for="password">Password:</label><br>
  <input type="text" name="password" ><br><br>
  <input type="submit" value="Submit">
</form> 
<br><br>
<a href="./RegisterServlet">Non ho un account</a><br/><br/>
<a href="./NewAuthorServlet">Aggiungi un autore</a>
</div>
</body>
</html>