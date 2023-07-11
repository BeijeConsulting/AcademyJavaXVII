<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cerca Page</title>
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

<form action="contatto" method="POST">
  <label for="nome">Nome:</label><br>
  <input type="text" name="nome" ><br>
  <label for="cognome">Cognome:</label><br>
  <input type="text" name="cognome" ><br><br>
  <input type="submit" value="Submit">
</form> 
</body>
</html>