<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form User</title>
</head>
<body>

<%
%>

<form action="./insert_user" method="POST">
  <label for="name">Name:</label><br>
  <input type="text" name="name" ><br>
  <label for="surname">Surname:</label><br>
  <input type="text" name="surname" ><br>
  <label for="email">Email:</label><br>
  <input type="text" name="email" ><br>
  <label for="password">Password:</label><br>
  <input type="text" name="password" ><br><br>
  <input type="submit" value="Submit">
</form> 

</body>
</html>