<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form:form action="newuser" method="POST">
  <label for="name">Name:</label><br>
  <input type="text" name="name" required><br><br>
  
  <label for="Surname">Surname:</label><br>
  <input type="text" name="surname" required><br><br>
  
  <label for="email">Email:</label><br>
  <input type="text" name="email" required><br><br>
  
  <label for="password">Password:</label><br>
  <input type="text" name="password" required><br><br>
  
  <input type="submit" value="Sign up">
</form:form> 
<br>

</body>
</html>