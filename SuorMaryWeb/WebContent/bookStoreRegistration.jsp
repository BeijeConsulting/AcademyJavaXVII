<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="./bookStoreRegistration" method="POST">
  <label for="email">Email:</label>
  <input type="email" name="email" id="email" required><br>
  
  <label for="password">Password:</label>
  <input type="password" name="password" id="password" required><br>
  
  <label for="name">Nome:</label>
  <input type="text" name="name" id="name" required><br>
  
  <label for="surname">Cognome:</label>
  <input type="text" name="surname" id="surname" required><br>
  
  <input type="submit" value="Registration">
</form>
</body>
</html>