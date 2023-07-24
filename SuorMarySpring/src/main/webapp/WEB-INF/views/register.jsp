<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<style>
 .button{
			    background-color: #4CAF50;
            color: white;
            border: none;
            padding: 5px 10px;
            text-decoration: none;
            cursor: pointer;
            margin-right: 5px;
             display: inline-block;
    margin-right: 10px;
			
		}
</style>
</head>
<body>
<h1>Registrati</h1>

<form action="./register" method="POST">
  <label for="name">Nome:</label><br>
  <input type="text" name="name" ><br>
   <label for="username">Cognome:</label><br>
  <input type="text" name="surname" ><br>
  <label for="email">Email:</label><br>
  <input type="text" name="email" ><br>
  <label for="password">Password:</label><br>
  <input type="password" name="password" ><br><br>
  <input type="submit" value="Registrati" class="button">
</form> 

</body>
</html>