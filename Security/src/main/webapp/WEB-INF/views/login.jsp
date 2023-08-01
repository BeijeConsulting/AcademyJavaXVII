<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>LOGIN</h1>

${error}

<form action="./login" method="post">
   <div> 
      <label> User Name : <input type="text" name="username" /> </label> 
   </div> 
   <div> 
   <label> Password: <input type="password" name="password" /> </label> 
   </div> 
   <div> 
      <input type="submit" value="Sign In" /> </div> 
</form>
</body>
</html>